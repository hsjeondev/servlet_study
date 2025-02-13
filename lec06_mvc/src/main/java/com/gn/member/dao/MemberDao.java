package com.gn.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import static com.gn.common.sql.JDBCTemplate.close;

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
}
