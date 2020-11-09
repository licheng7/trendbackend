package cn.arp.trend.repository.biz.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ContrastPatentManualMapper {

    List<HashMap<String, Object>> contrastByField1(
            Map<String, Object> params
    );

    List<HashMap<String, Object>> contrastByField2(
            Map<String, Object> params
    );

    List<HashMap<String, Object>> contrastByUnit1(
            Map<String, Object> params
    );
    List<HashMap<String, Object>> contrastByUnit2(
            Map<String, Object> params
    );
}