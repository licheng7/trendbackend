package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.StatXdzx;

public interface StatXdzxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_xdzx
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_xdzx
     *
     * @mbggenerated
     */
    int insert(StatXdzx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_xdzx
     *
     * @mbggenerated
     */
    int insertSelective(StatXdzx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_xdzx
     *
     * @mbggenerated
     */
    StatXdzx selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_xdzx
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StatXdzx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stat_xdzx
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StatXdzx record);
}