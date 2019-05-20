package com.atomikos.jta.controller;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.atomikos.jta.model.User;

//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
@Component
@EnableScheduling // 1.开启定时任务
@EnableAsync // 2.开启多线程
public class DataPushController {
//	@Autowired
//	UserService userService;

	@Async
//	@Scheduled(fixedDelay = 1500) // 间隔1秒
	public void sendSuggestData(User user) {
		try {
//			userService.sendSuggestData();
			Thread.sleep(1500 * 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Async
//	@Scheduled(fixedDelay = 1500)
	public void second() throws InterruptedException {
		System.out.println(
				"第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
		System.out.println();
		Thread.sleep(1500 * 1);
	}
}