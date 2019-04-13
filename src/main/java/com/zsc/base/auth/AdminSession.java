//package com.zsc.base.auth;
//import com.zsc.base.entity.Admin;
//import com.zsc.base.exception.SysException;
//
//import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 用户的基础类
// *
// * @author lujun
// */
//public class AdminSession implements Serializable {
//    private static final long serialVersionUID = 1L;
//    //管理员ID
//    private Integer adminId;
//    //管理员编号
//    private String adminNo;
//    //工会编号
//    private String unionNo;
//    //登录名称
//    private String account;
//    //等级0:超级管理员,1:普通管理员,2:用户
//    private Integer adminRank;
//    //姓名
//    private String trueName;
//    //联系电话
//    private String phone;
//    //状态0:无效,1:有效
//    private Integer state;
//    private Set<String> funSet;
//    public AdminSession() {}
//    public AdminSession(Admin admin) {
//        if(admin==null){
//            throw new SysException("admin is empty");
//        }
//        this.adminId = admin.getId();
//        this.adminNo = admin.getAdminNo();
//        this.unionNo = admin.getUnionNo();
//        this.account = admin.getAccount();
//        this.trueName = admin.getTrueName();
//        this.phone = admin.getPhone();
//        this.adminRank = admin.getAdminRank();
//        this.funSet = new HashSet<>();
//    }
//
//    public Integer getAdminId() {
//        return adminId;
//    }
//
//    public void setAdminId(Integer adminId) {
//        this.adminId = adminId;
//    }
//
//    public String getAdminNo() {
//        return adminNo;
//    }
//
//    public void setAdminNo(String adminNo) {
//        this.adminNo = adminNo;
//    }
//
//    public String getUnionNo() {
//        return unionNo;
//    }
//
//    public void setUnionNo(String unionNo) {
//        this.unionNo = unionNo;
//    }
//
//    public String getAccount() {
//        return account;
//    }
//
//    public void setAccount(String account) {
//        this.account = account;
//    }
//
//    public Integer getAdminRank() {
//        return adminRank;
//    }
//
//    public void setAdminRank(Integer adminRank) {
//        this.adminRank = adminRank;
//    }
//
//    public String getTrueName() {
//        return trueName;
//    }
//
//    public void setTrueName(String trueName) {
//        this.trueName = trueName;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public Integer getState() {
//        return state;
//    }
//
//    public void setState(Integer state) {
//        this.state = state;
//    }
//
//    public Set<String> getFunSet() {
//        return funSet;
//    }
//
//    public void setFunSet(Set<String> funSet) {
//        this.funSet = funSet;
//    }
//}
