package cn.itcast.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;


public interface BaseDao<T> {
	
	public void save(T entity);
		
	public void update(T entity);
		
	public void delete(Serializable id);
	
	public void deleteAll(Collection<?> entities);
	
	public List<T> findObjects();
		
	public T findObectsById(Serializable id);
	
	public 	Session getCurrentSession();
	
}
