package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.CasPxxJfbj;

public interface CasPxxJfbjMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_pxx_jfbj
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_pxx_jfbj
     *
     * @mbggenerated
     */
    int insert(CasPxxJfbj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_pxx_jfbj
     *
     * @mbggenerated
     */
    int insertSelective(CasPxxJfbj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_pxx_jfbj
     *
     * @mbggenerated
     */
    CasPxxJfbj selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_pxx_jfbj
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CasPxxJfbj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_pxx_jfbj
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CasPxxJfbj record);
}