package com.company.app.board;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

//vo value object = dto =do

public class Boardvo {

	private int seq;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int cnt;

	//파일 업로드
	private MultipartFile uploadFile;
	private String uploadFileName;
	
	//페이징 처리
	private int First;
	private int Last;
	
	

	public int getFirst() {
		return First;
	}

	public void setFirst(int first) {
		First = first;
	}

	public int getLast() {
		return Last;
	}

	public void setLast(int last) {
		Last = last;
	}

	public String getUploadFileName() {	//#{}
		return uploadFileName;
	}

	public void setUploadFileName(String uplodFileName) {
		this.uploadFileName = uplodFileName;
	}
	@JsonIgnore
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	@JsonIgnore
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	// 검색
	private String searchCondition;
	private String searchKeyword;
	private String sortCol;

	// 선택 삭제
	@JsonIgnore
	private String[] seqlist;

	@JsonIgnore
	public String[] getSeqlist() {
		return seqlist;
	}

	@JsonIgnore
	public void setSeqlist(String[] seqlist) {
		this.seqlist = seqlist;
	}

	@JsonIgnore
	public String getSortCol() {
		return sortCol;
	}

	@JsonIgnore
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	@JsonIgnore
	public String getSearchCondition() {
		return searchCondition;
	}

	@JsonIgnore
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@JsonIgnore
	public String getSearchKeyword() {
		return searchKeyword;
	}

	@JsonIgnore
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Boardvo [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + "]";
	}

}
