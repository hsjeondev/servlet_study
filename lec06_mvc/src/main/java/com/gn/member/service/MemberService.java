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
}
