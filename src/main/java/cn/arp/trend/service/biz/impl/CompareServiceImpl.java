package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.entity.biz.CasPxxCzbk;
import cn.arp.trend.entity.biz.CasPxxJcptCdsysXwPxLwKxjFmjJbj;
import cn.arp.trend.entity.biz.CasPxxJfbj;
import cn.arp.trend.entity.biz.CompareProjectObj;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.CompareService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy");

    @Resource
    private CasPxxJfbjManualMapper casPxxJfbjManualMapper;

    @Resource
    private CasPxxCzbkManualMapper casPxxCzbkManualMapper;

    @Resource
    private CasPxxJcptCdsysXwPxLwKxjFmjJbjManualMapper casPxxJcptCdsysXwPxLwKxjFmjJbjManualMapper;

    @Resource
    private StatNsfcProjectManualMapper statNsfcProjectManualMapper;

    @Resource
    private StatMostProjectManualMapper statMostProjectManualMapper;

    @Resource
    private StatXdzxManualMapper statXdzxManualMapper;

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
    public FacilityInfoDTO facilityQuery() {

        List<CasPxxJcptCdsysXwPxLwKxjFmjJbj> casPxxJcptCdsysXwPxLwKxjFmjJbjList =
            casPxxJcptCdsysXwPxLwKxjFmjJbjManualMapper.queryFacility();

        List<String> yearList = casPxxJcptCdsysXwPxLwKxjFmjJbjList.stream().filter(obj -> obj.getDate() != null).map
                (CasPxxJcptCdsysXwPxLwKxjFmjJbj::getDate).distinct().collect(Collectors.toList());

        List<String> nameList = casPxxJcptCdsysXwPxLwKxjFmjJbjList.stream().filter(obj -> obj.getName() != null).map
                (CasPxxJcptCdsysXwPxLwKxjFmjJbj::getName).distinct().collect(Collectors.toList());

        Map<String, Map<String, Double>> totalPlatform = Maps.newHashMap();
        Map<String, Map<String, Double>> totalKeylab = Maps.newHashMap();
        nameList.stream().forEach(str -> {
            totalPlatform.put(str, this.initYearMap(yearList));
            totalKeylab.put(str, this.initYearMap(yearList));
        });

        for(CasPxxJcptCdsysXwPxLwKxjFmjJbj casPxxJcptCdsysXwPxLwKxjFmjJbj :
                casPxxJcptCdsysXwPxLwKxjFmjJbjList) {
            if(casPxxJcptCdsysXwPxLwKxjFmjJbj.getType().equals("国家基础平台")) {
                Map<String, Double> _map = totalPlatform.get(casPxxJcptCdsysXwPxLwKxjFmjJbj
                        .getName());
                _map.put(casPxxJcptCdsysXwPxLwKxjFmjJbj.getDate(), casPxxJcptCdsysXwPxLwKxjFmjJbj
                        .getCount());
            } else if(casPxxJcptCdsysXwPxLwKxjFmjJbj.getType().equals("国家重点实验室")) {
                Map<String, Double> _map = totalKeylab.get(casPxxJcptCdsysXwPxLwKxjFmjJbj
                        .getName());
                _map.put(casPxxJcptCdsysXwPxLwKxjFmjJbj.getDate(), casPxxJcptCdsysXwPxLwKxjFmjJbj
                        .getCount());
            }
        }

        List<MapResultDTO> platformList = Lists.newArrayList();
        for(String name : totalPlatform.keySet()) {
            if(name.equals("中科院") || name.equals("C9高校")) {
                List<Double> countList = Lists.newArrayList();
                Map<String, Double> map = totalPlatform.get(name);
                map.entrySet().stream().forEach(obj -> countList.add(obj.getValue()));
                platformList.add(new MapResultDTO<String, List<Double>>(
                        name.equals("中科院") ? "中国科学院" : "中科院", countList));
            }
        }

        List<MapResultDTO> keylabList = Lists.newArrayList();
        for(String name : totalKeylab.keySet()) {
            if(name.equals("中国科学院") || name.equals("C9高校")) {
                List<Double> countList = Lists.newArrayList();
                Map<String, Double> map = totalKeylab.get(name);
                map.entrySet().stream().forEach(obj -> countList.add(obj.getValue()));
                keylabList.add(new MapResultDTO<String, List<Double>>(name, countList));
            }
        }

        return new FacilityInfoDTO(yearList, platformList, keylabList, "2019年10月", "2019年10月");
    }

    @Override
    public ProjectInfoDTO projectQuery(ProjectQueryDO projectQuery) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ProjectInfoDTO projectInfo = new ProjectInfoDTO();
        this.initProjectInfoDTO(projectInfo);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, -1);
        String endYearStr = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.YEAR, -4);
        String startYearStr = simpleDateFormat.format(calendar.getTime());

        int startYearInt = Integer.valueOf(startYearStr);
        int endYearInt = Integer.valueOf(endYearStr);

        projectQuery.setStartYear(startYearInt);
        projectQuery.setEndYear(endYearInt);

        List<String> yearListStr = Lists.newArrayList();
        List<Integer> yearListInt = Lists.newArrayList();
        Map<String, ProjectInfoDTO.OrderDTO> orderMap = Maps.newHashMap();
        for(int year = startYearInt; year <= endYearInt; year ++) {
            yearListInt.add(year);
            yearListStr.add(String.valueOf(year));
            orderMap.put(String.valueOf(year), projectInfo.new OrderDTO(
                    new MapResultDTO(), new MapResultDTO(), new MapResultDTO()));
        }

        List<CompareProjectObj> nsfcProjectList = statNsfcProjectManualMapper.queryProject
                (projectQuery);
        if(!nsfcProjectList.isEmpty()) {
            this.doProjectQuery(yearListStr, orderMap, nsfcProjectList, projectInfo, ProjectInfoDTO
                    .OrderDTO.class.getMethod("getNsfc"));
        }

        List<CompareProjectObj> mostProjectList = statMostProjectManualMapper.queryProject
                (projectQuery);
        if(!mostProjectList.isEmpty()) {
            this.doProjectQuery(yearListStr, orderMap, mostProjectList, projectInfo, ProjectInfoDTO
                    .OrderDTO.class.getMethod("getStd"));
        }

        List<CompareProjectObj> xdzxProjectList = statXdzxManualMapper.queryProject(projectQuery);
        if(!xdzxProjectList.isEmpty()) {
            this.doProjectQuery(yearListStr, orderMap, xdzxProjectList, projectInfo, ProjectInfoDTO
                    .OrderDTO.class.getMethod("getXd"));
        }

        projectInfo.setNsfcUpdateTime("2019年10月");
        projectInfo.setStdUpdateTime("2019年10月");
        projectInfo.setXdUpdateTime("2019年10月");
        projectInfo.setCategory(Lists.newArrayList("项目", "经费"));

        List<ProjectInfoDTO.OrderDTO> orderList = Lists.newArrayList();
        orderMap.entrySet().stream().forEach(obj -> orderList.add(obj.getValue()));
        projectInfo.setOrder(orderList);

        projectInfo.setYear(yearListStr);

        return projectInfo;
    }

    public void initProjectInfoDTO(ProjectInfoDTO projectInfo) {
        projectInfo.setNsfcProject(Lists.newArrayList());
        projectInfo.setNsfcFunds(Lists.newArrayList());
    }

    private void doProjectQuery(List<String> yearListStr, Map<String, ProjectInfoDTO.OrderDTO>
            orderMap, List<CompareProjectObj> bizDateList, ProjectInfoDTO projectInfo, Method
            method) throws InvocationTargetException, IllegalAccessException {

        Triple<Map, Map, Map> triple = this.initCalculateParam(yearListStr, bizDateList);
        Map<String, Long> xmList = triple.getLeft();
        Map<String, Double> jfList = triple.getMiddle();
        Map<String, CompareProjectObj> recordList = triple.getRight();

        projectInfo.setNsfcNew(xmList.get(yearListStr.get(yearListStr.size()-1)));

        for(String year : xmList.keySet()) {
            projectInfo.getNsfcProject().add(xmList.get(year).toString());
            projectInfo.setNsfcCumulation(projectInfo.getNsfcCumulation() + xmList.get(year));
        }

        for(String year : jfList.keySet()) {
            projectInfo.getNsfcFunds().add(jfList.get(year).toString());
        }

        for(String year : recordList.keySet()) {
            /*orderMap.get(year).getNsfc().setName(Lists.newArrayList(recordList.get(year).getJgmc
                    ()));
            orderMap.get(year).getNsfc().setValue(Lists.newArrayList(recordList.get(year).getXm()));*/

            ProjectInfoDTO.OrderDTO order = orderMap.get(year);
            MapResultDTO<List<String>, List<Long>> map = (MapResultDTO<List<String>, List<Long>>) method.invoke(order);
            if(null != recordList.get(year)) {
                map.setName(Lists.newArrayList(recordList.get(year).getJgmc()));
                map.setValue(Lists.newArrayList(recordList.get(year).getXm()));
            }
        }
    }

    private Triple<Map, Map, Map> initCalculateParam(List<String> yearListStr, List<CompareProjectObj> bizDateList) {

        Map<String, Long> xmList = Maps.newHashMap();   // 每年的项目数
        Map<String, Double> jfList = Maps.newHashMap();   // 每年的经费数
        Map<String, CompareProjectObj> recordList = Maps.newHashMap();   // 每年的记录数

        yearListStr.forEach(
                obj -> {
                    xmList.put(obj, 0L);
                    jfList.put(obj, 0D);
                    recordList.put(obj, null);
                }
        );

        bizDateList.forEach(
                obj -> {
                    if(yearListStr.contains(obj.getNf())) {
                        String year = obj.getNf();
                        xmList.put(year, xmList.get(year) + obj.getXm());
                        jfList.put(year, jfList.get(year) + obj.getJf());
                        recordList.put(year, obj);
                    }
                }
        );

        return Triple.of(xmList, jfList, recordList);
    }

    private Map<String, Double> initYearMap(List<String> yearList) {
        Map<String, Double> yearMap = Maps.newHashMap();
        yearList.stream().forEach(str -> yearMap.put(str, 0D));
        return yearMap;
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
