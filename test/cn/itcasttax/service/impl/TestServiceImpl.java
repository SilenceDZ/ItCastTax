package cn.itcasttax.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcasttax.dao.ITestDao;
import cn.itcasttax.entity.Person;
import cn.itcasttax.service.ITestService;

@Service("testService")
public class TestServiceImpl implements ITestService{
	@Autowired
	private ITestDao testDao;
	
	@Override
	public void say(){
		System.out.println("SpringIocTest.say()");
	}

	@Override
	public void save(Person person) {
		testDao.save(person);		
	}

	@Override
	public Person findPersonById(Serializable id) {
		return testDao.findPersonById(id);
	}
}
