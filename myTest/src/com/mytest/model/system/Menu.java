package com.mytest.model.system;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.mytest.model.base.system.BaseMenu;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Menu extends BaseMenu<Menu> {
	public static final Menu dao = new Menu();
	private static final String TABLENAME = "admin_menu";

	private List<Menu> childList;		//下属子菜单

//	public List<Menu> getAllMenuList(Integer userId) {
//		List<Menu> list = getChildMenuList(userId, 0);
//		List<Menu> menuList = new ArrayList<Menu>();
//		for (Menu menu : list) {
//			if(menu.getIsChild() == 1){
//				menu.setChildList(subList(menu, userId));
//			}
//			menuList.add(menu);
//		}
//		return menuList;
//	}
//
//	private List<Menu> subList(Menu menuF, Integer userId) {
//		List<Menu> chlidList = getChildMenuList(userId, menuF.getId());
//		List<Menu> menuList = new ArrayList<Menu>();
//		if (chlidList.size() > 0) {
//			for (Menu menu : chlidList) {
//				if(menu.getIsChild() == 1){
//					menu.setChildList(subList(menu, userId));
//				}
//				menuList.add(menu);
//			}
//		}
//		return menuList;
//	}
	
	/**
	 * 按层级获取所有菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> getAllMenuList(Integer userId) {
		List<Menu> list = getChildMenuList(userId, 0);
		for (Menu menu : list) {
			if(menu.getIsChild() == 1){
				menu = subList(menu, userId);
			}
		}
		return list;
	}
	
	/**
	 * 多层迭代获取子菜单
	 * @param userId
	 * @param parentId
	 * @return
	 */
	private Menu subList(Menu menuF, Integer userId) {
		List<Menu> chlidList = getChildMenuList(userId, menuF.getId());
		if (chlidList.size() > 0) {
			for (Menu menu : chlidList) {
				if(menu.getIsChild() == 1){
					menu = subList(menu, userId);
				}
			}
		}
		menuF.setChildList(chlidList);
		return menuF;
	}
	
	/**
	 * 获取子菜单列表
	 * @param userId
	 * @param parentId
	 * @return
	 */
	public List<Menu> getChildMenuList(Integer userId, Integer parentId) {
		List<Menu> list = new ArrayList<Menu>();
		String sql = "select * from " + TABLENAME +" as am left join user_menu um on um.menu_id=am.id"
				+ " where um.user_id=" + userId + " and um.status=0 and am.parent_id=? order by `order` asc";
		if(userId == 1){
			sql = "select * from " + TABLENAME
						+ " as am where am.status=0 and am.parent_id=? order by `order` asc";
		}
		list = dao.find(sql, parentId);
		return list;
	}
	
	/**
	 * 强制删除菜单并删除相关关联
	 * @param id
	 * @return
	 */
	public boolean delpPysically(Integer id){
		try {
			Db.update("delete from user_menu where menu_id=?",id);
			Menu menu = dao.findById(id);
			menu.delete();
			int fatherId = menu.getParentId();
			if(fatherId != 0){
				if(Db.queryLong("select count(1) from " + TABLENAME + " where parent_id=?",fatherId) == 0){
					Db.update("update " + TABLENAME + " set is_child=0 where id=?",fatherId);
				}
				return true;
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int deleMenu(String ids){
		return Db.update("update " + TABLENAME + " set status=1 where id in(" + ids + ")");
	}
	
	public boolean checkMenu(String name,String eName,Integer id){
		String sql = "select count(1) from " + TABLENAME + " where 1=1 and (name=? or e_name=?) ";
		if(id != null){
			sql += " and id<>" + id;
		}
		return Db.queryLong(sql,name,eName) == 0;
	}
	
	public List<Menu> getAllMenu(){
		return dao.find("select * from " + TABLENAME);
	}

	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}
}