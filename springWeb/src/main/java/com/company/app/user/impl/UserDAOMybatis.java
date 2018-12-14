package com.company.app.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.app.user.UserVO;

@Repository
public class UserDAOMybatis {
	
	@Autowired
	private SqlSessionTemplate ubatis;

	//단건 조회
	public UserVO getuser(UserVO vo) {
		System.out.println("ubatis getuser() 기능 실행");
		return ubatis.selectOne("user.getuser", vo);
	}

	//전체 조회
	public List<UserVO> getuserList(UserVO vo) {
		System.out.println("ubatis getUserList() 기능 실행");
		return ubatis.selectList("user.getUserList", vo);
	}

}
