package com.atomikos.jta.dao.databases2;

import org.springframework.stereotype.Repository;

import com.atomikos.jta.model.Message;

@Repository
public interface PushMessageMapper {
//	Integer add(Infomation message) throws Exception;
//
//	Integer del(Long id) throws Exception;
	
	
	int insertSuggest(Message msg);
}
