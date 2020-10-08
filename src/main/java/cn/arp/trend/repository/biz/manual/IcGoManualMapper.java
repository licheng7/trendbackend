package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.GoAnalyseQueryDO;
import cn.arp.trend.entity.biz.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午1:31
 **/
public interface IcGoManualMapper {

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
    List<Unit> queryGoUnit();

    /**
     * 
     * @return
     */
    List<Country> queryGoCountry();

    /**
     *
     * @return
     */
    List<GoAndComeLink> queryGoLink();

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
    List<GoAnalyse> queryGoAnalyse(@Param("request") GoAnalyseQueryDO request);
}
