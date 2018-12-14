package com.company.app.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.app.common.JDBCUtil;
import com.company.app.user.UserVO;

@Repository
public class UserDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	//sql 명령어
	private final String USER_GET = "select * from users where id=? ";
	private final String USER_LIST ="select * from users order by seq desc ";
	
	public UserVO getuser(UserVO vo) {
		UserVO uservo = new UserVO();
	 try {
		// 1. connect
		 conn = JDBCUtil.getConnection();
		 
		// 2. 구문
		 stmt =conn.prepareStatement(USER_GET);
		
		 // 3. 결과처리
		 stmt.setString(1, vo.getId());
		 rs = stmt.executeQuery();
		 
		 if(rs.next()) {
			 uservo.setId(rs.getString("id"));
			 uservo.setName(rs.getString("name"));
			 uservo.setPassword("password");
			 uservo.setRole("role");
		 }
	 }catch (Exception e) {
		 e.printStackTrace();
	 }finally {
		 
	 }
		return uservo;
	} 
	
	public List<UserVO> getUserList() {
		System.out.println("UserDAO getUserList");
		List<UserVO> UserList = new ArrayList<UserVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				UserVO uservo = new UserVO();
				uservo.setId(rs.getString("id"));
				uservo.setName(rs.getString("name"));
				uservo.setPassword(rs.getString("password"));
				uservo.setRole(rs.getString("role"));
				UserList.add(uservo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}
		return UserList;
	}
	
	
}
