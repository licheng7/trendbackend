package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.CasAcademicianCaeForeign;

public interface CasAcademicianCaeForeignMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_academician_cae_foreign
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_academician_cae_foreign
     *
     * @mbggenerated
     */
    int insert(CasAcademicianCaeForeign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_academician_cae_foreign
     *
     * @mbggenerated
     */
    int insertSelective(CasAcademicianCaeForeign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_academician_cae_foreign
     *
     * @mbggenerated
     */
    CasAcademicianCaeForeign selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_academician_cae_foreign
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CasAcademicianCaeForeign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_academician_cae_foreign
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CasAcademicianCaeForeign record);
}