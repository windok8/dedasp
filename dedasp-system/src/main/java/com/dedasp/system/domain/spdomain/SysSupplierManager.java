package com.dedasp.system.domain.spdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysSupplierManager {
    private Integer id;
    //建档人
    private String Archiver;
    //供应商编码
    private String SupplierNumber;
    //供应商名称
    private String SupplierName;
    private String SupplierDesoription;
    private String BrandCategory;
    private String SupplierGroup;
    private String SupplierType;
    private String SupplierGrade;
    private String Realname;
    private String Gender;
    private LocalDateTime BirthDate;
    private String PhoneNumber;
    private String Address;
    private String Note;
    private String Photo;
    //开户银行
    private String BankOpenName;
    //开户名称
    private String BankOpenAccount;
    //账号
    private String Account;
    //开户地址
    private String BankOpenAddress;
    //账期
    private Integer AccountPeriod;
    //授信额度
    private BigDecimal CreditLine;
    //期初未开票金额
    private BigDecimal UnbilledAmount;
    //开票公司名称
    private String InvoiceCompanyName;
    //纳税人识别号
    private String TaxpayerIdentificationNumber;
    private String Email;
    private String Password;
    private String AppID;
    private String openid;
    //账户余额
    private BigDecimal AccountBalance;
    //账单日
    private Integer BillDay;
    //期初账户余额
    private BigDecimal InitialAccountBalance;
    //应开票金额
    private BigDecimal AmountToBeInvoiced;
    //已开票金额
    private BigDecimal InvoicedAmount;
    //未开票金额
    private BigDecimal UninvoicedAmount;
    //可用账户余额
    private BigDecimal AvailableAccountBalance;
    //合作日期
    private LocalDateTime CooperateDate;
    //统一社会信用代码
    private String SocialCreditCode;
    //注册资金
    private Integer RegisteredCapital;
    //营业执照正本
    private String BusinessLicense;
    //营业执照副本
    private String BusinessLicenseCopy;
    //授权书
    private String AuthorizationLetter;
    //授权人身份证复印件
    private String AuthorizerIdCardCopy;
    //其他证件
    private String OtherDocuments;
    //供应商类别
    private String SupplierCategory;
    //企业性质
    private String EnterpriseNature;
    //传真号码
    private String FaxNumber;
    //公司网址
    private String Website;
    //法人代表
    private String LegalRepresentative;
    //成立时间
    private LocalDateTime EstablishmentTime;
    //主要客户
    private String MajorCustomers;
    //主要产品
    private String MajorProducts;
    //经营范围
    private String BusinessScope;
}
