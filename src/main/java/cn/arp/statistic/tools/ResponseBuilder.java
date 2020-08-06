package cn.arp.statistic.tools;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
	public static <T> ResponseEntity<List<T>> build(Page<T> entries) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("total", Long.toString(entries.getTotalElements()));
		headers.add("x-total-count", Long.toString(entries.getTotalElements()));
		ResponseEntity<List<T>> entity = new ResponseEntity<List<T>>(entries.getContent(), headers, HttpStatus.OK);
		return entity;
	}

	public static <T> ResponseEntity<List<T>> build(List<T> content, long total) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("total", Long.toString(total));
		headers.add("x-total-count", Long.toString(total));
		ResponseEntity<List<T>> entity = new ResponseEntity<List<T>>(content, headers, HttpStatus.OK);
		return entity;
	}
}
