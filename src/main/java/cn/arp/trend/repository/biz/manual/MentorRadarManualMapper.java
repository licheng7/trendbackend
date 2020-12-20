package cn.arp.trend.repository.biz.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MentorRadarManualMapper {

    List<Map<String, Object>> mentorRadarQuery(
            Map<String, Object> params
    );
}