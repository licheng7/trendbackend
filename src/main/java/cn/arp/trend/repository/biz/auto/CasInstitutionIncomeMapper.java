package cn.arp.trend.repository.biz.auto;

import cn.arp.trend.entity.biz.CasInstitutionIncome;

public interface CasInstitutionIncomeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_institution_income
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_institution_income
     *
     * @mbggenerated
     */
    int insert(CasInstitutionIncome record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_institution_income
     *
     * @mbggenerated
     */
    int insertSelective(CasInstitutionIncome record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_institution_income
     *
     * @mbggenerated
     */
    CasInstitutionIncome selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_institution_income
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CasInstitutionIncome record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cas_institution_income
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CasInstitutionIncome record);
}