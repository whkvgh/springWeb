package com.company.app.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.user.UserService;
import com.company.app.user.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired UserDAOMybatis uda;

	@Override	//단건 조회
	public UserVO getuser(UserVO vo) {
		return uda.getuser(vo);
	}
	
	@Override	//전체 조회
	public List<UserVO> getuserList(UserVO vo){
		return uda.getuserList(vo);
	}
	

	
	}
	
	

	

