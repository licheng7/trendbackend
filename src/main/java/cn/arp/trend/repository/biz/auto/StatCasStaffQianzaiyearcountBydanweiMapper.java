package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.StatCasStaffQianzaiyearcountBydanwei;
import cn.arp.trend.entity.biz.StatCasStaffQianzaiyearcountBydanweiKey;

public interface StatCasStaffQianzaiyearcountBydanweiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_cas_staff_qianzaiyearcount_bydanwei
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(StatCasStaffQianzaiyearcountBydanweiKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_cas_staff_qianzaiyearcount_bydanwei
     *
     * @mbggenerated
     */
    int insert(StatCasStaffQianzaiyearcountBydanwei record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_cas_staff_qianzaiyearcount_bydanwei
     *
     * @mbggenerated
     */
    int insertSelective(StatCasStaffQianzaiyearcountBydanwei record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_cas_staff_qianzaiyearcount_bydanwei
     *
     * @mbggenerated
     */
    StatCasStaffQianzaiyearcountBydanwei selectByPrimaryKey(StatCasStaffQianzaiyearcountBydanweiKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_cas_staff_qianzaiyearcount_bydanwei
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StatCasStaffQianzaiyearcountBydanwei record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_cas_staff_qianzaiyearcount_bydanwei
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StatCasStaffQianzaiyearcountBydanwei record);
}