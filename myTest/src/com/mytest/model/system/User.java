package com.mytest.model.system;

import java.util.List;

import com.mytest.model.base.system.BaseUser;

@SuppressWarnings("serial")
public class User extends BaseUser<User>{
	
	public static final User dao = new User();
	private final String TABLENAME = "admin_user";
	
	private int verdict = 0;//0正常，1密码错误
	
	/**
	 * 验证登录，不存在/密码错误
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkLogin(String username,String password){
		User user = dao.findFirst("select * from " + TABLENAME + " where username=? and userpass=?",username,password);
		if(user == null){
			user = dao.findFirst("select * from " + TABLENAME + " where username=?",username);
			if(user != null){
				user.setVerdict(1);
			}
		}
		return user;
	}
	
	/**
	 * 验证重名
	 * @param username
	 * @return
	 */
	public boolean checkJuna(String username){
		User user = dao.findFirst("select * from " + TABLENAME + " where username=?",username);
		if(user != null){
			return false;
		}else{
			return true;
		}
	}
	
	public List<User> getAllUser(){
		return dao.find("select * from " + TABLENAME + " where id<>1");
	}
	

	public int getVerdict() {
		return verdict;
	}

	public void setVerdict(int verdict) {
		this.verdict = verdict;
	}
}