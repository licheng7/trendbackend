package cn.arp.trend.repository.biz.manual;

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
}