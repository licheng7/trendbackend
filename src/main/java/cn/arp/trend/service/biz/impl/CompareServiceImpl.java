package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.entity.biz.*;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.CompareService;
import cn.arp.trend.service.biz.common.AbstructServiceHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
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
public class CompareServiceImpl extends AbstructServiceHelper implements CompareService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy");

    static NumberFormat nf = NumberFormat.getInstance();

    static {
        nf.setMaximumFractionDigits(5);
        nf.setGroupingUsed(false);
    }

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

    @Resource
    private CasChinaAward10YearFinalManualMapper casChinaAward10YearFinalManualMapper;

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

        List<Map<String, Object>> result = Lists.newArrayList();
        detail.entrySet().stream().forEach(map -> {
            Map<String, Object> bizMap = Maps.newHashMap();
            bizMap.put("name", map.getKey().equals("中科院") ? "中国科学院" : map.getKey());
            Map<String, Double> value = map.getValue();
            List<String> list = Lists.newArrayList();
            yearlist.stream().forEach(year -> {
                list.add(value.get(year) == null ? null : String.valueOf(value.get(year)));
            });
            bizMap.put("value", list);
            result.add(bizMap);
        });

        return new FundsInfoDTO(yearlist, result, "2019年10月");
    }
    @Override
    public FundsInfoDTO newfundsQuery(String startYear, String endYear, String ysYear) {

        List<String> yearlist = this.buildYearlist(startYear, endYear);
        yearlist.add(ysYear);

        List<CasPxxJfbj> casPxxJfbjList = casPxxJfbjManualMapper.queryNewFunds(startYear, endYear, ysYear);

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

        List<Map<String, Object>> result = Lists.newArrayList();
        detail.entrySet().stream().forEach(map -> {
            Map<String, Object> bizMap = Maps.newHashMap();
            bizMap.put("name", map.getKey().equals("中科院") ? "中国科学院" : map.getKey());
            Map<String, Double> value = map.getValue();
            List<String> list = Lists.newArrayList();
            yearlist.stream().forEach(year -> {
                list.add(value.get(year) == null ? null : String.valueOf(value.get(year)));
            });
            bizMap.put("value", list);
            result.add(bizMap);
        });

        return new FundsInfoDTO(yearlist, result, "2019年10月");
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
                Map<String, Double> _finance = detail.get(casPxxCzbk.getName());
                if(_finance.containsKey(casPxxCzbk.getYear())) {
                    _finance.put(casPxxCzbk.getYear(), casPxxCzbk.getAmount());
                }
            }
        }

        List<Map<String, Object>> result = Lists.newArrayList();
        detail.entrySet().stream().forEach(map -> {
            Map<String, Object> bizMap = Maps.newHashMap();
            bizMap.put("name", map.getKey().equals("中科院") ? "中国科学院" : map.getKey());
            Map<String, Double> value = map.getValue();
            List<Object> list = Lists.newArrayList();
            yearlist.stream().forEach(year -> {
                Double fund = value.get(year);
                list.add(String.valueOf(fund));
            });
            bizMap.put("value", list);
            result.add(bizMap);
        });

        return new FinanceInfoDTO(yearlist, result, "2019年10月");
    }

    @Override
    public FinanceInfoDTO newfinanceQuery(String startYear, String endYear, String ysYear) {

        List<String> yearlist = this.buildYearlist(startYear, endYear);
        yearlist.add(ysYear);

        List<CasPxxJfbj> casPxxJfbjList = casPxxJfbjManualMapper.queryNewFinance(startYear, endYear, ysYear);

        List<String> nameList = casPxxJfbjList.stream().filter(obj -> obj.getName() != null).map
                (CasPxxJfbj::getName).distinct().collect(Collectors.toList());

        Map<String, Map<String, Double>> detail = this.initDetail(nameList, yearlist);

        for(CasPxxJfbj casPxxJfbj : casPxxJfbjList) {
            if(detail.containsKey(casPxxJfbj.getName())) {
                Map<String, Double> _funds = detail.get(casPxxJfbj.getName());
                if(_funds.containsKey(casPxxJfbj.getYear())) {
                    _funds.put(casPxxJfbj.getYear(), casPxxJfbj.getFinanceFund());
                }
            }
        }

        List<Map<String, Object>> result = Lists.newArrayList();
        detail.entrySet().stream().forEach(map -> {
            Map<String, Object> bizMap = Maps.newHashMap();
            bizMap.put("name", map.getKey().equals("中科院") ? "中国科学院" : map.getKey());
            Map<String, Double> value = map.getValue();
            List<String> list = Lists.newArrayList();
            yearlist.stream().forEach(year -> {
                list.add(value.get(year) == null ? null : String.valueOf(value.get(year)));
            });
            bizMap.put("value", list);
            result.add(bizMap);
        });

        return new FinanceInfoDTO(yearlist, result, "2019年10月");
    }

    @Override
    public PaperInfoDTO paperQuery(String startYear, String endYear) {

        List<String> newYearlist = this.buildYearlist(startYear, endYear);

        /*List<String> newYearlist = yearlist.stream().map(str -> str).collect(Collectors
                .toList());*/

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
        detail.entrySet().stream().forEach(obj -> {
            List<Double> list = Lists.newArrayList();
            newYearlist.stream().forEach(year -> {
                list.add(obj.getValue().get(year));
            });

            MapResultDTO<String, List<Double>> mapResult = new MapResultDTO(obj.getKey(), list);

            detailList.add(mapResult);
        });

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
                Integer.class, null);
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
                map.get(statHcauthorsCount.getNf()).setValue(
                        (map.get(statHcauthorsCount.getNf()).getValue() == null ? 0 :
                                map.get(statHcauthorsCount.getNf()).getValue())
                                        + statHcauthorsCount.getTotal());
            }
        }


        List<StatHcauthorsCount> resultWorld = statHcauthorsCountManualMapper
                .queryScientist2(startYear, endYear);
        List<String> countryName = resultWorld.stream().map(obj -> obj.getCountryCh()).distinct().collect
                (Collectors.toList());
        Map<String, Map<String, MapResultDTO<String, Integer>>> worldtotal = this.initDetail2(countryName, yearlist,
                Integer.class, null);
        for(StatHcauthorsCount statHcauthorsCount : resultWorld) {
            if(worldtotal.containsKey(statHcauthorsCount.getCountryCh())) {
                Map<String, MapResultDTO<String, Integer>> map = worldtotal.get(statHcauthorsCount
                        .getCountryCh());
                if(map.containsKey(statHcauthorsCount.getNf())) {
                    map.get(statHcauthorsCount.getNf()).setValue(
                            (map.get(statHcauthorsCount.getNf()).getValue() == null ? 0 :
                                    map.get(statHcauthorsCount.getNf()).getValue()) +
                                    statHcauthorsCount.getTotal());
                }
            }
        }

        List<Map<String, Object>> totalFive = statHcauthorsCountManualMapper
                .queryScientist3(startYear, endYear);

        List<String> totalFiveName = Lists.newArrayList();
        totalFive.stream().forEach(obj -> totalFiveName.add((String)obj.get("country_ch")));

        List<MapResultDTO<String, List<Integer>>> newWorldlist = Lists.newArrayList();

        Map<String, Integer> otherMap = Maps.newHashMap();
        yearlist.stream().forEach(year -> {
            otherMap.put(year, null);
        });

        worldtotal.entrySet().stream().forEach(map -> {
            String country = map.getKey();
            if(totalFiveName.contains(country)) {
                List<Integer> list = Lists.newArrayList();
                yearlist.stream().forEach(year -> {
                    list.add(map.getValue().get(year).getValue());
                });
                newWorldlist.add(new MapResultDTO<String, List<Integer>>(country, list));
            } else {
                map.getValue().entrySet().stream().forEach(map2 -> {
                    if(otherMap.containsKey(map2.getKey())) {
                        otherMap.put(map2.getKey(),
                                (otherMap.get(map2.getKey()) == null ? 0 : otherMap.get(map2.getKey()))
                                        + (map2.getValue().getValue() == null ? 0 : map2.getValue().getValue()));
                    }
                });
            }
        });

        List<Integer> otherValueList = Lists.newArrayList();
        yearlist.stream().forEach(year -> {
            otherValueList.add(otherMap.get(year));
        });
        MapResultDTO<String, List<Integer>> otherMr = new MapResultDTO("其他", otherValueList);

        newWorldlist.add(otherMr);

        ScientistInfoDTO scientistInfo = new ScientistInfoDTO();
        scientistInfo.setYear(yearlist);
        List<MapResultDTO<String, List<Integer>>> domestic = Lists.newArrayList();
        detail.entrySet().stream().forEach(obj -> {
            String key = obj.getKey();
            List<Integer> value = Lists.newArrayList();
            yearlist.stream().forEach(year -> {
                value.add(obj.getValue().get(year).getValue());
            });
            domestic.add(new MapResultDTO<String, List<Integer>>(key, value));
        });

        yearlist.stream().forEach(year -> {
            Map<String, MapResultDTO<String, Integer>> obj = detail.get(year);

        });
        scientistInfo.setDomestic(domestic);

        scientistInfo.setNewWorldlist(newWorldlist);

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
        /*for(String name : totalPlatform.keySet()) {
            if(name.equals("中科院") || name.equals("C9高校")) {
                List<Double> countList = Lists.newArrayList();
                Map<String, Double> map = totalPlatform.get(name);
                map.entrySet().stream().forEach(obj -> countList.add(obj.getValue()));
                platformList.add(new MapResultDTO<String, List<Double>>(
                        name.equals("中科院") ? "中国科学院" : name, countList));
            }
        }*/

        for(String name : totalPlatform.keySet()) {
            if(name.equals("中国科学院") || name.equals("C9高校")) {
                List<Double> countList = Lists.newArrayList();
                Map<String, Double> map = totalPlatform.get(name);
                map.entrySet().stream().forEach(obj -> countList.add(obj.getValue()));
                platformList.add(new MapResultDTO<String, List<Double>>(name, countList));
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
            zrkx.add(null);
            jsfm.add(null);
            jsjb.add(null);
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
        distinctInstitution.add("中国科学院");

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
        nationalAwardInfo.setInstitution(distinctInstitution);
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
    public CompareAwardInfoDTO awardQuery() {
        List<String> allOrgNameList = Lists.newArrayList(
                "中国科学院", "清华大学", "北京大学", "浙江大学", "上海交通大学", "复旦大学",
                "西安交通大学", "中国科学技术大学", "哈尔滨工业大学", "南京大学");
        List<String> c9List = Lists.newArrayList(
                "清华大学", "北京大学", "浙江大学", "上海交通大学", "复旦大学", "西安交通大学",
                "中国科学技术大学", "哈尔滨工业大学", "南京大学");

        List<Map<String, Object>> queryResult1 =
                casChinaAward10YearFinalManualMapper.queryCompareAward1(c9List);

        List<Map<String, Object>> queryResult2 =
                casChinaAward10YearFinalManualMapper.queryCompareAward2();

        List<Map<String, Object>> qsjcQueryResult =
                casChinaAward10YearFinalManualMapper.queryCompareAward3();

        List<Map<String, Object>> hlhlQueryResult =
                casChinaAward10YearFinalManualMapper.queryCompareAward4();

        Map<String, JxlbInfoDTO> jxlbResult = Maps.newHashMap();
        allOrgNameList.stream().forEach(institute -> {
            jxlbResult.put(institute, new JxlbInfoDTO(institute));
        });

        List<Map<String, Object>> unionJxlbResultList = Lists.newArrayList();
        if(!queryResult1.isEmpty()) {
            unionJxlbResultList.addAll(queryResult1);
        }
        if(!queryResult2.isEmpty()) {
            unionJxlbResultList.addAll(queryResult2);
        }

        unionJxlbResultList.stream().forEach(map -> {
            String institute = String.valueOf(map.get("institute"));
            String jxlb = String.valueOf(map.get("jxlb"));
            int num = ((Number) map.get("num")).intValue();
            if(jxlbResult.containsKey(institute)) {
                JxlbInfoDTO jxlbInfo = jxlbResult.get(institute);
                if("国家技术发明奖".equals(jxlb)) {
                    jxlbInfo.setJsfm(num);
                } else if("国家自然科学奖".equals(jxlb)) {
                    jxlbInfo.setZrkx(num);
                } else if("国家科学技术进步奖".equals(jxlb)) {
                    jxlbInfo.setJsjb(num);
                }
            }
        });

        List<Integer> zrkxList = Lists.newArrayList();
        List<Integer> jsfmList = Lists.newArrayList();
        List<Integer> jsjbList = Lists.newArrayList();

        allOrgNameList.stream().forEach(orgName -> {
            int zrkx = 0;
            int jsfm = 0;
            int jsjb = 0;
            if(jxlbResult.containsKey(orgName)) {
                JxlbInfoDTO jxlbInfoDTO = jxlbResult.get(orgName);
                zrkx = jxlbInfoDTO.getZrkx();
                jsfm = jxlbInfoDTO.getJsfm();
                jsjb = jxlbInfoDTO.getJsjb();
            }
            zrkxList.add(zrkx);
            jsfmList.add(jsfm);
            jsjbList.add(jsjb);
        });

        List<Map<String, Object>> qsjcList = Lists.newArrayList();
        qsjcQueryResult.stream().forEach(map -> {
            Map<String, Object> resultObj = Maps.newHashMap();
            resultObj.put("name", map.get("category"));
            resultObj.put("value", map.get("num"));
            qsjcList.add(resultObj);
        });

        List<Map<String, Object>> hlhlList = Lists.newArrayList();
        hlhlQueryResult.stream().forEach(map -> {
            Map<String, Object> resultObj = Maps.newHashMap();
            resultObj.put("name", map.get("category"));
            resultObj.put("value", map.get("num"));
            hlhlList.add(resultObj);
        });

        return new CompareAwardInfoDTO(
                allOrgNameList, zrkxList, jsfmList, jsjbList, qsjcList, hlhlList);
    }

    @Override
    public ProjectInfoDTO projectQuery(ProjectQueryDO projectQuery)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ProjectInfoDTO projectInfo = new ProjectInfoDTO();
        this.initProjectInfoDTO(projectInfo);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, -1);
        String endYearStr = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.YEAR, -5);
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
                    new MapResultDTO(Lists.newArrayList(), Lists.newArrayList()),
                    new MapResultDTO(Lists.newArrayList(), Lists.newArrayList()),
                    new MapResultDTO(Lists.newArrayList(), Lists.newArrayList())));
        }

        List<CompareProjectObj> nsfcProjectList = statNsfcProjectManualMapper.queryProject(projectQuery);
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
        yearListStr.stream().forEach(year -> {
            orderList.add(orderMap.get(year));
        });
        projectInfo.setOrder(orderList);

        projectInfo.setYear(yearListStr);

        return projectInfo;
    }

    private void initProjectInfoDTO(ProjectInfoDTO projectInfo) {
        projectInfo.setNsfcProject(Lists.newArrayList());
        projectInfo.setNsfcFunds(Lists.newArrayList());

        projectInfo.setXdProject(Lists.newArrayList());
        projectInfo.setXdFunds(Lists.newArrayList());

        projectInfo.setStdProject(Lists.newArrayList());
        projectInfo.setStdFunds(Lists.newArrayList());
    }

    private void doProjectQuery(List<String> yearListStr, Map<String, ProjectInfoDTO.OrderDTO>
            orderMap, List<CompareProjectObj> bizDateList, ProjectInfoDTO projectInfo, Method
            method) throws InvocationTargetException, IllegalAccessException {

        Triple<Map, Map, Map> triple = this.initCalculateParam(yearListStr, bizDateList);
        Map<String, Long> xmList = triple.getLeft();
        Map<String, Double> jfList = triple.getMiddle();
        Map<String, List<CompareProjectObj>> recordList = triple.getRight();

        projectInfo.setNsfcNew(xmList.get(yearListStr.get(yearListStr.size()-1)));

        String lastYear = yearListStr.get(yearListStr.size() - 1);

        yearListStr.stream().forEach(year -> {
            if(method.getName().equals("getNsfc")) {
                projectInfo.getNsfcProject().add(xmList.get(year).toString());
                projectInfo.setNsfcCumulation(projectInfo.getNsfcCumulation() + xmList.get(year));
                projectInfo.getNsfcFunds().add(nf.format(jfList.get(year)));
                if(year.equals(lastYear)) {
                    projectInfo.setNsfcNew(xmList.get(year));
                }
            } else if(method.getName().equals("getStd")) {
                projectInfo.getStdProject().add(xmList.get(year).toString());
                projectInfo.setStdCumulation(projectInfo.getStdCumulation() + xmList.get(year));
                projectInfo.getStdFunds().add(nf.format(jfList.get(year)));
                if(year.equals(lastYear)) {
                    projectInfo.setStdNew(xmList.get(year));
                }
            } else if(method.getName().equals("getXd")) {
                projectInfo.getXdProject().add(xmList.get(year).toString());
                projectInfo.setXdCumulation(projectInfo.getXdCumulation() + xmList.get(year));
                projectInfo.getXdFunds().add(nf.format(jfList.get(year)));
                if(year.equals(lastYear)) {
                    projectInfo.setXdNew(xmList.get(year));
                }
            }
        });


        for(String year : recordList.keySet()) {
            ProjectInfoDTO.OrderDTO order = orderMap.get(year);
            MapResultDTO<List<String>, List<Long>> map = (MapResultDTO<List<String>, List<Long>>) method.invoke(order);
            if(null != recordList.get(year)) {
                List<CompareProjectObj> list = recordList.get(year);
                List<CompareProjectObj> sortedList = list.stream().sorted(Comparator
                        .comparingLong(ompareProjectObj -> ompareProjectObj.getXm() * -1)).limit(10)
                        .collect(Collectors.toList());
                sortedList.stream().forEach(compareProjectObj -> {
                    map.getName().add(compareProjectObj.getJgmc());
                    map.getValue().add(compareProjectObj.getXm());
                });
            }
        }
    }

    private Triple<Map, Map, Map> initCalculateParam(List<String> yearListStr, List<CompareProjectObj> bizDateList) {

        Map<String, Long> xmList = Maps.newHashMap();   // 每年的项目数
        Map<String, Double> jfList = Maps.newHashMap();   // 每年的经费数
        Map<String, List<CompareProjectObj>> recordList = Maps.newHashMap();   // 每年的记录数

        yearListStr.forEach(
                obj -> {
                    xmList.put(obj, 0L);
                    jfList.put(obj, 0D);
                    recordList.put(obj, Lists.newArrayList());
                }
        );

        bizDateList.forEach(
                obj -> {
                    if(yearListStr.contains(obj.getNf())) {
                        String year = obj.getNf();
                        xmList.put(year, xmList.get(year) + obj.getXm());
                        /*jfList.put(year, new BigDecimal(jfList.get(year)).add(new BigDecimal(obj
                                .getJf())).doubleValue());*/
                        jfList.put(year, jfList.get(year) + obj.getJf());
                        recordList.get(year).add(obj);
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

    private Map<String, Map<String, Double>> initDetail(List<String> nameList, List<String> yearlist) {
        Map<String, Map<String, Double>> detail = Maps.newHashMap();
        for(String name : nameList) {
            Map<String, Double> _funds = Maps.newHashMap();
            for(String year : yearlist) {
                _funds.put(year, null);
            }
            //detail.put(name.equals("中科院") ? "中国科学院" : name, _funds);
            detail.put(name, _funds);
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
