package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.StatArpStaffPromotion;

public interface StatArpStaffPromotionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff_promotion
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff_promotion
     *
     * @mbggenerated
     */
    int insert(StatArpStaffPromotion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff_promotion
     *
     * @mbggenerated
     */
    int insertSelective(StatArpStaffPromotion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff_promotion
     *
     * @mbggenerated
     */
    StatArpStaffPromotion selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff_promotion
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StatArpStaffPromotion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_arp_staff_promotion
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StatArpStaffPromotion record);
}