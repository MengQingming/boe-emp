package com.boe.sysmgr.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.boe.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜单Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Menu extends DataEntity<Menu> {

	private static final long serialVersionUID = 1L;
	
	private Menu parent;	// 父级菜单
	private String parentIds; // 所有父级编号
	private String appNo;//APP_NO appNo app_no  应用NO
	private String menuNo;//MENU_NO menuNo menu_no  菜单编号NO
	private String menuName;//MENU_NAME menuName menu_name 菜单名称
	private String menuType;//MENU_TYPE menuType menu_type 菜单类型
	private String menuPath;//MENU_PATH menuPath menu_path 菜单路径
	private String permission;//PERMISSION permission 授权码
	private String icon;//ICON icon 图标
	private String url;//URL url 菜单链接
	private String target;//TARGET target 打开连接方式（ mainFrame、_blank、_self、_parent、_top）
	private String displayFlag;//DISPLAY_FLAG displayFlag display_flag 是否显示标志
	private Integer displayOrder;//DISPLAY_ORDER displayOrder display_order 显示顺序
	private Integer layer;//LAYER layer 菜单层次
	private String hasChild;//HAS_CHILD hasChild has_child 是否存在子菜单
	private String tooltip;//TOOLTIP toolTip tooltip 提示信息
	private String remarks;//REMARKS remarks 菜单说明
	private String status;//STATUS status 菜单状态
	private String delFlag;//DEL_FLAG delFlag del_flag 删除标志
	private String attribute1;//attribute1 attribute1 属性1
	private String attribute2;//attribute2 attribute2 属性2
	private String attribute3;//attribute3 attribute3 属性3
	private String attribute4;//attribute4 attribute4 属性4
	private String attribute5;//attribute5 attribute5属性5
	private String attribute6;//attribute6 attribute6 属性6
	private String attribute7;//attribute7 attribute7 属性7
	private String attribute8;//attribute8 attribute8 属性8
	private String attribute9;//attribute9 attribute9 属性9
	private String attribute10;//attribute10 attribute10 属性10
	private Date creationDate;//CREATION_DATE creationDate creation_date 创建日期
	private Date lastUpdateDate;//LAST_UPDATE_DATE lastUpdateDate last_update_date 最后更新日期
	
	private Integer userId;
	public Menu(){
		super();
		this.displayOrder = 30;
		this.displayFlag = "1";
	}
	
	public Menu(Integer id){
		super(id);
	}

	@JsonBackReference
	@NotNull
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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

	@Length(min=0, max=20)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Length(min=0, max=100)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Length(min=0, max=200)
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : 0;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@JsonIgnore
	public static void sortList(List<Menu> list, List<Menu> sourcelist, Integer parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParent()!=null && child.getParent().getId()!=null
								&& child.getParent().getId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

	@JsonIgnore
	public static String getRootId(){
		return "1";
	}

	@Override
	public String toString() {
		return menuName;
	}
}