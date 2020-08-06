package cn.arp.trend.service;

/**
 * 内部的事件总线，用于解耦合
 * @author xiejj@cnic.cn
 *
 */
public interface EventService {
	void fireEvent(String name, Object param);
	EventService addListener(String name, EventListener listener);
	void removeListener(String name, EventListener listener);
}
