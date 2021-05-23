package com.springbook.biz.board;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

// VO(Value Object), DTO(Data Transfer)
@XmlAccessorType(XmlAccessType.FIELD) // xml 방식으로 데이터를 전달
public class BoardVO {
	@XmlAttribute // xml 방식으로 데이터를 전달할 때 필수적인 필드로 설정
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	// 파일 업로드 
	@XmlTransient // xml 방식으로 데이터를 전달할 때 사용하지 않는 필드로 설정
	private MultipartFile uploadFile;
	// 검색 기능 구현을 위한 필드
	@XmlTransient
	private String searchCondition;
	@XmlTransient
	private String searchKeyword;
		
	public int getSeq() {
		return seq;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public int getCnt() {
		return cnt;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@JsonIgnore
	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@JsonIgnore
	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	@JsonIgnore
	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + ", uploadFile=" + uploadFile + ", searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + "]";
	}
	
}
