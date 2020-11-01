package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AreaPatentQueryDO;
import cn.arp.trend.data.model.DO.ZKYChinaPatentQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatPatentNewManualMapper {

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryZKYChinaPatent1(@Param("query") ZKYChinaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryZKYChinaPatent2(@Param("query") ZKYChinaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryZKYChinaPatent3(@Param("query") ZKYChinaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryZKYChinaPatent4(@Param("query") ZKYChinaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent1(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent2(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent3(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent4(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent5(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent6(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent7(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent8(@Param("query") AreaPatentQueryDO query);

    /**
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryAreaPatent9(@Param("query") AreaPatentQueryDO query);
}