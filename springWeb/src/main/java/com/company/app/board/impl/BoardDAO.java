package com.company.app.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import org.springframework.stereotype.Repository;

import com.company.app.board.Boardvo;
import com.company.app.common.JDBCUtil;

//3번째로 호출
@Repository("boardDAO")
public class BoardDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// Sql 명령어
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content, regdate, cnt) "
			+ " values((select nvl(max(seq),0)+1 from board), ?, ?, ?, sysdate, 0)";
	private final String BOARD_UPDATE = "update board set title = ?, content = ? where seq=?";
	private final String BOARD_DELETE = "delete board where seq =?";
	private final String BOARD_GET = "select * from board where  seq =?";
	private final String BOARD_LIST = "select * from board order by seq desc";

	// 등록
	public void insertBoard(Boardvo vo) {
		System.out.println("BoardDOA insertBoard");
		try {
			// 1. connect
			conn = JDBCUtil.getConnection();
			//conn.setAutoCommit(false);	//트랜젝션 처리 
						
			// 2. 구문
			stmt = conn.prepareStatement(BOARD_INSERT);

			// 3. 결과처리
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			int r = stmt.executeUpdate();
			//conn.commit();
			System.out.println(r + "건이 등록됨");

		} catch (Exception e) {
			/*try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}*/
			e.printStackTrace();

		} finally {
			// 4. 연결해제
			JDBCUtil.close(rs, stmt, conn);
		}

	}
	// 수정

	public void updateBoard(Boardvo vo) {
		System.out.println("BoardDOA updateBoard");
		try {
			// 1. connect
			conn = JDBCUtil.getConnection();

			// 2. 구문
			stmt = conn.prepareStatement(BOARD_UPDATE);

			// 3. 결과처리
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			int r = stmt.executeUpdate();
			System.out.println(r + "건 수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

	}

	// 삭제
	public void deleteBoard(Boardvo vo) {
		System.out.println("BoardDOA deleteBoard");
		try {
			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}

	}

	// 단건조회

	public Boardvo getBoard(Boardvo vo) {
		System.out.println("BoardDOA getBoard");
		Boardvo boardVo = new Boardvo();
		try {

			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(BOARD_GET);

			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();

			if (rs.next()) {
				boardVo.setCnt(rs.getInt("cnt"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setWriter(rs.getString("writer"));
				boardVo.setContent(rs.getString("content"));
				boardVo.setRegdate(rs.getString("regdate"));
				boardVo.setSeq(rs.getInt("seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return boardVo;

	}

	// 전체 조회
	public List<Boardvo> getBoardList() {
		System.out.println("BoardDao getBoardList");
		List<Boardvo> BoardList = new ArrayList<Boardvo>();
		try {
			// 1. connect
			conn = JDBCUtil.getConnection();
			// 2. 구문
			stmt = conn.prepareStatement(BOARD_LIST);
			// 3.처리결과

			rs = stmt.executeQuery();

			while (rs.next()) {
				Boardvo boardVo = new Boardvo();
				boardVo.setCnt(rs.getInt("cnt"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setWriter(rs.getString("writer"));
				boardVo.setContent(rs.getString("content"));
				boardVo.setRegdate(rs.getString("regdate"));
				boardVo.setSeq(rs.getInt("seq"));
				BoardList.add(boardVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);

		}
		return BoardList;

	}
}
