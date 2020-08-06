package cn.arp.trend.tools;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageParam {
	private int page = 1;

	private int pageSize = 10;

	private String sort;

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setPage(int page) {
		if (page > 0) {
			this.page = page;
		}
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Sort buildSortObject() {
		if (StringUtils.isNotEmpty(this.sort)) {
			return Sort.by(SortParamParser.parse(this.sort));
		}
		return null;
	}

	public PageRequest toPageRequest() {
		Sort sortObj = buildSortObject();
		if (sortObj != null) {
			return PageRequest.of(page - 1, pageSize, sortObj);
		} else {
			return PageRequest.of(page - 1, pageSize);
		}
	}
}
