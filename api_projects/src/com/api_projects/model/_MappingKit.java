package com.api_projects.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
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
		arp.addMapping("api_admin", "id", Admin.class);
		arp.addMapping("api_api_in_out", "id", ApiInOut.class);
		arp.addMapping("api_api_info", "id", ApiInfo.class);
		arp.addMapping("api_projects", "id", Projects.class);
	}
}
