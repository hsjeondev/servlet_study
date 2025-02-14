package com.gn.member.service;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.gn.member.dao.MemberDao;
import com.gn.member.vo.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();

	public int createMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.createMember(member, conn);
		close(conn);
		return result;
	}
	
	public Member loginMember(String id, String pw) {
		Connection conn = getConnection();
		Member member = memberDao.loginMember(id, pw, conn);
		close(conn);
		return member;
	}
	
	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMember(member, conn);
		close(conn);
		return result;
	}
	
	public Member selectMember(int memberNo) {
		Connection conn = getConnection();
		Member member = memberDao.selectMember(memberNo, conn);
		close(conn);
		return member;
	}
}
