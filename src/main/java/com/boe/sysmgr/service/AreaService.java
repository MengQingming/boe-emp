package com.boe.sysmgr.service;

import com.boe.sysmgr.entity.Area;
import com.boe.common.service.TreeService;
import com.boe.sysmgr.dao.AreaDao;
import com.boe.sysmgr.entity.User;
import com.boe.sysmgr.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(String parentId){
		Area area = new Area();
		//当前登录用户
		User u = UserUtils.getUser();
		if(!u.isAdmin()){
			area.setCompanyId(u.getCompany().getId());
		}
		Area area2 = new Area();
		area2.setId(Integer.parseInt(parentId));
		area.setParent(area2);
		return UserUtils.getAreaList(area);
	}

	@Transactional(readOnly = false)
	public void saveOrUpdateArea(Area area) {
		//获取当前登录用户
		User u = UserUtils.getUser();
		
		area.setCreateBy(u);
		area.setUpdateBy(u);
		area.setCreateDate(new Date());
		area.setUpdateDate(new Date());
		//查询当前父节点
		Area parent = dao.get(area.getParent().getId());
		if(area.getId()!=null){
			//当前节点
			Area areaOld = dao.get(area.getId());
			//查询本身父节点
			Area parentItself = dao.get(areaOld.getParent().getId());
			area.setPath(areaOld.getPath().replace(parentItself.getPath(), parent.getPath()));
			area.setUpdateDate(new Date());
			//attribute1
			//区县
			if(areaOld.getType().equals("4")){
				area.setFullname(parentItself.getName()+"-"+areaOld.getName());
			}
			if(parent.getId()!=parentItself.getId()){
				//查询所有子节点更新path
				Area areachild = new Area();
				areachild.setParent(area);
				List<Area> children = dao.findListByPrentId(areachild);
				if(children!=null && children.size()>0){
					for(int j=0;j<children.size();j++){
						children.get(j).setPath(children.get(j).getPath().replace(parentItself.getPath(), parent.getPath()));
						children.get(j).setUpdateDate(new Date());
						System.out.println(children.get(j).getPath());
						super.save(children.get(j));
						areachild.setParent(children.get(j));
						List<Area> nodes = dao.findListByPrentId(areachild);
						if(nodes!=null && nodes.size()>0){
							List<Area> node = recursion(nodes,children.get(j),parent,parentItself,areachild);
							if(node==null){
								continue;
							}
						}
					}
				}
			}
		}else{

			//查询本身父节点
			Area parentItself = dao.get(area.getParent().getId());
			if(area.getType().equals("4")){
				area.setFullname(parentItself.getName()+"-"+area.getName());
			}
			StringBuffer path = new StringBuffer("");
			if(area.getParent()!=null){
				path = path.append(parent.getPath()).append("-");
			}else{
				path = path.append("");
			}
			area.setPath(path.toString());
		}
		
		super.save(area);
	}
	
	private List<Area> recursion(List<Area> children,Area area, Area parent, Area parentItself, Area areachild) {
//		area.setPath(area.getPath().replace(parentItself.getPath(), parent.getPath()));
//		System.out.println(area.getPath());
//		super.save(area);
		areachild.setParent(area);
		List<Area> nodes = dao.findListByPrentId(areachild);
		if(nodes!=null && nodes.size()>0){
			for(int i=0;i<nodes.size();i++){
				nodes.get(i).setPath(nodes.get(i).getPath().replace(parentItself.getPath(), parent.getPath()));
				nodes.get(i).setUpdateDate(new Date());
				System.out.println(nodes.get(i).getPath());
				super.save(nodes.get(i));
				areachild.setParent(nodes.get(i));
				List<Area> nodes1 = dao.findListByPrentId(areachild);
				if(nodes1!=null && nodes1.size()>0){
					recursion(nodes1,nodes.get(i), parent, parentItself, areachild);
				}else{
					continue;
				}
			}
		}else{
			return null;
		}
		return null;
	}

	@Transactional(readOnly = false)
	public void deleteArea(Area area) {
		super.delete(area);
	}

	public List<Area> findListByPrentId(Area area2) {
		return dao.findListByPrentId(area2);
	}

	public List<Area> findListByTreeBox() {
		Area area = new Area();
		//当前登录用户
		User u = UserUtils.getUser();
		if(!u.isAdmin()){
			area.setCompanyId(u.getCompany().getId());
		}
		return dao.findListByTreeBox(area);
	}

    /**
     * 通过 城市code 查询 城市信息
     * @param code
     * @param companyId
     * @return
     */
	public Area findByAreaCode(String code, Integer companyId){
	    return dao.findByAreaCode(code, companyId);
    }

    public Area getAreaByName(String areaName) {
		List<Area> list = dao.getAreaByName(areaName);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
    }

    public Area getTopArea(Integer companyId) {

		return dao.getTopArea(companyId);
    }
}
