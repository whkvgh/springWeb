package com.company.app.board.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

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
		return mv;//리턴할때 jsp페이지명을 작성했음.
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
//jrxml 소스-> pdf 출력
	@RequestMapping("/boardreport")
	public void report(HttpServletRequest request, HttpServletResponse response, Boardvo vo) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			JasperReport report = JasperCompileManager
					.compileReport(request.getSession().getServletContext().getRealPath("reports/boardList.jrxml"));//소스파일 지정
			vo.setFirst(1);
			vo.setLast(100);
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(boardService.getBoardList(vo));	//출력할 데이터 넘김
			JasperPrint print = JasperFillManager.fillReport(report, map, JRdataSource);
			JRExporter exporter = new JRPdfExporter();
			OutputStream out;
			
			response.reset();
			out = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report3.pdf");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);	//pdf 다운
			exporter.exportReport();	//pdf 파일 생성됨
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//엑셀출력
	@RequestMapping("/boardExcel")
	public ModelAndView excelView(Boardvo vo, HttpServletResponse response) throws IOException{
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Map<String, Object> map = new HashMap<String, Object>();
	
	map =new HashMap<String, Object>();
	map.put("title", "제목1");
	map.put("cnt", 10);
	map.put("writer", "안녕");
	list.add(map);
	
	map =new HashMap<String, Object>();
	map.put("title", "제목2");
	map.put("cnt", 9);
	map.put("writer", "하이");
	list.add(map);
	
	map =new HashMap<String, Object>();
	map.put("title", "제목3");
	map.put("cnt", 8);
	map.put("writer", "잘가");
	list.add(map);
	
	HashMap<String, Object> emap =  new HashMap<String, Object>();
	String[] header= {"writer", "title"	};
	emap.put("header", header);
	emap.put("filename", "excel_dept");
	emap.put("datas", list);
	return new ModelAndView("commonExcelView", emap);
	}
}
