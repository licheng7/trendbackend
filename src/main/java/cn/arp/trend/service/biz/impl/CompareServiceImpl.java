package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DTO.FacilityInfoDTO;
import cn.arp.trend.data.model.DTO.FinanceInfoDTO;
import cn.arp.trend.data.model.DTO.FundsInfoDTO;
import cn.arp.trend.entity.biz.CasPxxCzbk;
import cn.arp.trend.entity.biz.CasPxxJfbj;
import cn.arp.trend.repository.biz.manual.CasPxxCzbkManualMapper;
import cn.arp.trend.repository.biz.manual.CasPxxJfbjManualMapper;
import cn.arp.trend.service.biz.CompareService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/9
 * Time:上午12:26
 **/
@Service
public class CompareServiceImpl implements CompareService {

    @Resource
    private CasPxxJfbjManualMapper casPxxJfbjManualMapper;

    @Resource
    private CasPxxCzbkManualMapper casPxxCzbkManualMapper;

    @Override
    public FundsInfoDTO fundsQuery(String startYear, String endYear) {

        List<String> yearlist = this.buildYearlist(startYear, endYear);

        List<CasPxxJfbj> casPxxJfbjList = casPxxJfbjManualMapper.queryFunds(startYear, endYear);

        List<String> nameList = casPxxJfbjList.stream().filter(obj -> obj.getName() != null).map
                (CasPxxJfbj::getName).distinct().collect(Collectors.toList());

        Map<String, Map<String, Double>> detail = this.initDetail(nameList, yearlist);

        for(CasPxxJfbj casPxxJfbj : casPxxJfbjList) {
            if(detail.containsKey(casPxxJfbj.getName())) {
                Map<String, Double> _funds = detail.get(casPxxJfbj.getName());
                if(_funds.containsKey(casPxxJfbj.getYear())) {
                    _funds.put(casPxxJfbj.getYear(), casPxxJfbj.getAmount());
                }
            }
        }

        return new FundsInfoDTO(yearlist, detail, "2019年10月");
    }

    @Override
    public FinanceInfoDTO financeQuery(String startYear, String endYear) {

        List<String> yearlist = this.buildYearlist(startYear, endYear);

        List<CasPxxCzbk> casPxxCzbkList = casPxxCzbkManualMapper.queryFinance(startYear, endYear);

        List<String> nameList = casPxxCzbkList.stream().filter(obj -> obj.getName() != null).map
                (CasPxxCzbk::getName).distinct().collect(Collectors.toList());

        Map<String, Map<String, Double>> detail = this.initDetail(nameList, yearlist);

        for(CasPxxCzbk casPxxCzbk : casPxxCzbkList) {
            if(detail.containsKey(casPxxCzbk.getName())) {
                Map<String, Double> _funds = detail.get(casPxxCzbk.getName());
                if(_funds.containsKey(casPxxCzbk.getYear())) {
                    _funds.put(casPxxCzbk.getYear(), casPxxCzbk.getAmount());
                }
            }
        }

        return new FinanceInfoDTO(yearlist, detail, "2019年10月");
    }

    @Override
    public FacilityInfoDTO facilityQuery(String startYear, String endYear) {

        return null;
    }

    private List<String> buildYearlist(String startYear, String endYear) {
        List<String> yearlist = Lists.newArrayList();
        int _startYear = Integer.valueOf(startYear);
        int _endYear = Integer.valueOf(endYear);
        while(_startYear <= _endYear) {
            yearlist.add(String.valueOf(_startYear));
            _startYear ++;
        }
        return yearlist;
    }

    private Map<String, Map<String, Double>> initDetail(List<String> nameList, List<String> yearlist) {
        Map<String, Map<String, Double>> detail = Maps.newHashMap();
        for(String name : nameList) {
            Map<String, Double> _funds = Maps.newHashMap();
            for(String year : yearlist) {
                _funds.put(year, 0D);
            }
            detail.put(name.equals("中科院") ? "中国科学院" : name, _funds);
        }
        return detail;
    }
}
