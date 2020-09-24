package cn.arp.trend.service;

import java.util.List;

public interface DataSetAuthService {

	boolean canAccess(List<Integer> roleIds, String dataset);
}
