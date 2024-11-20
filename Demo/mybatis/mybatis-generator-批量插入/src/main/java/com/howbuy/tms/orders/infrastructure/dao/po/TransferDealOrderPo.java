package com.howbuy.tms.orders.infrastructure.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class TransferDealOrderPo implements Serializable {
    private String transferNo;

    private String transferType;

    private String txAcctNo;

    private String custName;

    private String invstType;

    private String disCode;

    private String productCode;

    private String productName;

    private String tProductCode;

    private String tProductName;

    private BigDecimal appTatio;

    private String cpAcctNo;

    private String protocolNo;

    private String taTradeDt;

    private String redeemDealNo;

    private String purchaseDealNo;

    private String settleDt;

    private String redeemAckDt;

    private BigDecimal redeemAckAmt;

    private BigDecimal redeemFee;

    private String purchaseAckDt;

    private BigDecimal purchaseAckAmt;

    private BigDecimal purchaseFee;

    private BigDecimal fee;

    private BigDecimal adviserFee;

    private BigDecimal refundAmt;

    private String redeemStatus;

    private String purchaseStatus;

    private String processStatus;

    private String failDesc;

    private String appDate;

    private String appTime;

    private String orderStatus;

    private String bankCode;

    private String bankAcct;

    private String txCode;

    private String externalDealNo;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}