package com.company.app.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.company.app.board.Boardvo;

@Repository
public class BoardDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 등록
		public void insertBoard(Boardvo vo) {
			System.out.println("mybatis insertboard() 기능 실행");
			mybatis.update("board.insertBoard", vo);
		}
		
		//수정
		public void updateBoard(Boardvo vo) {
			System.out.println("mybatis updateboard() 기능 실행");
			mybatis.update("board.updateBoard", vo);
			//namespace 작성하고 각 id값 넣어주면됨
		}
		//삭제
		public void deleteBoard(Boardvo vo) {
			System.out.println("mybatis deleteboard() 기능 실행");
			mybatis.update("board.deleteBoard", vo);
		}
		
		//선택 삭제
		public void deleteBoardList(Boardvo vo) {
			System.out.println("mybatis deleteBoardList() 기능 실행");
			mybatis.update("board.deleteBoardList", vo);
		}
		
		//단권조회
		public Boardvo getBoard(Boardvo vo) {
			System.out.println("mybatis getBoard() 기능 실행");
			return mybatis.selectOne("board.getBoard", vo);
		}
		
		//전체 조회
		public List<Boardvo> getBoardList(Boardvo vo) {
			System.out.println("mybatis getBoard() 기능 실행");
			return mybatis.selectList("board.getBoardList", vo);
			//리턴값이 있으면 따로 정의해서 읽어와야된다
		}
		
		//건수 조회
		public int getCount(Boardvo vo) {
			return mybatis.selectOne("board.getCount", vo);
		}
	}

