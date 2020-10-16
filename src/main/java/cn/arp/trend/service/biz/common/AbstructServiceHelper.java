package cn.arp.trend.service.biz.common;

import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/14
 * Time:下午9:29
 **/
public class AbstructServiceHelper {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy");

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
}
