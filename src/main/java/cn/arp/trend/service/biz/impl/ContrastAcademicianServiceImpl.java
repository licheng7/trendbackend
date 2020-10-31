package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.data.model.DO.ForeignQueryDO;
import cn.arp.trend.data.model.DTO.DACompareInfoDTO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;
import cn.arp.trend.data.model.DTO.MapResultDTO;
import cn.arp.trend.repository.biz.manual.CasAcademicianChinaManualMapper;
import cn.arp.trend.repository.biz.manual.CasAcademicianForeignManualMapper;
import cn.arp.trend.repository.biz.manual.ContrastAcademicianManualMapper;
import cn.arp.trend.service.biz.ContrastAcademicianService;
import cn.arp.trend.service.biz.ContrastBaseService;
import cn.arp.trend.service.biz.DetailAcademicianService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:48
 **/
@Service
public class ContrastAcademicianServiceImpl implements ContrastAcademicianService {

    @Resource
    private ContrastAcademicianManualMapper contrastAcademicianManualMapper;

    @Override
    public Object byField(String userId, String startYear, String endYear, List<String> fieldIds) {

        String fieldIdsStr = " in (" + String.join(",", fieldIds) +  ")";
        contrastAcademicianManualMapper.contrastByField(userId, startYear, endYear, fieldIdsStr);
        return null;
    }

    @Override
    public Object byUnit(String userId, String startYear, String endYear, List<String> jgbhs) {
        return null;
    }
}
