package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DTO.FundsInfoDTO;
import cn.arp.trend.entity.biz.CasPxxJfbj;
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

    @Override
    public FundsInfoDTO fundsQuery(String startYear, String endYear) {

        List<String> yearlist = Lists.newArrayList();
        int _startYear = Integer.valueOf(startYear);
        int _endYear = Integer.valueOf(endYear);
        while(_startYear <= _endYear) {
            yearlist.add(String.valueOf(_startYear));
            _startYear ++;
        }

        List<CasPxxJfbj> casPxxJfbjList = casPxxJfbjManualMapper.queryFunds(startYear, endYear);

        List<String> nameList = casPxxJfbjList.stream().filter(obj -> obj.getName() != null).map
                (CasPxxJfbj::getName).distinct().collect(Collectors.toList());

        Map<String, Map<String, Double>> detail = Maps.newHashMap();

        for(String name : nameList) {
            Map<String, Double> _funds = Maps.newHashMap();
            for(String year : yearlist) {
                _funds.put(year, 0D);
            }
            detail.put(name.equals("中科院") ? "中国科学院" : name, _funds);
        }

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
}
