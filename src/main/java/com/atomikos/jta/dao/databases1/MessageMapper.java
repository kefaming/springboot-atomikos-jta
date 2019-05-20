package com.atomikos.jta.dao.databases1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atomikos.jta.model.Message;
import com.atomikos.jta.model.User;

@Repository
public interface MessageMapper {
//	Integer add(User user) throws Exception;
//	Integer del(Long id) throws Exception;
    
    List<Message> selectSuggestList(Map<String,Object> map);
	int updateSuggestByGroup(@Param("ids") String[] ids);
    
}
