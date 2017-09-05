package com.boe.sysmgr.entity;

import com.google.common.collect.Lists;
import com.boe.common.config.Global;
import com.boe.common.persistence.DataEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 角色Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class Role extends DataEntity<Role> {
	
	private static final long serialVersionUID = 1L;
	private Group group;//组织机构
	private Company company;//
	private String roleName;//ROLE_NAME 	role_name roleName 角色名
	private String roleNo;//ROLE_NO role_no roleNo 角色编号
	private Integer displayOrder;//DISPLAY_ORDER display_order displayOrder 显示顺序
	private String roleType;//ROLE_TYPE role_type roleType 角色类型
	private String dataScope;//DATA_SCOPE data_scope dataScope 数据范围
	private String status;//STATUS status 角色状态
	private String attribute1;//attribute1  attribute1 属性1
	private String attribute2;//attribute2  attribute2 属性2
	private String attribute3;//attribute3  attribute3 属性3
	private String attribute4;//attribute4  attribute4 属性4
	private String attribute5;//attribute5  attribute5 属性5
	private String attribute6;//attribute6  attribute6 属性6
	private String attribute7;//attribute7  attribute7 属性7
	private String attribute8;//attribute8  attribute8 属性8
	private String attribute9;//attribute9  attribute9 属性9
	private String attribute10;//attribute10 attribute10 属性10
	private String homePage;//HOME_PAGE home_page homePage 首页
	private Date creationDate;//CREATION_DATE creation_date creationDate 创建日期
	private Date lastUpdateDate;//LAST_UPDATE_DATE last_update_date lastUpdateDate 最后更新日期
	
	private Integer roleMenuId;
	
	private User user;		// 根据用户ID查询角色列表

//	private List<User> userList = Lists.newArrayList(); // 拥有用户列表
	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表
	private List<Group> groupList = Lists.newArrayList(); // 按明细设置数据范围

	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	public static final String DATA_SCOPE_ALL = "1";
	public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";
	public static final String DATA_SCOPE_COMPANY = "3";
	public static final String DATA_SCOPE_OFFICE_AND_CHILD = "4";
	public static final String DATA_SCOPE_OFFICE = "5";
	public static final String DATA_SCOPE_SELF = "8";
	public static final String DATA_SCOPE_CUSTOM = "9";

	
	public Role() {
		super();
		this.dataScope = DATA_SCOPE_SELF;
		this.status=Global.YES;
	}
	
	public Role(Integer id){
		super(id);
	}
	
	public Role(User user) {
		this();
		this.user = user;
		// 修复 Role 查询时候 currentUser = null 查询超时错误
		this.currentUser = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
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

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Length(min=1, max=100)
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Integer> getMenuIdList() {
		List<Integer> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		menuList = Lists.newArrayList();
		for (String menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(Integer.parseInt(menuId));
			menuList.add(menu);
		}
	}

	public String getMenuIds() {
		return StringUtils.join(getMenuIdList(), ",");
	}
	
	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null){
			String[] ids = StringUtils.split(menuIds, ",");
			setMenuIdList(Lists.newArrayList(ids));
		}
	}
	
	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public List<Integer> getGroupIdList() {
		List<Integer> groupIdList = Lists.newArrayList();
		for (Group group : groupList) {
			groupIdList.add(group.getId());
		}
		return groupIdList;
	}

	public void setGroupIdList(List<String> groupIdList) {
		groupList = Lists.newArrayList();
		for (String groupId : groupIdList) {
			Group group = new Group();
			group.setId(Integer.parseInt(groupId));
			groupList.add(group);
		}
	}

	public String getGroupIds() {
		return StringUtils.join(getGroupIdList(), ",");
	}
	
	public void setGroupIds(String groupIds) {
		groupList = Lists.newArrayList();
		if (groupIds != null){
			String[] ids = StringUtils.split(groupIds, ",");
			setGroupIdList(Lists.newArrayList(ids));
		}
	}
	
	/**
	 * 获取权限字符串列表
	 */
	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (Menu menu : menuList) {
			if (menu.getPermission()!=null && !"".equals(menu.getPermission())){
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}

	public User getuser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Integer roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

//	public boolean isAdmin(){
//		return isAdmin(this.id);
//	}
//	
//	public static boolean isAdmin(String id){
//		return id != null && "1".equals(id);
//	}
	
//	@Transient
//	public String getMenuNames() {
//		List<String> menuNameList = Lists.newArrayList();
//		for (Menu menu : menuList) {
//			menuNameList.add(menu.getName());
//		}
//		return StringUtils.join(menuNameList, ",");
//	}
}
