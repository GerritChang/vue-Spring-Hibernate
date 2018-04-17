/**
 * Captain
 * © 2018 Captain copyright，All rights reserved.
 * http://www.sccaptain.com.cn
 * 
 * JAVA : 8
 * 文  件  名: RequestInterceptor.java
 * 创  建  人: ZhangGuantao (zhang.guantao@sccaptain.com.cn)
 * 创建时间: 2018年4月16日 下午4:04:26
 * 版         本: 1.0.0
 * 备         注:
 * 修订历史:
 */
package com.cpt.ssh.iterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @since 1.0
 * @version 1.0
 * @author ZhangGuantao (zhang.guantao@sccaptain.com.cn)
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允许浏览器携带用户身份信息（cookie）
		return true;
	}
}
