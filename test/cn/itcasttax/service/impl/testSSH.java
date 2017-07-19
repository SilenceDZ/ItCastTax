package cn.itcasttax.service.impl;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testSSH {
	private static ClassPathXmlApplicationContext context=null;
	
	@BeforeClass
	public static void init(){
		context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@AfterClass
	public static void destory(){
		
	}
	
	@Test
	public void test() {
		SpringIocTest bean=(SpringIocTest) context.getBean("springIoc");
		bean.say();
	}

}
