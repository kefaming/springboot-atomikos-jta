package com.atomikos.jta.service;

import com.atomikos.jta.model.User;

/**
 * Created by hyson on 2017/3/10.
 */
public interface MessageService {
//    Integer add(User user) throws Exception ;
//
//    Integer del(Long id) throws Exception ;
    
    void sendMessageData() throws Exception;
    
    void updateMessageLog() throws Exception;
    
    
}  