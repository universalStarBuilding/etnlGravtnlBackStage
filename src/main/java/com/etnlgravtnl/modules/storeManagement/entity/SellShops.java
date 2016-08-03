package com.etnlgravtnl.modules.storeManagement.entity;

import com.etnlgravtnl.common.persistence.DataEntity;
import com.etnlgravtnl.common.utils.DateUtils;
import com.etnlgravtnl.common.utils.StringUtils;

import java.util.Date;

public class SellShops extends DataEntity<SellShops> {

    private Integer userId;

    private String logo;

    private Integer industryId;

    private String industryName;

    private String signature;

    private String storeDesc;

    private Date createDate;

    private String userName;

    private String storeName;

    private String statusCode;

    private String statusName;

    private String auth;

    private String busiPic;

    private String applyPic;

    private String area;

    private String postcode;

    private String phone;

    private String fax;

    private String trademark;

    private String registNo;

    private String website;

    private String mainProd;

    private String manage;

    private String custom;

    private String employee;

    private String turnover;

    private String factory;

    private String market;

    private String exports;

    private String quality;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusiPic() {
        return busiPic;
    }

    public void setBusiPic(String busiPic) {
        this.busiPic = busiPic;
    }

    public String getApplyPic() {
        return applyPic;
    }

    public void setApplyPic(String applyPic) {
        this.applyPic = applyPic;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getRegistNo() {
        return registNo;
    }

    public void setRegistNo(String registNo) {
        this.registNo = registNo;
    }

    public String getMainProd() {
        return mainProd;
    }

    public void setMainProd(String mainProd) {
        this.mainProd = mainProd;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getExports() {
        return exports;
    }

    public void setExports(String exports) {
        this.exports = exports;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getUserName() {
        return StringUtils.trimToEmpty(userName);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogo() {
        return StringUtils.trimToEmpty(logo);
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return StringUtils.trimToEmpty( industryName);
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getSignature() {
        return StringUtils.trimToEmpty( signature);
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStoreDesc() {
        return StringUtils.trimToEmpty( storeDesc);
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public String getCreateDate() {
        return createDate==null?"":DateUtils.formatDate(createDate);
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStoreName() {
        return StringUtils.trimToEmpty( storeName);
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStatusCode() {
        return StringUtils.trimToEmpty( statusCode);
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return StringUtils.trimToEmpty( statusName);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}