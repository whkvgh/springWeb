package com.company.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.app.user.UserService;
import com.company.app.user.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class UserServiceClient {
	
	@Autowired
	UserService userService;
	
	@Test
	public void getUserTest() {
		UserVO vo = new UserVO();
		vo.setId("test");
		assertEquals("관리자", userService.getuser(vo).getName());
	}

}
