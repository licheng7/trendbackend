package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.StatArpStaff;

public interface StatArpStaffMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff
     *
     * @mbggenerated
     */
    int insert(StatArpStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff
     *
     * @mbggenerated
     */
    int insertSelective(StatArpStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff
     *
     * @mbggenerated
     */
    StatArpStaff selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StatArpStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StatArpStaff record);
}