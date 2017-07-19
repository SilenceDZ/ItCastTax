package cn.itcasttax.service.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcasttax.entity.Person;
import cn.itcasttax.service.ITestService;

public class testSSH {
	private static ClassPathXmlApplicationContext context;
	@BeforeClass
	public static void init(){
		context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	
	@Test
	public void test() {
		ITestService bean= (ITestService) context.getBean("testService");
		bean.say();
	}

	@Test
	public void testHibernate(){
		SessionFactory cf=(SessionFactory) context.getBean("sessionFactory");
		Session session=cf.openSession();
		Transaction tx=session.beginTransaction();
		Person per=(Person) context.getBean("person");
		per.setName("testHibernate3"); 
		session.save(per);
		tx.commit();
		session.close();
	}
	
	@Test
	public void testService(){
		ITestService service=(ITestService) context.getBean("testService");
//		service.save(new Person("testService"));
		Person per=service.findPersonById("402848e45d5b5b4c015d5b5b4e810000");
		System.out.println(per);
	}
	
}
