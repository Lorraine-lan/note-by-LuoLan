package com.hgsf.supermarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 超市用户实体类，对应数据库表smbms_user
 */
@TableName("smbms_user")
public class User {
    // 主键ID，自增策略
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 用户编码
    private String userCode;
    // 用户名
    private String userName;
    // 用户密码
    private String userPassword;
    // 密码盐值
    private String salt;
    // 状态（0-禁用，1-启用等）
    private Integer status;
    // 性别（0-女，1-男等）
    //将服务端的日期对象数据响应给客户端时转换为字符串格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    //将客户端请求的日期字符串转换为Date对象
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Integer gender;
    // 生日
    private Date birthday;
    // 电话
    private String phone;
    // 地址
    private String address;
    // 头像URL
    private String headUrl;
    // 用户角色（关联角色表）
    private Integer userRole;
    // 创建人ID
    private Long createdBy;
    // 创建时间
    private Date creationDate;
    // 修改人ID
    private Long modifyBy;
    // 修改时间
    private Date modifyDate;

    // 在 User 类中添加以下字段和方法（不映射到数据库）
    @TableField(exist = false)
    private String statusText;

    @TableField(exist = false)
    private String genderText;

    // 定义权限对象作为属性（不映射到数据库）
    @TableField(exist = false)
    private Role role;

    /**
     * 无参构造方法（MyBatis-Plus等ORM框架必须）
     */
    public User() {
    }

    /**
     * 全参构造方法（实用！适用于需要初始化所有字段的场景）
     */
    public User(Long id, String userCode, String userName, String userPassword, String salt, Integer status,
                Integer gender, Date birthday, String phone, String address, String headUrl, Integer userRole,
                Long createdBy, Date creationDate, Long modifyBy, Date modifyDate) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.salt = salt;
        this.status = status;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.headUrl = headUrl;
        this.userRole = userRole;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getGenderText() {
        return genderText;
    }

    public void setGenderText(String genderText) {
        this.genderText = genderText;
    }

    // 所有字段的Getter和Setter方法（保持不变）
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * toString方法，方便日志打印和调试
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", salt='" + salt + '\'' +
                ", status=" + status +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", userRole=" + userRole +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                '}';
    }
    public void setPassword(String newPwd) {
    }

    public void getPassword() {
    }

}