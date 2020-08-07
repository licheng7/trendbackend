package cn.arp.trend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.arp.trend.entity.Menu;
import cn.arp.trend.entity.MenuRoleRelation;

@Repository
public interface MenuRoleDAO
		extends JpaRepository<MenuRoleRelation, String>, JpaSpecificationExecutor<MenuRoleRelation> {
	@Modifying
	@Query("delete from MenuRoleRelation where roleId = ?1")
	void deleteByRoleId(Integer roleId);

	@Modifying
	@Query("delete from MenuRoleRelation where menuId = ?1")
	void deleteByMenuId(String param);

	@Modifying
	@Query("delete from MenuRoleRelation where menuId in ?1")
	void deleteByMenuIds(List<String> param);
	
	@Modifying
	@Query("delete from MenuRoleRelation where roleId in ?1")
	void deleteByRoleIds(List<String> param);

	@Query("select m from MenuRoleRelation mr, Menu m where m.id=mr.menuId and mr.roleId in ?1")
	List<Menu> findRoleMenus(List<Integer> roleIds);

	@Query("from MenuRoleRelation where roleId = ?1")
	List<MenuRoleRelation> findByRoleId(Integer roleId);

}
