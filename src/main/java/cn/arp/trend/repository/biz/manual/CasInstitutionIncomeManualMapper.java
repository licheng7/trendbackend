package cn.arp.trend.repository.biz.manual;

import cn.arp.trend.data.model.DO.AssetIncomeQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CasInstitutionIncomeManualMapper {

    List<Map<String, Object>> queryAssetIncome(@Param("query") AssetIncomeQueryDO query);
}