package cn.arp.statistic.service.impl;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.arp.icas.rest.RawAPIResponse;
import cn.arp.icas.rest.RestAPIClient;
import cn.arp.icas.rest.RestException;
import cn.vlabs.umt.oauth.AccessToken;
import cn.vlabs.umt.oauth.ArpInfo;

@Service
public class DispatchClient {
	private String baseurl;
	private RestAPIClient client;
	@Value("${login.appname}")
	private String appName;
	// 对方的API包里没有默认构造函数，这里添加一个
	private static class ExtendedArpInfo extends ArpInfo {
	}

	public DispatchClient(@Value("${login.dispatch}") String baseUrl) {
		this.baseurl = baseUrl;
		this.client = new RestAPIClient(baseUrl);
	}

	public String buildUrl() {
		return baseurl + "/login/"+appName;
	}

	public AccessToken retrieveTokenInfo(String code) throws RestException {
		RawAPIResponse response = client.callGetMethod("/authorize/" + code);
		if (response.getStatusCode() == HttpStatus.SC_OK) {
			JSONObject object = response.asJSONObject();
			JSONObject userInfo = (JSONObject) object.get("userInfo");
			JSONObject arpInfo = (JSONObject) userInfo.get("arpInfo");
			userInfo.remove("arpInfo");
			AccessToken token = (AccessToken) JSONObject.toJavaObject(object, AccessToken.class);
			token.getUserInfo().setArpInfo(JSONObject.toJavaObject(arpInfo, ExtendedArpInfo.class));
			return token;
		} else {
			if (response.isJSONObject()){
				JSONObject errorInfo = response.asJSONObject();
				throw new RestException(String.format("%d-%s", errorInfo.get("code"), errorInfo.get("reason")));
			}else{
				throw new RestException(String.format("%d-%s", response.getStatusCode(), response.getReasonPhase()));
			}
		}
	}
}
