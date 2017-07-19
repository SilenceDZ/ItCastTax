package cn.itcasttax.service;

import java.io.Serializable;

import cn.itcasttax.entity.Person;

public interface ITestService {
	public void say();
	
	public void save(Person person);
	
	public Person findPersonById(Serializable id);
}
