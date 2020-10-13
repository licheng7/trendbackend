package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.entity.biz.*;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.CompareService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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

    @Resource
    private StatHcauthorsCountManualMapper statHcauthorsCountManualMapper;

    @Resource
    private StatChinaAward10yearFinalCountManualMapper statChinaAward10yearFinalCountManualMapper;

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
    public PaperInfoDTO paperQuery(String startYear, String endYear) {

        List<String> yearlist = this.buildYearlist(startYear, endYear);

        List<String> newYearlist = yearlist.stream().map(str -> str+"年").collect(Collectors.toList());

        List<CasPxxJcptCdsysXwPxLwKxjFmjJbj> paperResultList =
                casPxxJcptCdsysXwPxLwKxjFmjJbjManualMapper.queryPaper(startYear, endYear);

        List<String> nameList = paperResultList.stream().filter(obj -> obj.getName() != null).map
                (CasPxxJcptCdsysXwPxLwKxjFmjJbj::getName).distinct().collect(Collectors.toList());

        Map<String, Map<String, Double>> detail = this.initDetail(nameList, newYearlist);

        for(CasPxxJcptCdsysXwPxLwKxjFmjJbj paper : paperResultList) {
            if(detail.containsKey(paper.getName())) {
                Map<String, Double> _paper = detail.get(paper.getName());
                if(_paper.containsKey(paper.getDate())) {
                    _paper.put(paper.getDate(), paper.getCount());
                }
            }
        }

        PaperInfoDTO paperInfo = new PaperInfoDTO();
        paperInfo.setYear(newYearlist);
        paperInfo.setPaperUpdateTimeLw("2019年10月");
        paperInfo.setPaperUpdateTimeGby("2019年10月");

        List<MapResultDTO> detailList = Lists.newArrayList();
        detail.entrySet().stream().forEach(obj -> detailList.add(new MapResultDTO(obj.getKey(),
                obj.getValue())));

        List<MapResultDTO> newDetailList = Lists.newArrayList();
        newDetailList.add(detailList.get(4));
        newDetailList.add(detailList.get(0));
        newDetailList.add(detailList.get(1));
        newDetailList.add(detailList.get(2));
        newDetailList.add(detailList.get(3));
        newDetailList.add(detailList.get(5));

        paperInfo.setDetail(newDetailList);

        return paperInfo;
    }

    @Override
    public ScientistInfoDTO scientistQuery(String startYear, String endYear) {

        List<String> yearlist = this.buildYearlist(startYear, endYear);

        List<String> nameList = Lists.newArrayList("中国科学院", "C9高校", "中国农业科学院", "中国医学科学院", "中国林业科学院", "国家海洋局", "其他");
        List<String> listC9 = Lists.newArrayList("清华大学", "浙江大学", "北京大学", "复旦大学", "中国科技大学", "上海交通大学", "南京大学", "西安交通大学", "哈尔滨工业大学");

        Map<String, Map<String, MapResultDTO<String, Integer>>> detail = this.initDetail2(nameList, yearlist,
                Integer.class, 0);
        List<StatHcauthorsCount> resultList = statHcauthorsCountManualMapper
                .queryScientist1(startYear, endYear);
        for(StatHcauthorsCount statHcauthorsCount : resultList) {
            String key;
            if(detail.containsKey(statHcauthorsCount.getInstitutionCh())) {
                key = statHcauthorsCount.getInstitutionCh();
            } else {
                if(-1 != listC9.indexOf(statHcauthorsCount.getInstitutionCh())) {
                    key = "C9高校";
                } else {
                    key = "其他";
                }
            }
            Map<String, MapResultDTO<String, Integer>> map = detail.get(key);
            if(map.containsKey(statHcauthorsCount.getNf())) {
                map.get(statHcauthorsCount.getNf()).setValue(map.get(statHcauthorsCount.getNf()).getValue() +
                        statHcauthorsCount.getTotal());
            }
        }


        List<StatHcauthorsCount> resultWorld = statHcauthorsCountManualMapper
                .queryScientist2(startYear, endYear);
        List<String> countryName = resultWorld.stream().map(obj -> obj.getCountryCh()).distinct().collect
                (Collectors.toList());
        Map<String, Map<String, MapResultDTO<String, Integer>>> worldtotal = this.initDetail2(countryName, yearlist,
                Integer.class, 0);
        for(StatHcauthorsCount statHcauthorsCount : resultWorld) {
            if(worldtotal.containsKey(statHcauthorsCount.getCountryCh())) {
                Map<String, MapResultDTO<String, Integer>> map = worldtotal.get(statHcauthorsCount
                        .getCountryCh());
                if(map.containsKey(statHcauthorsCount.getNf())) {
                    map.get(statHcauthorsCount.getNf()).setValue(map.get(statHcauthorsCount.getNf()).getValue() +
                            statHcauthorsCount.getTotal());
                }
            }
        }


        List<Map<String, Object>> totalFive = statHcauthorsCountManualMapper
                .queryScientist3(startYear, endYear);
        List<String> totalFiveName = Lists.newArrayList();
        totalFive.stream().forEach(obj -> totalFiveName.add((String)obj.get("country_ch")));
        List<MapResultDTO<String, Integer>> otherList = Lists.newArrayList();
        List<MapResultDTO<String, List<MapResultDTO<String, Integer>>>> newWorldlist = Lists.newArrayList();
        for(String key : worldtotal.keySet()) {
            if(totalFiveName.indexOf(key) != -1) {
                for(String year : yearlist) {
                    otherList.add(new MapResultDTO<String, Integer>(year, worldtotal.get(key).get(year)
                            .getValue()));
                }
            } else {
                List<MapResultDTO<String, Integer>> list = Lists.newArrayList();
                worldtotal.get(key).entrySet().stream().forEach(obj -> list.add(obj.getValue()));
                newWorldlist.add(new MapResultDTO(key, list));
            }
        }
        newWorldlist.add(new MapResultDTO("其他", otherList));


        /*List<Map<String, Object>> newFive = statHcauthorsCountManualMapper
                .queryScientist4(startYear, endYear);*/

        ScientistInfoDTO scientistInfo = new ScientistInfoDTO();
        scientistInfo.setYear(yearlist);
        List<MapResultDTO<String, List<Integer>>> domestic = Lists.newArrayList();
        detail.entrySet().stream().forEach(obj -> {
            String key = obj.getKey();
            List<Integer> value = Lists.newArrayList();
            obj.getValue().entrySet().stream().forEach(map -> value.add(map.getValue().getValue()));
            domestic.add(new MapResultDTO<String, List<Integer>>(key, value));
        });
        scientistInfo.setDomestic(domestic);

        List<MapResultDTO<String, List<Integer>>> _newWorldlist = Lists.newArrayList();
        newWorldlist.stream().forEach(obj -> {
            String key = obj.getName();
            List<Integer> list = Lists.newArrayList();
            List<MapResultDTO<String, Integer>> value = obj.getValue();
            value.stream().forEach(obj2 -> list.add(obj2.getValue()));
            _newWorldlist.add(new MapResultDTO<String, List<Integer>>(key, list));
        });
        scientistInfo.setNewWorldlist(_newWorldlist);

        return scientistInfo;
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
    public DevelopmentInfoDTO developmentQuery() {

        List<CasPxxJcptCdsysXwPxLwKxjFmjJbj> queryResult1 =
                casPxxJcptCdsysXwPxLwKxjFmjJbjManualMapper.queryDevelopment1();

        List<String> institution = queryResult1.stream().map(obj -> obj.getName()).distinct()
                .collect(Collectors.toList());

        List<Double> zrkx = Lists.newArrayList();
        List<Double> jsfm = Lists.newArrayList();
        List<Double> jsjb = Lists.newArrayList();

        queryResult1.stream().forEach(obj -> {
            if(obj.getType().equals("自然科学奖")) {
                zrkx.add(obj.getCount());
            } else if(obj.getType().equals("技术发明奖")) {
                jsfm.add(obj.getCount());
            } else if(obj.getType().equals("技术进步奖")) {
                jsjb.add(obj.getCount());
            }
        });

        List<MapResultDTO<String, Double>> kj = Lists.newArrayList();
        List<MapResultDTO<String, Double>> kx = Lists.newArrayList();

        List<CasPxxJcptCdsysXwPxLwKxjFmjJbj> queryResult2 =
                casPxxJcptCdsysXwPxLwKxjFmjJbjManualMapper.queryDevelopment2();

        queryResult2.stream().forEach(obj -> {
                if(obj.getType().equals("新闻")) {
                    kj.add(new MapResultDTO<String, Double>(obj.getName(), obj.getCount()));
                } else {
                    kx.add(new MapResultDTO<String, Double>(obj.getName(), obj.getCount()));
                }
            }
        );

        List<MapResultDTO> newkj = Lists.newArrayList();
        newkj.add(kj.get(4));
        newkj.add(kj.get(0));
        newkj.add(kj.get(1));
        newkj.add(kj.get(2));
        newkj.add(kj.get(3));
        newkj.add(kj.get(5));

        List<MapResultDTO> newkx = Lists.newArrayList();
        newkx.add(kx.get(4));
        newkx.add(kx.get(0));
        newkx.add(kx.get(1));
        newkx.add(kx.get(2));
        newkx.add(kx.get(3));
        newkx.add(kx.get(5));

        DevelopmentInfoDTO developmentInfo = new DevelopmentInfoDTO();
        developmentInfo.setInstitution(institution);
        developmentInfo.setZrkx(zrkx);
        developmentInfo.setJsfm(jsfm);
        developmentInfo.setJsjb(jsjb);
        developmentInfo.setNewkj(newkj);
        developmentInfo.setNewkx(newkx);
        developmentInfo.setJsfmUpdateTime("2019年10月");
        developmentInfo.setZrkxUpdateTime("2019年10月");
        developmentInfo.setJsjbUpdateTime("2019年10月");
        developmentInfo.setKyyxUpdateTime("2019年10月");

        return developmentInfo;
    }

    @Override
    public NationalAwardInfoDTO nationalAwardQuery() {

        List<Map<String, Object>> queryResult1 = statChinaAward10yearFinalCountManualMapper
                .queryNationalAward1();

        List<Map<String, Object>> queryResult2 = statChinaAward10yearFinalCountManualMapper
                .queryNationalAward2();

        List<String> institution = Lists.newArrayList();
        queryResult1.stream().forEach(
                obj -> {
                    if(obj.get("jgmc") != null) {
                        institution.add((String) obj.get("jgmc"));
                    }
                }
        );
        List<String> distinctInstitution = institution.stream().map(str -> str).distinct().collect
                (Collectors.toList());

        List<Long> zrkx = Lists.newArrayList();
        List<Long> jsfm = Lists.newArrayList();
        List<Long> jsjb = Lists.newArrayList();

        for(int i = 0; i < distinctInstitution.size(); i++) {
            zrkx.add(0L);
            jsfm.add(0L);
            jsjb.add(0L);
        }

        queryResult1.stream().forEach(
                obj -> {
                    if(obj.get("jgmc") != null) {
                        String jgmc = (String) obj.get("jgmc");
                        int index = distinctInstitution.indexOf(jgmc);
                        if(index != -1) {
                            if(obj.get("jxlb") != null) {
                                String jxlb = (String) obj.get("jxlb");
                                if (jxlb.equals("国家自然科学奖")) {
                                    zrkx.set(index, ((BigDecimal) obj.get("num")).longValue());
                                } else if (jxlb.equals("国家技术发明奖")) {
                                    jsfm.set(index, ((BigDecimal) obj.get("num")).longValue());
                                } else if (jxlb.equals("国家科学技术进步奖")) {
                                    jsjb.set(index, ((BigDecimal) obj.get("num")).longValue());
                                }
                            }
                        }
                    }
                }
        );

        institution.add("中国科学院");

        queryResult2.stream().forEach(
                obj -> {
                    if(obj.get("jxlb") != null) {
                        String jxlb = (String) obj.get("jxlb");
                        if(jxlb.equals("国家自然科学奖")) {
                            zrkx.add(((BigDecimal) obj.get("num")).longValue());
                        } else if (jxlb.equals("国家技术发明奖")) {
                            jsfm.add(((BigDecimal) obj.get("num")).longValue());
                        } else if (jxlb.equals("国家科学技术进步奖")) {
                            jsjb.add(((BigDecimal) obj.get("num")).longValue());
                        }
                    }
                }
        );

        NationalAwardInfoDTO nationalAwardInfo = new NationalAwardInfoDTO();
        nationalAwardInfo.setInstitution(institution);
        nationalAwardInfo.setZrkx(zrkx);
        nationalAwardInfo.setJsfm(jsfm);
        nationalAwardInfo.setJsjb(jsjb);
        nationalAwardInfo.setResultArray(Lists.newArrayList(queryResult1, queryResult2));
        nationalAwardInfo.setZrkxUpdateTime("2019年10月");
        nationalAwardInfo.setJsfmUpdateTime("2019年10月");
        nationalAwardInfo.setJsjbUpdateTime("2019年10月");
        nationalAwardInfo.setKyyxUpdateTime("2019年10月");

        return nationalAwardInfo;
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

    private void initProjectInfoDTO(ProjectInfoDTO projectInfo) {
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

    private <V> Map<String, Map<String, MapResultDTO<String, V>>> initDetail2(
            List<String> nameList, List<String> yearlist, Class<V> v, V defaultValue) {
        Map<String, Map<String, MapResultDTO<String, V>>> detail = Maps.newHashMap();
        for(String name : nameList) {
            Map<String, MapResultDTO<String, V>> map = Maps.newHashMap();
            for(String year : yearlist) {
                map.put(year, new MapResultDTO(year, defaultValue));
            }
            detail.put(name, map);
        }
        return detail;
    }
}
