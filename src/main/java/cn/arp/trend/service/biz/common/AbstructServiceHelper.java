package cn.arp.trend.service.biz.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/14
 * Time:下午9:29
 **/
public class AbstructServiceHelper {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy");

    public static NumberFormat nf = NumberFormat.getNumberInstance();

    public static final String UPDATETIME = "2019年12月";

    static {
        nf.setMaximumFractionDigits(2);
    }

    public List<String> buildYearlist(String startYear, String endYear) {
        List<String> yearlist = Lists.newArrayList();
        int _startYear = Integer.valueOf(startYear);
        int _endYear = Integer.valueOf(endYear);
        while(_startYear <= _endYear) {
            yearlist.add(String.valueOf(_startYear));
            _startYear ++;
        }
        return yearlist;
    }

    public List<String> buildYearlist(int startYear, int endYear) {
        List<String> yearlist = Lists.newArrayList();
        int _startYear = startYear;
        int _endYear = endYear;
        while(_startYear <= _endYear) {
            yearlist.add(String.valueOf(_startYear));
            _startYear ++;
        }
        return yearlist;
    }

    public Double doubleFormat(Double d) {
        BigDecimal b = new BigDecimal(d);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 便利bizList，提取list元素指定key作为新map的key，并且多个key重复时，只保留第一个
     * @param bizList
     * @param key
     * @return
     */
    public Map<String, Map<String, Object>> bizData4MapByKeyLimit1(List<Map<String, Object>>
                                                                            bizList, String key) {
        Map<String, Map<String, Object>> bizDataMap = Maps.newHashMap();
        bizList.stream().forEach(map -> {
            String str = (String) map.get(key);
            if(!bizDataMap.containsKey(str)) {
                bizDataMap.put(str, map);
            }
        });
        return bizDataMap;
    }

    /**
     * 便利bizList，提取list元素指定key作为新map的key，并且多个key重复时，保留最后一个
     * @param bizList
     * @param key
     * @return
     */
    public Map<String, Map<String, Object>> bizData4MapByKeyLast(List<Map<String, Object>>
                                                                           bizList, String key) {
        Map<String, Map<String, Object>> bizDataMap = Maps.newHashMap();
        bizList.stream().forEach(map -> {
            String nf = (String) map.get(key);
            bizDataMap.put(nf, map);
        });
        return bizDataMap;
    }
}
