package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.DoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DTO.DoctoralSupervisorInfoDTO;
import cn.arp.trend.repository.biz.manual.CasEduFTeacherInfoManualMapper;
import cn.arp.trend.service.biz.DetailMentorService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午10:43
 **/
@Service
public class DetailMentorServiceImpl implements DetailMentorService {

    @Resource
    private CasEduFTeacherInfoManualMapper casEduFTeacherInfoManualMapper;


    @Override
    public DoctoralSupervisorInfoDTO doctoralupervisorQuery(DoctoralSupervisorQueryDO query) {

        List<Map<String, Object>> distributionAgeList = casEduFTeacherInfoManualMapper
                .distributionAge(query);

        List<Map> distributionAge = Lists.newArrayList();

        distributionAgeList.stream().forEach(map -> {
            int age = ((Number) map.get("age")).intValue();
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("age", age);
            _map.put("number", number);
            distributionAge.add(_map);
        });

        List<Map<String, Object>> distributionFieldList = casEduFTeacherInfoManualMapper
                .distributionField(query);

        List<Map> distributionField = Lists.newArrayList();

        distributionFieldList.stream().forEach(map -> {
            String field = (String) map.get("field");
            int number = (Integer) map.get("number");
            Map<String, Object> _map = Maps.newHashMap();
            _map.put("field", field);
            _map.put("number", number);
            distributionField.add(_map);
        });

        DoctoralSupervisorInfoDTO result = new DoctoralSupervisorInfoDTO();
        result.setDistributionAge(distributionAge);
        result.setDistributionField(distributionField);

        return result;
    }
}
