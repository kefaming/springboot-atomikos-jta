package com.atomikos.jta.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atomikos.jta.dao.databases1.MessageMapper;
import com.atomikos.jta.dao.databases2.PushMessageMapper;
import com.atomikos.jta.model.Infomation;
import com.atomikos.jta.model.Message;
import com.atomikos.jta.model.PushMessage;
import com.atomikos.jta.model.User;
import com.atomikos.jta.service.UserService;
import com.atomikos.jta.util.DateUtil;

/**
 * Created by hyson on 2017/3/10.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    MessageMapper originalMessageMapper;
    @Autowired
    PushMessageMapper pushMessageMapper;

//    @Override
//    public Integer add(User user) {
//        //这里必须要抛出异常，不然JTA不会回滚数据
//        try {
//            userMapper.add(user);
////            System.out.println(1 / 0);
//            Infomation message = new Infomation();
//            message.setName(user.getName());
//            message.setContent(user.getAge().toString());
//            messageMapper.add(message);
//        } catch (Exception e) {
//            logger.error("add异常");
//            throw new RuntimeException();
//        }
//        return 1;
//    }
//
//    @Override
//    public Integer del(Long id) {
//        //这里必须要抛出异常，不然JTA不会回滚数据
//        try {
//            userMapper.del(id);
//            messageMapper.del(id);
//        } catch (Exception e) {
//            logger.error("del异常");
//            throw new RuntimeException();
//        }
//        return 1;
//    }

	@Override
	public void sendSuggestData() throws Exception {
		long beginTime = System.currentTimeMillis();
		
		//1、从数据库获取要推送的数据
		Map<String,Object> map=new HashMap<String,Object>();
		List<Message> list = originalMessageMapper.selectSuggestList(map);
		if(list == null || list.size() <=0){
			logger.info("============= 推送失败[失败原因：当前无新数据]， 当前时间："+DateUtil.format(new Date(System.currentTimeMillis()))+"... ==============");
			return;
		}
		
		boolean basesuccess = true;
		if(basesuccess) {
			boolean success = false;
			//5、将数据生成XML文件
			String[] ids = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {// 此处 for 循环可替换成 遍历 数据库表的结果集操作;
				Message msg = list.get(i);
				msg.setMsgId(msg.getId());
				pushMessageMapper.insertSuggest(msg);
				ids[i] = msg.getId();
				success = true;
			}
//			System.out.println(1 / 0);
	        if(success) {
		        	if(list != null  && list.size() > 0){        	
		        		//7、统一更新状态码
		        		int count = originalMessageMapper.updateSuggestByGroup(ids);
		        		long endTime = System.currentTimeMillis();
		        		logger.info("============= 推送成功[共写入"+count+"条记录，耗时"+(endTime-beginTime)/1000+"秒]， 当前时间："+DateUtil.format(new Date(System.currentTimeMillis()))+"... ==============");
		        		return;
		        	}
	        }
	        String end = DateUtil.format(new Date(System.currentTimeMillis()));
	        logger.info("============= 写入成功。当前系统时间："+end+" ==============");
		}
		
	}

}