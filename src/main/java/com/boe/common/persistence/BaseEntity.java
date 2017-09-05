package com.boe.common.persistence;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.boe.common.supcan.annotation.treelist.SupTreeList;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.boe.common.config.Global;
import com.boe.common.supcan.annotation.treelist.cols.SupCol;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.utils.UserUtils;

/**
 * Entity支持类
 * @author cyg
 * @version 2016-11-16
 */
@SupTreeList
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	//protected String id;
	protected Integer id;
	
	/**
	 * 当前用户
	 */
	protected User currentUser;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;
	
	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;
	
	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;
	
	/**
	 * 删除标记（0：删除；1：正常；2：审核；） 
	 */
	public static final String DEL_FLAG_DELETE = "0";
	public static final String DEL_FLAG_NORMAL = "1";
	public static final String DEL_FLAG_AUDIT = "2";	

	/**
	 * 0-草稿，1-发送，2-处理中，3-处理完成
	 */
	public static final String FORM_STATUS_DRAFT = "0";
	public static final String FORM_STATUS_SEND = "1";	
	public static final String FORM_STATUS_ING = "2";	
	public static final String FORM_STATUS_DONE = "3";	
	
	public BaseEntity() {
		
	}
	
	public BaseEntity(Integer id) {
		this();
		this.id = id;
	}

	@SupCol(isUnique="true", isHide="true")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		if(currentUser == null){
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@JsonIgnore
	@XmlTransient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	
    /**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     * @return
     */
	public boolean getIsNewRecord() {
		//return isNewRecord || StringUtils.isBlank(getId());
        if(null == getId()){
        	isNewRecord = true ;
        }
		return isNewRecord;
		
		
    }

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	/**
	 * 全局变量对象
	 */
	@JsonIgnore
	public Global getGlobal() {
		return Global.getInstance();
	}
	
	/**
	 * 获取数据库名称
	 */
	@JsonIgnore
	public String getDbName(){
		return Global.getConfig("jdbc.type");
	}
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    

}
