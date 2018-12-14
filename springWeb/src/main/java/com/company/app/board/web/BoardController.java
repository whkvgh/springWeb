package com.company.app.board.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.company.app.board.BoardService;
import com.company.app.board.Boardvo;
import com.company.app.common.Paging;

@Controller // 클라이언트 요청시 1번으로 실행됨.
public class BoardController {

	@Autowired
	BoardService boardService; // service 호출

	// 전체 조회ajax
	@RequestMapping("getBoardListAjax")
	@ResponseBody
	public List<Boardvo> getBoardListAjax(Boardvo vo) {
		return boardService.getBoardList(vo);
	}

	// 전체 조회ajax 뷰페이지로 이동
	@RequestMapping("getBoardListAjaxView")
	public String getBoardListAjaxView(Boardvo vo) {
		return "board/getBoardListAjax";
	}

	/*
	 * // 전체 조회
	 * 
	 * @RequestMapping(value = { "/getBoardList", "/getListBoard", "/getBoards" },
	 * method = RequestMethod.GET) // http://localhost:8081/app/getBoardList public
	 * String getBoardList(Model model, Boardvo vo) {
	 * model.addAttribute("boardList", boardService.getBoardList(vo)); return
	 * "board/getBoardList"; // 폴더명/페이지명; }
	 */

	// 전체 조회
	@RequestMapping(value = { "/getBoardList", "/getListBoard", "/getBoards" }, method = RequestMethod.GET)
	// http://localhost:8081/app/getBoardList
	public ModelAndView getBoardList(Boardvo vo, Paging paging) {
		ModelAndView mv = new ModelAndView();
		
		//페이징 처리
		//페이지번호 파라미터
		if(paging.getPage() == null) {
			paging.setPage(1);
	}
//한페이지 출력할 레코드 건수
		paging.setPageUnit(5);
	//first.last
	vo.setFirst(paging.getFirst());
	vo.setLast(paging.getLast());
	
	//전체 레코드 건수
	paging.setTotalRecord(boardService.getCount(vo));
		
		mv.addObject("paging", paging);
		mv.addObject("boardList", boardService.getBoardList(vo));
		mv.setViewName("board/getBoardList");
		return mv;
	}

	// 단건 조회
	@RequestMapping("/getBoard") // http://localhost:8081/app/getBoardList
	public String getBoard(Model model, Boardvo vo) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "board/getBoard";
	}

	// 등록폼
	@RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
	public String insertBoardform() {
		return "board/insertBoard";
	}

	// 등록 처리
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public String insertBoard(Boardvo vo, HttpServletRequest request) throws IllegalStateException, IOException { // 커멘드
																													// 객체
		// 첨부파일이 있으면 첨부파일을 업로드(업로더 폴더로 저장)
		// 업로드 파일명을 vo에 저장
		String path = request.getSession().getServletContext().getRealPath("/resources");
		System.out.println("path=========" + path);// 이미지 파일일 경우

		// ServlertContext ==내장객체 application
		// 첨부파일이 있으면 첨부 파일을 업로드(업로드 폴더로 저장)
		MultipartFile uploadFile = vo.getUploadFile();

		if (!uploadFile.isEmpty() && uploadFile.getSize() > 0) { // 파일크기로 첨부여부 확인
			String filename = uploadFile.getOriginalFilename(); // 업로드 파일명
			uploadFile.transferTo(new File(path, filename)); // 파일 이름
			// 업로드 파일을 vo에 저장
			vo.setUploadFileName(filename);
		}
		boardService.insertBoard(vo); // 목록처리
		return "redirect:getBoardList"; // 목록 요청
	}

	// 수정 폼
	@RequestMapping("/updateBoardform")
	public String updateBoardform(Model model, Boardvo vo) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "board/updateBoard";
	}

	// 수정 처리
	@RequestMapping("/updateBoard")
	public String updateBoard(Boardvo vo) {
		boardService.updateBoard(vo);
		return "redirect:getBoardList";
	}

	// 삭제 처리
	@RequestMapping("/deleteBoard")
	public String deleteBoardform(Boardvo vo) {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList";
	}

	// 선택 삭제
	@RequestMapping("/deleteBoardList")
	public String deleteBoardList(Boardvo vo) {
		boardService.deleteBoardList(vo);
		return "redirect:getBoardList";
	}

	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	@RequestMapping(value = "/FileDown")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String atchFileId = (String) commandMap.get("atchFileId");
		String path = request.getSession().getServletContext().getRealPath("/resources");
		File uFile = new File(path, atchFileId);
		long fSize = uFile.length();
		if (fSize > 0) {
			String mimetype = "application/x-msdownload";
			response.setContentType(mimetype);
			// response.setHeader("Content-Disposition", "attachment; 한글이 첨부되어있어서
			// filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
			setDisposition(atchFileId, request, response);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} else {
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<h2>Could not get file name:<br>" + atchFileId + "</h2>");
			printwriter.println("<center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

}
