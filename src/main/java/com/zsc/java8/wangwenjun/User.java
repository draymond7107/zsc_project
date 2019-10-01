package com.zsc.java8.wangwenjun;

import lombok.Data;

import java.util.Date;

/**
 * 用户表
 * @author lujun
 */
@Data
public class User  {
	private static final long serialVersionUID = 1L;
	/**ID**/
	private Integer id;
	/**用户类别**/
	private Integer kind;
	/**所属班级**/
	private Integer classId;
	/**用户姓名**/
	private String realName;
	/**用户昵称**/
	private String nickName;
	/**手机号码**/
	private String mobile;
	/**性别**/
	private String sex;
	/**钉钉部门ID**/
	private String ddDmId;
	/**钉钉部门集合**/
	private String ddDmArr;
	/**open_id**/
	private String ddOpenId;
	/**union_id**/
	private String ddUnionId;
	/**描述信息**/
	private String description;
	/**人脸图片地址**/
	private String faceUrl;
	/**建卡状态**/
	private Integer cardStatus;
	/**人脸状态**/
	private Integer faceStatus;
	/**最后进出时间**/
	private Date lastInoutTime;
	/**是否管理员**/
	private Integer isAdmin;
	/**状态**/
	private Integer status;
	/**创建时间**/
	private Date createTime;
	/**更新时间**/
	private Date updateTime;
	public User() {}
}