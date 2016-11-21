package com.mytest.action.system.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.mytest.common.StaticObject;
import com.mytest.interceptor.LoginInterceptor;
import com.mytest.model.system.Menu;
import com.mytest.model.system.UserMenu;

@Before(LoginInterceptor.class)
public class MenuAction extends Controller {

	public void index() {
		int userId = getSessionAttr(StaticObject.LOGINID);
		List<Menu> list = Menu.dao.getChildMenuList(userId, 0);
		setAttr("list", list);
		render("menu.jsp");
	}
	
	public void childMenu(){
		int parentId = getParaToInt("id",0);
		int userId = getSessionAttr(StaticObject.LOGINID);
		List<Menu> list = Menu.dao.getChildMenuList(userId, parentId);
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("menuList", list);
		renderJson(obj);
	}
	
	/**
	 * 新增菜单添加关联
	 */
	public void saveMenu(){
		Map<String, Object> obj = new HashMap<String, Object>();
		Menu menu = getModel(Menu.class);
		Integer id = menu.getId();
		if(Menu.dao.checkMenu(menu.getName(), menu.getEName(), id)){
			obj.put("success", menu.saveOrUpdate());
			if(id == null){
				UserMenu.dao.doUserMenuByMenuId(menu.getId());
			}
		}else{
			obj.put("success", false);
			obj.put("msg", "菜单名重复！");
		}
		renderJson(obj);
	}
	
	public void menuInfo(){
		int id = getParaToInt("id",0);
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("menuInfo", Menu.dao.findById(id));
		renderJson(obj);
	}
	
//	public void checkMenu(){
//		Map<String, Object> obj = new HashMap<String, Object>();
//		String name = getPara("name","");
//		String eName = getPara("eName","");
//		obj.put("success", Menu.dao.checkMenu(name, eName));
//		renderJson(obj);
//	}
	
	/**
	 * 删除菜单解除关联
	 */
	public void delMenu(){
		Map<String, Object> obj = new HashMap<String, Object>();
		String id = getPara("id");
		obj.put("success", Menu.dao.deleMenu(id));
		UserMenu.dao.deleteUserMenu(null, id);
		renderJson(obj);
	}
}