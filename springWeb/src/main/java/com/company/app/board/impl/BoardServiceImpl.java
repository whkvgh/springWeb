package com.company.app.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.board.BoardService;
import com.company.app.board.Boardvo;

@Service // 빈생성하는거!
public class BoardServiceImpl implements BoardService {

//	@Autowired BoardDAO dao;	//2번 호출 ->dao 호출 dao로 호출

	// @Autowired BoardDAOSpring dao; // spring dao 호출

	/* private Log4Advice log = new Log4Advice(); */

	@Autowired
	BoardDAOMybatis dab;

	@Override
	public void insertBoard(Boardvo vo) {
		dab.insertBoard(vo);
		System.out.println("=============" + vo.getSeq());
	}

	@Override
	public void updateBoard(Boardvo vo) {
		dab.updateBoard(vo);
	}

	@Override
	public void deleteBoard(Boardvo vo) {
		dab.deleteBoard(vo);
	}

	@Override
	public void deleteBoardList(Boardvo vo) {
		dab.deleteBoardList(vo);
	}

	@Override
	public Boardvo getBoard(Boardvo vo) {
		return dab.getBoard(vo);
	}

	@Override
	public List<Boardvo> getBoardList(Boardvo vo) {
		return dab.getBoardList(vo);
	}

	@Override
	public int getCount(Boardvo vo) {
		return dab.getCount(vo);
	}

}
