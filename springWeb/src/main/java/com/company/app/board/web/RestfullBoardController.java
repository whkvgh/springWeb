package com.company.app.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.board.BoardService;
import com.company.app.board.Boardvo;

@RestController // 메소드로 구분
public class RestfullBoardController {

	@Autowired
	BoardService boardService;

	// 전체조회
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public List<Boardvo> getBoardList() {
		return boardService.getBoardList(null);
	}

	// 조회
	@RequestMapping(value = "/board/{seq}", method = RequestMethod.GET)
	public Boardvo getBoard(@PathVariable Integer seq) {
		Boardvo vo = new Boardvo();
		vo.setSeq(seq);
		return boardService.getBoard(vo);
	}

	// 삭제
	@RequestMapping(value="/board/{seq}", method=RequestMethod.DELETE)
	public Boardvo deleteBoard(@PathVariable Integer seq) {
		Boardvo vo = new Boardvo();
		vo.setSeq(seq);
		boardService.deleteBoard(vo);
		return vo;
	}
		
	//등록
	@RequestMapping(value="/board"
					, method=RequestMethod.POST
					, consumes="application/json")	//request 요청값
	public Boardvo insertBoard(@RequestBody Boardvo vo) {
		boardService.insertBoard(vo);
		return boardService.getBoard(vo);
	}
	//수정
	@RequestMapping(value="/board"
				, method=RequestMethod.PUT
				, consumes="application/json")
	public Boardvo updateBoard(@RequestBody Boardvo vo) {
		boardService.insertBoard(vo);
		return boardService.getBoard(vo);
}
	}
