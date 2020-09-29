package cn.arp.trend.repository.biz.manual;

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
}
