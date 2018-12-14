package com.company.app.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.app.board.CommentsService;
import com.company.app.board.CommentsVO;

@Controller
public class CommentsConteoller {

	@Autowired CommentsService commentservice;
	
	
	//댓글 조회
	@RequestMapping("getCommentsList")
	@ResponseBody
	public List<CommentsVO> getCommentsList(CommentsVO vo) {
		vo.setPageUnit(10);
		return commentservice.getCommentsList(vo);
	}
	
	//댓글등록
	@RequestMapping("insertComments")
	@ResponseBody
	public CommentsVO insertComments(CommentsVO vo) {
		commentservice.insertComments(vo);
		return commentservice.getComments(vo);
		
	}
	
	//댓글 삭제
	@RequestMapping("deleteComments")
	@ResponseBody
	public CommentsVO deleteComments(CommentsVO vo) {
		commentservice.deleteComments(vo);
		return vo;
	
	}
	
}
