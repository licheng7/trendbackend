package cn.arp.trend.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.arp.trend.service.EventListener;
import cn.arp.trend.service.EventService;

@Service
public class EventServiceImpl implements EventService {
	private Map<String, Set<EventListener>> map;

	public EventServiceImpl() {
		map = Collections.synchronizedMap(new HashMap<String, Set<EventListener>>());
	}

	@Override
	public void fireEvent(String name, Object param) {
		Set<EventListener> listeners = map.get(name);
		if (listeners != null) {
			for (EventListener listener : listeners) {
				listener.actionPerformed(param);
			}
		}
	}

	@Override
	public EventService addListener(String name, EventListener listener) {
		Set<EventListener> listeners = map.get(name);
		if (listeners == null) {
			listeners = Collections.synchronizedSet(new HashSet<EventListener>());
			map.put(name, listeners);
		}
		listeners.add(listener);
		return this;
	}

	@Override
	public void removeListener(String name, EventListener listener) {
		Set<EventListener> listeners = map.get(name);
		if (listeners != null) {
			listeners.remove(listener);
		}
	}

}
