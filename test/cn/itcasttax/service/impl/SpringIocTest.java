package cn.itcasttax.service.impl;

import org.springframework.stereotype.Service;

@Service("springIoc")
public class SpringIocTest {
	public void say(){
		System.out.println("SpringIocTest.say()");
	}
}
