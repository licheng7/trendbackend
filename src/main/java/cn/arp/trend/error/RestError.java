package cn.arp.trend.error;

import com.alibaba.fastjson.JSONObject;

public class RestError extends Exception {
	private static final long serialVersionUID = 1L;
	private int code;
	private String reason;

	private RestError(int code, String reason) {
		super(String.format("%d-%s", code, reason));
		this.code = code;
		this.reason = reason;
	}

	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("reason", reason);
		return result;
	}

	public static RestError notFound(String reason) {
		return new RestError(404, reason);
	}

	public static RestError badArgument(String reason) {
		return new RestError(400, reason);
	}

	public static RestError notAuthorized(String reason) {
		return new RestError(401, reason);
	}

	public static RestError accessForbidden(String reason) {
		return new RestError(403, reason);
	}
	
	public static RestError internalError(String reason) {
		return new RestError(500, reason);
	}
	public int getCode() {
		return this.code;
	}
}
