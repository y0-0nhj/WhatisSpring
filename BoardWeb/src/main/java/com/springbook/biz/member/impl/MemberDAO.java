package com.springbook.biz.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.member.MemberVO;

@Repository("memberDAO")
public class MemberDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
		
	// sql 문
	private final String MEMBER_INSERT = "insert into member values(?,?,?,?)";
	private final String MEMBER_UPDATE = "update member set name=?, role=? where id=?";
	private final String MEMBER_DELETE = "delete member where id=?";
	private final String MEMBER_GET = "select * from member where id=? and pwd=?";
	private final String MEMBER_LIST = "select * from member";
	
	// 회원 추가(등록)
	public void insertMember(MemberVO vo) {
		System.out.println("===> JDBC로 insertMember() 처리");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getRole());
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("### insertMember() 에러 ###");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 회원 정보 수정
	public void updateMember(MemberVO vo) {
		System.out.println("===> JDBC로 updateMember() 처리");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getRole());
			pstmt.setString(3, vo.getId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("### updateMember() 에러 ###");
			e.printStackTrace();	
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 회원 삭제(탈퇴)
	public void deleteMember(MemberVO vo) {
		System.out.println("===> JDBC로 deleteMember() 처리");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_DELETE);
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("### deleteMember() 에러 ###");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 회원 정보 확인 - 1건(본인)
	public MemberVO getMember(MemberVO vo) {
		System.out.println("===> JDBC로 getMember() 처리");
		MemberVO member = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));	
				member.setName(rs.getString("name"));
				member.setRole(rs.getString("role"));
			}
		} catch(Exception e) {
			System.out.println("### getMember() 에러 ###");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return member;
	}
	
	// 회원 목록 확인 - 전체(관리자)
	public List<MemberVO> getMemberList(MemberVO vo) {
		System.out.println("===> JDBC로 getMemberList() 처리");
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		MemberVO member = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(MEMBER_LIST);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));	
				member.setName(rs.getString("name"));
				member.setRole(rs.getString("role"));
				memberList.add(member);
			}
		} catch(Exception e) {
			System.out.println("### getMemberList() 에러 ###");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return memberList;
	}
	
}
