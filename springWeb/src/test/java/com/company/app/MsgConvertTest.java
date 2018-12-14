package com.company.app;

import java.io.IOException;

import com.company.app.user.UserVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// jackson 라이브러리 테스트
//json string <--> vo 객체

public class MsgConvertTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		
		String str = "{\"id\":\"user1\", \"name\" :\"홍길동\"}";
		UserVO user = objectMapper.readValue(str, UserVO.class);	//(변수명, vo의 클래스 이름) string 은 자동으로 값을 넘겨주는데. controller가 아닐때는 수작업으로 해줘야된다.
		System.out.println(user.getId() + " : " user.getName());
		
	
	}

}
