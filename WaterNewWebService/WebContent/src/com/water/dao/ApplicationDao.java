package com.water.dao;

import com.water.bean.AppRegBean;
import com.water.model.Application;

public interface  ApplicationDao {
	
	Application appRegister(AppRegBean appRegBean);
	public Application saveApplication(Application application);

}
