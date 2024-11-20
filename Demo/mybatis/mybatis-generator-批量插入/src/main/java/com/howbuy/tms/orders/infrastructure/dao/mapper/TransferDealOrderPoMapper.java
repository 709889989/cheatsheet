package com.howbuy.tms.orders.infrastructure.dao.mapper;

import com.howbuy.tms.orders.infrastructure.dao.po.TransferDealOrderPo;

public interface TransferDealOrderPoMapper {
    int deleteByPrimaryKey(String transferNo);

    int insert(TransferDealOrderPo record);

    int insertSelective(TransferDealOrderPo record);

    TransferDealOrderPo selectByPrimaryKey(String transferNo);

    int updateByPrimaryKeySelective(TransferDealOrderPo record);

    int updateByPrimaryKey(TransferDealOrderPo record);
}