package cn.itcasttax.dao;

import java.io.Serializable;

import cn.itcasttax.entity.Person;

public interface ITestDao {
public void save(Person person);
	
	public Person findPersonById(Serializable id);

}
