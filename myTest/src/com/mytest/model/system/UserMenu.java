package com.mytest.model.system;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.mytest.model.base.system.BaseUserMenu;
import com.mytest.util.StringUtil;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class UserMenu extends BaseUserMenu<UserMenu> {
	public static final UserMenu dao = new UserMenu();
	private final String TABLENAME = "user_menu";

	public UserMenu() {

	}

	public UserMenu(Integer userId, Integer menuId, Integer add, Integer edit, Integer delete) {
		this.setUserId(userId);
		this.setMenuId(menuId);
		this.setAdd(add);
		this.setEdit(edit);
		this.setDelete(delete);
	}

	/**
	 * 根据用户或者菜单号删除关联
	 * @param userId
	 * @param menuId
	 * @return
	 */
	public int deleteUserMenu(String userId, String menuId) {
		String sql = "update " + TABLENAME + " set status=1 where 1=1";
		if(!StringUtil.isEmptyStr(userId)){
			sql += " and user_id in (" + userId + ")";
		}
		if(!StringUtil.isEmptyStr(menuId)){
			sql += " and menu_id in (" + menuId + ")";
		}
	return Db.update(sql);
	}
	
	public int[] doUserMenuByUserId(Integer userId){
		List<UserMenu> list = new ArrayList<UserMenu>();
		List<Menu> menus = Menu.dao.getAllMenu();
		for(Menu menu : menus){
			UserMenu um = new UserMenu();
			um.setUserId(userId);
			um.setMenuId(menu.getId());
			list.add(um);
		}
		return doUserMenu(list);
	}
	
	public int[] doUserMenuByMenuId(Integer menuId){
		List<UserMenu> list = new ArrayList<UserMenu>();
		List<User> users = User.dao.getAllUser();
		for(User user : users){
			UserMenu um = new UserMenu();
			um.setUserId(user.getId());
			um.setMenuId(menuId);
//			record.set("userId", user.getId());
//			record.set("menuId", menuId);
			list.add(um);
		}
		return doUserMenu(list);
	}
	
	public int[] doUserMenu(List<UserMenu> list){
//		Db.batchSave(modelList, batchSize)
		return Db.batchSave(list, list.size());
	}
}