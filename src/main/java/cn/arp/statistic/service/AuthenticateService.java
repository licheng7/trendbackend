package cn.arp.statistic.service;

import javax.servlet.http.HttpServletRequest;

import cn.arp.statistic.auth.UserSubject;
import cn.vlabs.umt.oauth.UserInfo;

public interface AuthenticateService {
	void saveSession(HttpServletRequest request, UserInfo userInfo);

	boolean canAccessOrg(HttpServletRequest request, String orgId);
	boolean hasRole(HttpServletRequest request, String roleName);

	boolean containAtLeastOneRole(HttpServletRequest request, String[] roles);

	boolean hasLogin(HttpServletRequest request);
	
	UserSubject loadSubject(HttpServletRequest request);

	boolean canAccessAll(HttpServletRequest request);
	
	boolean canAccessHost(HttpServletRequest request,String ip);
}
