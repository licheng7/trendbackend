package cn.arp.trend.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "T_TREND_MENU")
/**
 * 菜单实体类： corpOrgId为空，表示是全局的菜单，不为空则是租户对应的菜单，租户只能修改本地的菜单。
 * 
 * @author xiejj@cnic.cn
 *
 */
public class Menu {
	@Id
	@Column(name = "ID", length = 32)
	private String id;

	@Column(name = "MENU_URL", length = 2000)
	private String menuUrl; // 菜单URL

	@Column(name = "MENU_LABEL", length = 200)
	private String menuLabel; // 菜单显示名称

	@Column(name = "PARENT_ID", length = 32)
	private String parentId; // 父menu_id，对于没有上级的，写-1

	@Column(name = "ORDER_COLUMN", length = 200)
	private int orderColumn; // 显示顺序，每一级的排序

	@Column(name = "MENU_SEQ", length = 2000)
	private String menuSeq; // 序列，格式类似“祖父menu_id.父menu_id.自己menu_id”，有多少父级就记录多少，如
							// 111.222.333，对于没有父级的，即father_id=-1的，就写自己的

	@Column(name = "memo", length = 200)
	private String memo; // 备注

	@Column(name = "CREATE_USERID", length = 32)
	private String createUserId; // 创建人

	@Column(name = "CREATE_TIME")
	private Date createTime; // 创建时间

	@Column(name = "UPDATE_TIME")
	private Date updateTime; // 更新时间

	@Lob // 大字段
	@Basic(fetch = FetchType.LAZY) // 懒加载
	@Column(name = "IMG", length = 1024 * 1024) // 1G
	private byte[] img; // 桌面端图标 BLOB

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

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] pcImg) {
		this.img = pcImg;
	}
}
