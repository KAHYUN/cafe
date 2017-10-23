package com.cafedal.webapp.dao.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.cafedal.webapp.dao.MemberDao;
import com.cafedal.webapp.dao.NoticeDao;
import com.cafedal.webapp.entity.Member;
import com.cafedal.webapp.entity.Notice;
import com.cafedal.webapp.entity.NoticeView;

public class MyBatisMemberDao implements MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Member get(String id) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.get(id);
	}
}
