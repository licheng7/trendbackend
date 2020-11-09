package cn.arp.trend.repository.biz.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ContrastPaperManualMapper {

    List<HashMap<String, Object>> contrastByField(
            Map<String, Object> params
    );

    List<HashMap<String, Object>> contrastByUnit(
            Map<String, Object> params
    );
}