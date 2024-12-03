package com.dedasp.system.mapper.spmapper;

import com.dedasp.system.domain.spdomain.SysWinBidPublicity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysWinBidPublicityMapper {
    List<SysWinBidPublicity> getPublicityList();
}
