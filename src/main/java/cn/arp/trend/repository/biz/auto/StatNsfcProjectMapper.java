package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.StatNsfcProject;

public interface StatNsfcProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_nsfc_project
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_nsfc_project
     *
     * @mbggenerated
     */
    int insert(StatNsfcProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_nsfc_project
     *
     * @mbggenerated
     */
    int insertSelective(StatNsfcProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_nsfc_project
     *
     * @mbggenerated
     */
    StatNsfcProject selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_nsfc_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StatNsfcProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_nsfc_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StatNsfcProject record);
}