package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ForeignQueryDO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;
import cn.arp.trend.repository.biz.manual.CasAcademicianForeignManualMapper;
import cn.arp.trend.service.biz.DetailAcademicianService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:48
 **/
@Service
public class DetailAcademicianServiceImpl implements DetailAcademicianService {

    @Resource
    private CasAcademicianForeignManualMapper casAcademicianForeignManualMapper;

    @Override
    public ForeignInfoDTO foreignQuery(List<String> affiliation) {

        ForeignQueryDO foreignQuery = new ForeignQueryDO();
        foreignQuery.setAffiliation(affiliation);
        if(affiliation != null && !affiliation.isEmpty() && affiliation.contains("未知")) {
            foreignQuery.setFlag("OR");
        } else {
            foreignQuery.setFlag("");
        }
        List<Map<String, Object>> queryResult = casAcademicianForeignManualMapper.queryForeign
                (foreignQuery);

        return new ForeignInfoDTO(queryResult);
    }
}
