package com.company.app.user;

import java.util.List;

public interface UserService {
	
	//단건 조회
	public UserVO getuser(UserVO vo);
	
	//전체 조회
	public List<UserVO> getuserList(UserVO vo);
	

}
