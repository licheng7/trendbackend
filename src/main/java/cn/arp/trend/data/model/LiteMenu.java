package cn.arp.trend.data.model;

import java.util.Date;

import cn.arp.trend.entity.Menu;

public class LiteMenu {
	private String id;

	private String menuUrl; // 菜单URL

	private String menuLabel; // 菜单显示名称

	private String parentId; // 父menu_id，对于没有上级的，写-1

	private int orderColumn; // 显示顺序，每一级的排序

	private String menuSeq; // 序列，格式类似“祖父menu_id.父menu_id.自己menu_id”，有多少父级就记录多少，如
							// 111.222.333，对于没有父级的，即father_id=-1的，就写自己的

	private String memo; // 备注

	private String createUserId; // 创建人

	private Date createTime; // 创建时间

	private Date updateTime; // 更新时间

	private int menuLevel; // 级别，从0开始
	
	public static final String PC_ROOT_ID = "0";
	public static final String MOBILE_ROOT_ID = "1";

	public static final String ROOT = "-1";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(int orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public LiteMenu(){
		
	}
	public LiteMenu(Menu m){
		this.createTime = m.getCreateTime();
		this.createUserId = m.getCreateUserId();
		this.id = m.getId();
		this.memo = m.getMemo();
		this.menuLabel = m.getMenuLabel();
		this.menuLevel = m.getMenuLevel();
		this.menuUrl = m.getMenuUrl();
		this.orderColumn = m.getOrderColumn();
		this.parentId = m.getParentId();
		this.updateTime = m.getUpdateTime();
	}
}
