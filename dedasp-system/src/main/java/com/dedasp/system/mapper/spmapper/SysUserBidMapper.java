package com.dedasp.system.mapper.spmapper;

import org.apache.ibatis.annotations.Param;

public interface SysUserBidMapper {
    void addUserBid(@Param("userId") Long userId,@Param("bidId") Long bidId);
}
