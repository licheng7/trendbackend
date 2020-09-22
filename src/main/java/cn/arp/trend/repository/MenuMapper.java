package cn.arp.trend.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.arp.trend.entity.Menu;

@Mapper
@Repository
public interface MenuMapper {
	List<Menu> queryAll();
}
