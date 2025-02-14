package com.gn.member.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gn.member.vo.Member;

public class MemberDao {

	public int createMember(Member member, Connection conn) {
		int result = 0;
		
		String id = member.getMemberId();
		String pw = member.getMemberPw();
		String name = member.getMemberName();
		
		String sql = "INSERT INTO member(member_id ,member_pw ,member_name)"
				+ " VALUES (? ,? ,?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member loginMember(String id, String pw, Connection conn) {
		String sql = "SELECT * "
				+ " FROM member "
				+ " WHERE member_id = ? AND member_pw = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int memberNo = rs.getInt("member_no");
				String memberId = rs.getString("member_id");
				String memberPw = rs.getString("member_pw");
				String memberName = rs.getString("member_name");
				member = new Member(memberNo, memberId, memberPw, memberName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return member;
	}
	
	public int updateMember(Member member, Connection conn) {
		int result = 0;
		
		int no = member.getMemberNo();
		String pw = member.getMemberPw();
		String name = member.getMemberName();
		
		String sql = "UPDATE member SET member_pw = ?, member_name = ? "
				+ " WHERE member_no = ?";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setInt(3, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member selectMember(int memberNo, Connection conn) {
		String sql = "SELECT * "
				+ " FROM member "
				+ " WHERE member_no = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt("member_no");
				String memberId = rs.getString("member_id");
				String memberPw = rs.getString("member_pw");
				String memberName = rs.getString("member_name");
				member = new Member(no, memberId, memberPw, memberName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return member;
	}
}
