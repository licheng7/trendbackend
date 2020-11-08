package cn.arp.trend.repository.biz.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ContrastCustomManualMapper {

    List<HashMap<String, Object>> getAffiliations();

    List<HashMap<String, Object>> getUserTags(String userId);

    List<HashMap<String, Object>> getFieldAffiliations(String userId, String fieldId);

    int addField1(Map<String, Object> params);
    int addField2(Map<String, Object> params);

    int updateRelationFieldUser(Map<String, Object> params);
    int deleteRelationFieldAffiliation(Map<String, Object> params);
    int addRelationFieldAffiliation(Map<String, Object> params);

    int deleteRelationFieldUser(Map<String, Object> params);

}