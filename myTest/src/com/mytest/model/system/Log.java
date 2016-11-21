package com.mytest.model.system;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.plugin.activerecord.Page;
import com.mytest.model.base.system.BaseLog;
import com.mytest.util.CMDUtil;

@SuppressWarnings("serial")
public class Log extends BaseLog<Log> {
	public static final Log dao = new Log();
	private final String TABLENAME = "admin_log";

	public boolean saveLog(int userId, String username, HttpServletRequest request, String detail){
		Log log = new Log();
		log.setUserId(userId);
		log.setUsername(username);
		String ip = request.getRemoteAddr();
		String mac = "";
		String browser = request.getHeader("user-agent");
		try {
			mac =  CMDUtil.getMacAddress(request.getRemoteHost());
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.setMac(mac);
		log.setIp(ip);
		log.setBrowser(browser);
		log.setDetail(detail);
		return log.save();
	}

	/**
	 * 获取日志分页
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<Log> getPages(int page, int rows) {
		String select = "select * ";
		String sql = " from " + TABLENAME;
		return dao.paginate(page, rows, select, sql);
	}
}