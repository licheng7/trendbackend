package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;
import cn.arp.trend.entity.biz.RefOrgType;
import cn.arp.trend.repository.biz.manual.RefOrgTypeManualMapper;
import cn.arp.trend.service.biz.BasicService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午11:43
 **/
@Service
public class BasicServiceImpl implements BasicService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy");

    @Resource
    private RefOrgTypeManualMapper refOrgTypeManualMapper;

    @Override
    public OrgInfoDTO orgInfoQuery(OrgInfoQueryDO orgInfoQueryDO) {
        // List<RefOrgType> list1 = refOrgTypeManualMapper.queryOrgByJGBHAndResearch(orgInfoQuery);
        List<String> list2 = refOrgTypeManualMapper.queryResearchByJGBHAndResearch(orgInfoQueryDO);
        List<RefOrgType> list3 = refOrgTypeManualMapper.queryOrgAndResearchByAll();

        List<OrgInfoDTO.OrgAndResearchDTO> data = Lists.newArrayList();
        for(RefOrgType refOrgType : list3) {
            data.add(new OrgInfoDTO.OrgAndResearchDTO(
                    refOrgType.getJgbh(), refOrgType.getJgmc(), refOrgType.getResearchField()));
        }

        OrgInfoDTO result = new OrgInfoDTO();
        result.setFields(list2);
        result.setInstitutions(data);
        return result;
    }

    @Override
    public List<String> queryYear() {
        List<String> result = Lists.newArrayList();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        for(int i=0; i<10; i++) {
            result.add(simpleDateFormat.format(cal.getTime()));
            cal.add(Calendar.YEAR, -1);
        }

        return result;
    }
}
