package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.entity.biz.CasPxxJcptCdsysXwPxLwKxjFmjJbj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:下午5:06
 **/
public interface CasPxxJcptCdsysXwPxLwKxjFmjJbjManualMapper {

    /**
     *
     * @return
     */
    List<CasPxxJcptCdsysXwPxLwKxjFmjJbj> queryFacility();

    /**
     *
     * @param startYear
     * @param endYear
     * @return
     */
    List<CasPxxJcptCdsysXwPxLwKxjFmjJbj> queryPaper(@Param("startYear") String startYear, @Param("endYear") String
            endYear);
}
