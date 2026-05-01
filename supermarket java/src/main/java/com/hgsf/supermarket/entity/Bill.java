package com.hgsf.supermarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@TableName("smbms_bill")
public class Bill {
    @TableId(type = IdType.AUTO)
    private Long id; // 账单ID
    private String billCode; // 账单编码
    private String productName; // 商品名称
    private String productDesc; // 商品描述
    private Double productCount; // 商品数量
    private String productUnit; // 商品单位
    private Double totalPrice; // 总金额
    private Integer isPayment; // 是否支付（1：已支付，2：未支付）
    private Long createdBy; // 创建人ID
    //将服务端的日期对象数据响应给客户端时转换为字符串格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    //将客户端请求的日期字符串转换为Date对象
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate; // 创建时间
    private Long modifyBy;//更新者（userID)
    private Date modifyDate;//更新时间
    private Long providerId; // 关联供应商ID

    //一对一查询，一个账单对于一个供应商
    @TableField(exist = false)
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    // 无参构造
    public Bill() {
    }

    // 带参构造（核心字段）
    public Bill(Long id, String billCode, String productName, String productDesc,
                Double productCount, String productUnit, Double totalPrice,
                Integer isPayment, Long createdBy, Long providerId) {
        this.id = id;
        this.billCode = billCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCount = productCount;
        this.productUnit = productUnit;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createdBy = createdBy;
        this.providerId = providerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Double getProductCount() {
        return productCount;
    }

    public void setProductCount(Double productCount) {
        this.productCount = productCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billCode='" + billCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productCount=" + productCount +
                ", productUnit='" + productUnit + '\'' +
                ", totalPrice=" + totalPrice +
                ", isPayment=" + isPayment +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", providerId=" + providerId +
                ", provider=" + provider +
                '}';
    }
}
