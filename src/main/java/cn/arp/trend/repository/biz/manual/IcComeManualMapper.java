package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.ComeAnalyseQueryDO;
import cn.arp.trend.entity.biz.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:31
 **/
public interface IcComeManualMapper {

    /**
     *
     * @return
     */
    List<String> queryCountry();

    /**
     *
     * @return
     */
    List<String> queryNationality();

    /**
     *
     * @return
     */
    List<String> queryForm();

    /**
     *
     * @return
     */
    List<String> queryAgeYear();

    /**
     *
     * @return
     */
    List<Rank> queryRank();

    /**
     *
     * @return
     */
    List<Unit> queryComeUnit();

    /**
     *
     * @return
     */
    List<Country> queryComeCountry();

    /**
     *
     * @return
     */
    List<GoAndComeLink> queryComeLink();

    /**
     *
     * @return
     */
    List<String> queryDistinctCountry();

    /**
     *
     * @return
     */
    List<CountryAndNationality> queryCountryAndNationality();

    /**
     *
     * @param request
     * @return
     */
    List<GoAndComeAnalyse> queryComeAnalyse(@Param("request") ComeAnalyseQueryDO request);
}
