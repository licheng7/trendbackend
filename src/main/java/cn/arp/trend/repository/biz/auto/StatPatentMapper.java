package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.StatPatent;

public interface StatPatentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_patent
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_patent
     *
     * @mbggenerated
     */
    int insert(StatPatent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_patent
     *
     * @mbggenerated
     */
    int insertSelective(StatPatent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_patent
     *
     * @mbggenerated
     */
    StatPatent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_patent
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StatPatent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_patent
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StatPatent record);
}