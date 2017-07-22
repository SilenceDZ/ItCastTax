package cn.itcast.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import cn.itcast.core.dao.BaseDao;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class<T> clazz;
	/*@Autowired
	private SessionFactory sessionFactory;*/
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		//UserDaoImpl<User>  this是指代的UserDaoImpl类（实例化的子类），所以能获取User.class
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) pt.getActualTypeArguments()[0];
	}
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity); 
	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(findObectsById(id));
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObjects() {
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession(); 
		String hql=" from "+clazz.getSimpleName();
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public T findObectsById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	

}
