package cn.itcasttax.service.impl;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestLog {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Log log = LogFactory.getLog(getClass());
		log.debug("这是debug级别日志");
		log.info("这是info级别日志");
		log.warn("这是warn级别日志");
		log.error("这是error级别日志");
		log.fatal("这是fatal级别日志");
	}

}
