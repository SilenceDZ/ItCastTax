package cn.itcasttax.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.itcasttax.dao.ITestDao;
import cn.itcasttax.entity.Person;

@Repository
public class TestDaoImpl extends HibernateDaoSupport implements ITestDao{
	
	@Override
	public void save(Person person) {
		getHibernateTemplate().save(person);
	}

	@Override
	public Person findPersonById(Serializable id) {
		return getHibernateTemplate().get(Person.class,id);
	}

}
