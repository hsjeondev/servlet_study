package com.gn.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class AppListener implements ServletContextListener {
	
	public AppListener() {}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("=== 웹 애플리케이션 종료 ===");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("=== 웹 애플리케이션 시작 ===");
		// ex)
		// 데이터베이스 연결
		// 사용자에게 시작 되었다고 알려주는 화면
	}
	
	
	
}
