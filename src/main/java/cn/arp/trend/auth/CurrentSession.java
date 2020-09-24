package cn.arp.trend.auth;

import java.util.ArrayList;
import java.util.List;

public class CurrentSession {
	private static ThreadLocal<UserSubject> currentUserInfo = new ThreadLocal<UserSubject>();

	public static void populate(UserSubject subject) {
		currentUserInfo.set(subject);
	}

	public static void clear() {
		currentUserInfo.remove();
	}

	public static UserSubject getSubject() {
		return currentUserInfo.get();
	}

	public static String getUserId() {
		if (currentUserInfo.get() != null) {
			return currentUserInfo.get().getUser().getEmail();
		} else {
			return null;
		}
	}

	public static List<Integer> getRoleIds() {
		if (currentUserInfo.get() != null) {
			List<Integer> list = new ArrayList<>();
			for (RolePrincipal role : currentUserInfo.get().getRoles()) {
				list.add(role.getRoleId());
			}
			return list;
		}
		return null;
	}
}
