package com.dedasp.system.service.supplier.impl;

import com.dedasp.common.core.domain.AjaxResult;
import com.dedasp.common.utils.SecurityUtils;
import com.dedasp.system.domain.spdomain.SysBid;
import com.dedasp.system.domain.spdomain.SysBidDetail;
import com.dedasp.system.dto.BidHelper;
import com.dedasp.system.mapper.spmapper.*;
import com.dedasp.system.service.supplier.SysBidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysBidServiceImpl implements SysBidService {
    @Resource
    private SysBidMapper sysBidMapper;
    @Resource
    private SysBidTenderMapper sysBidTenderMapper;
    @Resource
    private SysBidDetailMapper sysBidDetailMapper;
    @Resource
    private SysBidDetailRelationshipMapper sysBidDetailRelationshipMapper;
    @Resource
    private SysUserBidMapper sysUserBidMapper;

    /**
     * 添加投标信息
     * @param bidHelper
     * @return
     */
    @Override
    public AjaxResult addBid(BidHelper bidHelper) {
        if (bidHelper.getSysBid() == null && bidHelper.getSysTender2() == null){
            return AjaxResult.error("投标信息不能为空");
        }

        if (bidHelper.getSysBid().getBidDetailList().isEmpty()){
            return AjaxResult.error("投标明细不能为空");
        }

        if (bidHelper.getSysBid().getBidFrequency() > 1){
            //更新投标信息
            sysBidMapper.updateBidByBidId(bidHelper.getSysBid().getBidId());
        }

        SysBid sysBid = bidHelper.getSysBid();
        sysBid.setLatestDid(1);

        //添加投标信息
        Integer row = sysBidMapper.addBid(sysBid);

        if (row < 0){
            return AjaxResult.error("投标信息添加失败");
        }

        //添加投标与供应商的联系
        sysUserBidMapper.addUserBid(SecurityUtils.getUserId(),sysBid.getBidId());

        //添加投标信息,跟投标公告联系
        sysBidTenderMapper.addBidTenderRelation(sysBid.getBidId(),bidHelper.getSysTender2().getTenderNumber());

        //添加投标明细
        sysBid.getBidDetailList().forEach(bidDetail -> {
            sysBidDetailMapper.addBidDetail(bidDetail);
            //添加明细,跟投标信息联系
            sysBidDetailRelationshipMapper.addBidDetailRelation(bidDetail.getBidDetailId(),sysBid.getBidId());
        });

        return AjaxResult.success(row);
    }

    /**
     * 根据公告编号获取最新投标信息
     * @param tenderNumber
     * @return
     */

    @Override
    public AjaxResult getLastBid(String tenderNumber) {

        SysBid sysBid = sysBidMapper.getLastBid(tenderNumber,SecurityUtils.getUserId());
        ArrayList<SysBidDetail> bidDetails = new ArrayList<>();
        bidDetails.add(new SysBidDetail());
        sysBid.setBidDetailList(bidDetails);

        return AjaxResult.success(sysBid);
    }

    /**
     * 代理获取投标信息
     * @param tenderNumber
     * @return
     */
    @Override
    public AjaxResult getBidDetailInformation(String tenderNumber) {
        List<SysBid> bids = sysBidMapper.getBidDetailInformation(tenderNumber);

        return AjaxResult.success(bids);
    }
}
