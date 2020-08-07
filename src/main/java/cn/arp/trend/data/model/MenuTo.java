package cn.arp.trend.data.model;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import cn.arp.trend.entity.Menu;

public class MenuTo {
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
	
	private String img; // 桌面端图标 BLOB

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

	public String getImg() {
		return img;
	}

	public void setImg(String pcImg) {
		this.img = pcImg;
	}
	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	
	public Menu toEntity(){
		Menu menu = new Menu();
		menu.setCreateTime(createTime);
		menu.setCreateUserId(createUserId);
		menu.setId(id);
		try {
			menu.setImg(img==null?null:img.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			//Do nothing
		}
		menu.setMemo(memo);
		menu.setMenuLabel(menuLabel);
		menu.setMenuLevel(menuLevel);
		menu.setMenuSeq(menuSeq);
		menu.setMenuUrl(menuUrl);
		menu.setOrderColumn(orderColumn);
		menu.setParentId(parentId);
		menu.setUpdateTime(updateTime);
		return menu;
	}
	public MenuTo(){
		
	}
	public MenuTo(Menu menu) {
		this.setId(menu.getId());
		this.memo = menu.getMemo();
		this.menuUrl = menu.getMenuUrl();
		this.menuLabel = menu.getMenuLabel();
		this.menuLevel = menu.getMenuLevel();
		this.parentId = menu.getParentId();
		this.orderColumn = menu.getOrderColumn();
		this.menuSeq = menu.getMenuSeq();
		this.createUserId = menu.getCreateUserId();
		this.createTime = menu.getCreateTime();
		this.updateTime = menu.getUpdateTime();
		try {
			this.img = menu.getImg() == null ? null : new String(menu.getImg(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
