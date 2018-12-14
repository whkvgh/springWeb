package com.company.app;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.app.board.BoardService;
import com.company.app.board.Boardvo;
import com.company.app.board.impl.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BoardDAOClient {

	@Autowired BoardService boardService;
	
	/*@Autowired BoardDAO dao;
	
	@Ignore @Test
	public void getBoardTest() {
		Boardvo vo = new Boardvo();
		vo.setSeq(1);
		Boardvo result = dao.getBoard(vo);
		System.out.println("result");
		assertEquals("홍길동", result.getWriter());
	}*/
	
	@Test
	public void insertBoardTest() {
		Boardvo vo = new Boardvo();
		vo.setSeq(8);
		vo.setTitle("팔번째 글");
		vo.setWriter("jjh");
		vo.setContent("휴..");
		boardService.insertBoard(vo);
	
	}
}
