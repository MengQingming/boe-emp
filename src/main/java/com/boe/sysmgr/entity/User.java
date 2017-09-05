package com.boe.sysmgr.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.boe.common.config.Global;
import com.boe.common.persistence.DataEntity;
import com.boe.common.supcan.annotation.treelist.cols.SupCol;
import com.boe.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.boe.common.utils.Collections3;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class User extends DataEntity<User> {
	private static final long serialVersionUID = 1L;
	
	private Integer parentId;			//上级ID
	private String parentNum;			//上级编号
	private String parentFullName;		//上级名称
	private Group group;				//用户全职机构
	private Integer groupId;			//数据导入导出时组织机构ID
	private Company company;
	private String userName;			//USER_NAME 用户登录名
	private String userNum;				//USER_NUM 员工编号
	private String fullName;			//FULL_NAME	用户全名
	private String rankCode;			//RANK_CODE	职级代码
	private String rankName;			//RANK_CODE	职级代码名称
	private String password;			//PASSWORD	密码(加密后)
	private Date lastPasswordDate;		//LAST_PASSWORD_DATE	上次修改密码日期
	private String photo;				//PHOTO 照片
	private String email;				//EMAIL 电子邮箱
	private String phone;				//PHONE 办公电话
	private String mobile;				//MOBILE 手机
	private String fax;					//FAX 传真
	private Integer displayOrder;		//DISPLAY_ORDER 显示顺序
	private String status;				//STATUS 用户状态
	private Date startDate;				//START_DATE 开始日期
	private Date endDate;				//END_DATE 结束日期
	private String lang;				//LANG 语言
	private String remarks;				//REMARKS 备注
	private String attribute1;			//attribute1 属性1
	private String attribute2;			//attribute2 属性2
	private String attribute3;			//attribute3 属性3
	private String attribute4;			//attribute4 属性4
	private String attribute5;			//attribute5 属性5
	private String attribute6;			//attribute6 属性6
	private String attribute7;			//attribute7 属性7
	private String attribute8;			//attribute8 属性8
	private String attribute9;			//attribute9 属性9
	private String attribute10;			//attribute10 属性10
	private String lastLoginIp;			//LAST_LOGIN_IP 上次登录IP
	private Date lastLoginDate;			//LAST_LOGIN_DATE 上次登录时间
	private String dataSource;			//DATA_SOURCE 数据来源
	private String dataSourceKey;		//DATA_SOURCE_KEY 数据来源业务主键
	private Date lastSyncDate;			//LAST_SYNC_DATE 最后同步日期
	private Date creationDate;			//CREATION_DATE 创建日期
	private Date lastUpdateDate;		//LAST_UPDATE_DATE 最后更新日期
	private Integer userRoleId;
	private String homeCityCode;		//HOME_CITY_CODE 家庭所在地城市编码
	private String homeCityName;		//HOME_CITY_NAME 家庭所在地城市名称
	private String workingCityCode;		//WORKING_CITY_CODE 工作所在地城市编码
	private String workingCityName;		//WORKING_CITY_NAME 工作所在地城市名称
	private String postedCityCode;		//POSTED_CITY_CODE 派驻地城市编码
	private String postedCityName;		//POSTED_CITY_NAME 派驻地城市名称
	private String positionCode;		//岗位编号
	private String positionName;		//岗位名称
	private String userTypeCode;		//用户类型代码
	private String userTypeName;		//用户类型名称
	private String allowanceLevelCode;	//补助级别代码
	private String allowanceLevelName;	//补助级别名称

	private Role role;					// 根据角色查询用户条件
	private String groupIds;
	
	private List<Role> roleList = Lists.newArrayList(); 	// 拥有角色列表
	private List<Group> groupList = Lists.newArrayList(); 	// 拥有角色列表
	
	public User() {
		super();
		this.delFlag = Global.NO;
	}
	
	public User(Integer id){
		super(id);
	}

	public User(Integer id, String userName){
		super(id);
		this.userName = userName;
	}

	public User(Role role){
		super();
		this.role = role;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getUserTypeCode() {
		return userTypeCode;
	}

	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getAllowanceLevelCode() {
		return allowanceLevelCode;
	}

	public void setAllowanceLevelCode(String allowanceLevelCode) {
		this.allowanceLevelCode = allowanceLevelCode;
	}

	public String getAllowanceLevelName() {
		return allowanceLevelName;
	}

	public void setAllowanceLevelName(String allowanceLevelName) {
		this.allowanceLevelName = allowanceLevelName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentNum() {
		return parentNum;
	}

	public void setParentNum(String parentNum) {
		this.parentNum = parentNum;
	}

	public String getParentFullName() {
		return parentFullName;
	}

	public void setParentFullName(String parentFullName) {
		this.parentFullName = parentFullName;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@ExcelField(title="组织机构ID(必填)",align=2, sort=21)
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@SupCol(isUnique="true", isHide="true")
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public Integer getId() {
		if (id == null) {
			return 0;
		}
		return id;
	}
	

	@NotNull(message="用户登录名不能为空")
	@ExcelField(title="登录名(必填)", align=2, sort=20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ExcelField(title="员工编号(必填)", align=2, sort=25)
	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@ExcelField(title="姓名(必填)", align=2, sort=30)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRankCode() {
		return rankCode;
	}

	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastPasswordDate() {
		return lastPasswordDate;
	}

	public void setLastPasswordDate(Date lastPasswordDate) {
		this.lastPasswordDate = lastPasswordDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Email(message="邮箱格式不正确")
	@Length(min=0, max=200, message="邮箱长度必须介于 1 和 200 之间")
	@ExcelField(title="邮箱", align=1, sort=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ExcelField(title="传真", align=1, sort=55)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@NotNull(message="语言不能为空")
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastSyncDate() {
	    if (lastSyncDate == null)
	        return new Date();
		return lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	@NotNull(message="创建日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@NotNull(message="最后更新日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@JsonIgnore
	@NotNull(message="用户登录密码不能为空")
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Length(min=0, max=200, message="电话长度必须介于 1 和 200 之间")
	@ExcelField(title="电话", align=2, sort=60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=200, message="手机长度必须介于 1 和 200 之间")
	@ExcelField(title="手机", align=2, sort=70)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ExcelField(title="备注", align=1, sort=900)
	public String getRemarks() {
		return remarks;
	}
	

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	
	public String getHomeCityCode() {
		return homeCityCode;
	}

	public void setHomeCityCode(String homeCityCode) {
		this.homeCityCode = homeCityCode;
	}

	public String getHomeCityName() {
		return homeCityName;
	}

	public void setHomeCityName(String homeCityName) {
		this.homeCityName = homeCityName;
	}

	public String getWorkingCityCode() {
		return workingCityCode;
	}

	public void setWorkingCityCode(String workingCityCode) {
		this.workingCityCode = workingCityCode;
	}

	public String getWorkingCityName() {
		return workingCityName;
	}

	public void setWorkingCityName(String workingCityName) {
		this.workingCityName = workingCityName;
	}

	public String getPostedCityCode() {
		return postedCityCode;
	}

	public void setPostedCityCode(String postedCityCode) {
		this.postedCityCode = postedCityCode;
	}

	public String getPostedCityName() {
		return postedCityName;
	}

	public void setPostedCityName(String postedCityName) {
		this.postedCityName = postedCityName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@JsonIgnore
	public List<Integer> getRoleIdList() {
		List<Integer> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		roleList = Lists.newArrayList();
		for (Integer roleId : roleIdList) {
			Role role = new Role();
			role.setId(roleId);
			roleList.add(role);
		}
	}
	
	@JsonIgnore
	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	
	@JsonIgnore
	public List<Integer> getGroupIdList() {
		List<Integer> roleIdList = Lists.newArrayList();
		for (Group group : groupList) {
			roleIdList.add(group.getId());
		}
		return roleIdList;
	}

	public void setGroupIdList(List<Integer> groupIdList) {
		groupList = Lists.newArrayList();
		for (Integer groupId : groupIdList) {
			Group group = new Group();
			group.setId(groupId);
			groupList.add(group);
		}
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	
	public String getGroupIds() {
		return this.groupIds;
	}
	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "roleName", ",");
	}
	
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(Integer id){
		return id != null &&  id.equals(1);
	}

	@Override
	public String toString() {
		return "User [group=" + group + ", userName=" + userName + ", userNum="
				+ userNum + ", fullName=" + fullName + ", rankCode=" + rankCode
				+ ", password=" + password + ", lastPasswordDate="
				+ lastPasswordDate + ", photo=" + photo + ", email=" + email
				+ ", phone=" + phone + ", mobile=" + mobile + ", fax=" + fax
				+ ", displayOrder=" + displayOrder + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", lang=" + lang + ", remarks=" + remarks + ", attribute1="
				+ attribute1 + ", attribute2=" + attribute2 + ", attribute3="
				+ attribute3 + ", attribute4=" + attribute4 + ", attribute5="
				+ attribute5 + ", attribute6=" + attribute6 + ", attribute7="
				+ attribute7 + ", attribute8=" + attribute8 + ", attribute9="
				+ attribute9 + ", attribute10=" + attribute10
				+ ", lastLoginIp=" + lastLoginIp + ", lastLoginDate="
				+ lastLoginDate + ", dataSource=" + dataSource
				+ ", dataSourceKey=" + dataSourceKey + ", lastSyncDate="
				+ lastSyncDate + ", creationDate=" + creationDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", role=" + role
				+ ", roleList=" + roleList + "]";
	}
}