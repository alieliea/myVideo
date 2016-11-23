package com.api_projects.model.base;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings({ "serial" })
public abstract class BaseModel<M extends BaseModel<M>>  extends Model<M> {

	public boolean saveOrUpdate(){
		if(this.get("id") != null){
			return this.update();
		}else{
			return this.save();
		}
	}
}