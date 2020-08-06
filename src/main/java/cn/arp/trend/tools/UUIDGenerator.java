package cn.arp.trend.tools;

import java.util.UUID;

public final class UUIDGenerator {
    public static String generateId(){
	UUID id = UUID.randomUUID();
	return id.toString().replaceAll("-", "");
    }
}
