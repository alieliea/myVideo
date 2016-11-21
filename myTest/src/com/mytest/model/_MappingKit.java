package com.mytest.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.mytest.model.web.Categories;
import com.mytest.model.system.Log;
import com.mytest.model.system.Menu;
import com.mytest.model.system.SystemIcon;
import com.mytest.model.system.User;
import com.mytest.model.system.UserMenu;
import com.mytest.model.web.Files;
import com.mytest.model.web.News;

/**
 * Generated by JFinal, do not modify this file.
 * 
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("admin_user", "id", User.class);
		arp.addMapping("admin_log", "id", Log.class);
		arp.addMapping("admin_menu", "id", Menu.class);
		arp.addMapping("user_menu", UserMenu.class);
		arp.addMapping("news", "id", News.class);
		arp.addMapping("files", "id", Files.class);
		arp.addMapping("categories", "id", Categories.class);
		arp.addMapping("system_icon", "id", SystemIcon.class);
	}
}
