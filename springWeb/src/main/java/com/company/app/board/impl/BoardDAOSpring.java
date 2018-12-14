package com.company.app.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.company.app.board.Boardvo;

@Repository
public class BoardDAOSpring {

	@Autowired private JdbcTemplate jdbcTemplate;
	//sql 명령어
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content, regdate, cnt) "
			+ " values(?, ?, ?, ?, sysdate, 0)"; //(select nvl(max(seq),0)+1 from board)
	private final String BOARD_UPDATE = "update board set title = ?, content = ? where seq=?";
	private final String BOARD_DELETE = "delete board where seq =?";
	private final String BOARD_GET = "select * from board where  seq =?";
	private final String BOARD_LIST = "select * from board order by seq desc";

	// 등록
	public void insertBoard(Boardvo vo) {
		System.out.println("Spring jdbc로 insertboard() 기능 실행");
		Object[] params = {vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getContent()};
		jdbcTemplate.update(BOARD_INSERT, params);
	}
	
	//수정
	public void updateBoard(Boardvo vo) {
		System.out.println("spring jdbc로 updateboard() 기능 실행");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	//삭제
	public void deleteBoard(Boardvo vo) {
		System.out.println("spring jdbc로 deleteboard() 기능 실행");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	//단권조회
	public Boardvo getBoard(Boardvo vo) {
		System.out.println("spring jdbc getBoard() 기능 실행");
		Object[] param = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, param, new BoardRowMapper());
	}
	
	//전체 조회
	public List<Boardvo> getBoardList() {
		System.out.println("spring jdbc로 getboardlist() 기능 실행");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		//리턴값이 있으면 따로 정의해서 읽어와야된다
	}
	
}

//매핑 클래스 resultset을 -> javaobject DTO로 만들기!
class BoardRowMapper implements RowMapper<Boardvo>{	

	@Override
	public Boardvo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Boardvo boardVo = new Boardvo();
		boardVo.setCnt(rs.getInt("cnt"));
		boardVo.setTitle(rs.getString("title"));
		boardVo.setWriter(rs.getString("writer"));
		boardVo.setContent(rs.getString("content"));
		boardVo.setRegdate(rs.getString("regdate"));
		boardVo.setSeq(rs.getInt("seq"));
		return boardVo;
	}
	
}
