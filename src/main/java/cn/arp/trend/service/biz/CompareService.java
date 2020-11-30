package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午12:26
 **/
public interface CompareService {

    FundsInfoDTO fundsQuery(String startYear, String endYear);

    FundsInfoDTO newfundsQuery(String startYear, String endYear, String ysYear);

    FinanceInfoDTO financeQuery(String startYear, String endYear);

    FinanceInfoDTO newfinanceQuery(String startYear, String endYear, String ysYear);

    PaperInfoDTO paperQuery(String startYear, String endYear);

    ScientistInfoDTO scientistQuery(String startYear, String endYear);

    FacilityInfoDTO facilityQuery();

    DevelopmentInfoDTO developmentQuery();

    NationalAwardInfoDTO nationalAwardQuery();

    ProjectInfoDTO projectQuery(ProjectQueryDO projectQuery) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
