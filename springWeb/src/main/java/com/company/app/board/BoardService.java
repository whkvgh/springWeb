package com.company.app.board;

import java.util.List;

//DAO에 있는 메소드 해드만 있음 됨.
public interface BoardService {

	public void insertBoard(Boardvo vo);

	public void updateBoard(Boardvo vo);

	public void deleteBoard(Boardvo vo);

	public void deleteBoardList(Boardvo vo);

	public Boardvo getBoard(Boardvo vo);

	public List<Boardvo> getBoardList(Boardvo vo);
	
	public int getCount(Boardvo vo);
}
