package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DTO.AnalyzeAllResultDTO;
import cn.arp.trend.data.model.DTO.AnalyzeInfoDTO;
import cn.arp.trend.data.model.DTO.AnalyzeIntermediateResultDTO;
import cn.arp.trend.entity.biz.*;
import cn.arp.trend.repository.biz.manual.*;
import cn.arp.trend.service.biz.AnalyzeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:下午3:51
 **/
@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    @Resource
    private ExecutorService fixedThreadPool;

    @Resource
    private ListeningExecutorService listeningExecutorService;

    @Resource
    private RefOrgTypeManualMapper refOrgTypeManualMapper;

    @Resource
    private StatCasTalentsProgramManualMapper statCasTalentsProgramManualMapper;

    @Resource
    private StatPatentManualMapper statPatentManualMapper;

    @Resource
    private StatCasPaperManualMapper statCasPaperManualMapper;

    @Resource
    private StatNsfcProjectManualMapper statNsfcProjectManualMapper;

    @Resource
    private StatMostProjectManualMapper satMostProjectManualMapper;

    @Resource
    private StatXdzxManualMapper statXdzxManualMapper;

    @Resource
    private CasNo1IrcPm14ManualMapper casNo1IrcPm14ManualMapper;

    @Resource
    private StatArpFinIncomeManualMapper statArpFinIncomeManualMapper;

    @Resource
    private CasAcademicianChinaManualMapper casAcademicianChinaManualMapper;

    @Resource
    private CasAcademicianCaeChinaManualMapper casAcademicianCaeChinaManualMapper;

    @Resource
    private StatChinaAward10yearFinalCountManualMapper statChinaAward10yearFinalCountManualMapper;

    @Resource
    private StatTeacherStudentManualMapper statTeacherStudentManualMapper;

    @Override
    public AnalyzeInfoDTO query() {
        String startYear = "2016";
        String endYear = "2019";

        /**
         * TODO
         * 这里因为方法太多，做一步优化，利用guava future + AQS
         * 做多线程的并行处理，因为数据库设计成这样，大表方式处理暂时不可能，多线程的坏处是限制了不能做大并发量查询，有机会最好重构掉吧
         */
        List<ListenableFuture> fetureList = Lists.newArrayList();

        AnalyzeIntermediateResultDTO intermediateResult = new AnalyzeIntermediateResultDTO();

        // 1、获取单位信息
        ListenableFuture<List<RefOrgType>> afTask = listeningExecutorService.submit(
                () -> { return refOrgTypeManualMapper.queryJgmcAndResearchAndSsfy();
                });
        // 2、统计导师人数
        ListenableFuture<List<StatTeacherStudent>> eduTask = listeningExecutorService.submit(
                () -> { return statTeacherStudentManualMapper.queryByNf(startYear, endYear);
                });
        // 3、统计各单位百人数量
        ListenableFuture<List<StatCasTalentsProgram>> talent100Task = listeningExecutorService.submit(
                () -> { return statCasTalentsProgramManualMapper.queryBySslyAndNfAndLx(startYear, endYear);
                });
        // 4、统计各单位专利数量
        ListenableFuture<List<StatPatent>> patentTask = listeningExecutorService.submit(
                () -> { return statPatentManualMapper.queryByNf(startYear, endYear);
                });
        // 5、统计各单位论文数量
        ListenableFuture<List<StatCasPaper>> paperTask = listeningExecutorService.submit(
                () -> { return statCasPaperManualMapper.queryByNf(startYear, endYear);
                });
        // 6、List<StatNsfcProject> nsfcList = statNsfcProjectManualMapper.queryByNf(startYear, endYear);
        ListenableFuture<List<StatNsfcProject>> nsfcTask = listeningExecutorService.submit(
                () -> { return statNsfcProjectManualMapper.queryByNf(startYear, endYear);
                });
        // 7、List<StatMostProject> kjbList = satMostProjectManualMapper.queryByNf(startYear, endYear);
        ListenableFuture<List<StatMostProject>> kjbTask = listeningExecutorService.submit(
                () -> { return satMostProjectManualMapper.queryByNf(startYear, endYear);
                });
        // 8、List<StatXdzx> xdList = statXdzxManualMapper.queryByNf(startYear, endYear);
        ListenableFuture<List<StatXdzx>> xdTask = listeningExecutorService.submit(
                () -> { return statXdzxManualMapper.queryByNf(startYear, endYear);
                });
        // 9、各单位总收入 List<Funds> fundsList = casNo1IrcPm14ManualMapper.queryFunds(startYear,
        // endYear);
        ListenableFuture<List<Funds>> financeTask = listeningExecutorService.submit(
                () -> { return casNo1IrcPm14ManualMapper.queryFunds(startYear, endYear);
                });
        // 10、各单位院士 List<CasAcademicianChina> casList = casAcademicianChinaManualMapper.queryByDxnf(endYear);
        ListenableFuture<List<CasAcademicianChina>> casTask = listeningExecutorService.submit(
                () -> { return casAcademicianChinaManualMapper.queryByDxnf(endYear);
                });
        // 11、List<CasAcademicianCaeChina> caeList = casAcademicianCaeChinaManualMapper.queryByDxnf(endYear);
        ListenableFuture<List<CasAcademicianCaeChina>> caeTask = listeningExecutorService.submit(
                () -> { return casAcademicianCaeChinaManualMapper.queryByDxnf(endYear);
                });
        // 12、List<StatChinaAward10yearFinalCount> awardList = statChinaAward10yearFinalCountManualMapper.queryByHjnf(startYear, endYear);
        ListenableFuture<List<StatChinaAward10yearFinalCount>> awardTask = listeningExecutorService.submit(
                () -> { return statChinaAward10yearFinalCountManualMapper.queryByHjnf(startYear, endYear);
                });

        fetureList.add(afTask);
        fetureList.add(eduTask);
        fetureList.add(talent100Task);
        fetureList.add(patentTask);
        fetureList.add(paperTask);
        fetureList.add(nsfcTask);
        fetureList.add(kjbTask);
        fetureList.add(xdTask);
        fetureList.add(financeTask);
        fetureList.add(casTask);
        fetureList.add(caeTask);
        fetureList.add(awardTask);

        final CountDownLatch latch = new CountDownLatch(fetureList.size());

        this.doAddCallback(afTask, intermediateResult::setAfList, fetureList, latch);
        this.doAddCallback(eduTask, intermediateResult::setEduList, fetureList, latch);
        this.doAddCallback(talent100Task, intermediateResult::setTalent100List, fetureList, latch);
        this.doAddCallback(patentTask, intermediateResult::setPatentList, fetureList, latch);
        this.doAddCallback(paperTask, intermediateResult::setPaperList, fetureList, latch);
        this.doAddCallback(nsfcTask, intermediateResult::setNsfcList, fetureList, latch);
        this.doAddCallback(kjbTask, intermediateResult::setKjbList, fetureList, latch);
        this.doAddCallback(xdTask, intermediateResult::setXdList, fetureList, latch);
        this.doAddCallback(financeTask, intermediateResult::setFundsList, fetureList, latch);
        this.doAddCallback(casTask, intermediateResult::setCasList, fetureList, latch);
        this.doAddCallback(caeTask, intermediateResult::setCaeList, fetureList, latch);
        this.doAddCallback(awardTask, intermediateResult::setAwardList, fetureList, latch);

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("执行analyzeServiceImpl.query()异常", e);
        }

        intermediateResult.setAfByJgmcMap(
                intermediateResult.getAfList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setEduByJgmcMap(
                intermediateResult.getEduList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setTalent100ByJgmcMap(
                intermediateResult.getTalent100List().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setPatentByJgmcMap(
                intermediateResult.getPatentList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setPaperByJgmcMap(
                intermediateResult.getPaperList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setNsfcByJgmcMap(
                intermediateResult.getNsfcList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setKjbByJgmcMap(
                intermediateResult.getKjbList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setXdByJgmcMap(
                intermediateResult.getXdList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setFundsByJgmcMap(
                intermediateResult.getFundsList().stream().collect(Collectors.toMap(obj
                        -> obj.getJgmc(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setCasByGzdwGf1Map(
                intermediateResult.getCasList().stream().collect(Collectors.toMap(obj
                        -> obj.getGzdwGf1(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setCaeByGzdwGf1Map(
                intermediateResult.getCaeList().stream().collect(Collectors.toMap(obj
                        -> obj.getGzdwGf1(), obj -> obj, (obj1, obj2) -> obj2)));
        intermediateResult.setAwardByFirstWcdwMap(
                intermediateResult.getAwardList().stream().collect(Collectors.toMap(obj
                        -> obj.getFirstWcdw(), obj -> obj, (obj1, obj2) -> obj2)));

        List<AnalyzeAllResultDTO> analyzeAllResultList = Lists.newArrayList();

        int index = 0;

        for(String key : intermediateResult.getAfByJgmcMap().keySet()) {
            AnalyzeAllResultDTO analyzeAllResult = new AnalyzeAllResultDTO();
            analyzeAllResult.setIndex(index);

            String jgmc = intermediateResult.getAfByJgmcMap().get(key).getJgmc();
            String field = intermediateResult.getAfByJgmcMap().get(key).getResearchField();
            analyzeAllResult.setFaf(jgmc);
            analyzeAllResult.setField(field);

            if(intermediateResult.getEduByJgmcMap().containsKey(jgmc)) {
                StatTeacherStudent edu = intermediateResult.getEduByJgmcMap().get(jgmc);
                if(endYear.equals(edu.getNf())) {
                    analyzeAllResult.setMentor(edu == null ? 0 : (edu.getBdrs() + edu
                            .getSdrs()));
                    analyzeAllResult.setConcurrent(edu == null ? 0 : edu.getBdrs());
                }
            }

            if(intermediateResult.getTalent100ByJgmcMap().containsKey(jgmc)) {
                StatCasTalentsProgram talent100 = intermediateResult.getTalent100ByJgmcMap().get(jgmc);
                if(endYear.equals(talent100.getNf())) {
                    analyzeAllResult.setTalent100(talent100 == null ? 0 : talent100.getLj());
                }
            }

            if(intermediateResult.getPatentByJgmcMap().containsKey(jgmc)) {
                StatPatent patent = intermediateResult.getPatentByJgmcMap().get(jgmc);
                if(endYear.equals(patent.getNf())) {
                    analyzeAllResult.setPatent(patent == null ? 0 : patent.getLj());
                }
            }

            if(intermediateResult.getPaperByJgmcMap().containsKey(jgmc)) {
                StatCasPaper paper = intermediateResult.getPaperByJgmcMap().get(jgmc);
                analyzeAllResult.setPaper(paper == null ? 0 : paper.getLws());
            }

            if(intermediateResult.getNsfcByJgmcMap().containsKey(jgmc)) {
                StatNsfcProject nsfc = intermediateResult.getNsfcByJgmcMap().get(jgmc);
                analyzeAllResult.setProjectNsfc(nsfc == null ? 0 : nsfc.getXzxms());
            }

            if(intermediateResult.getKjbByJgmcMap().containsKey(jgmc)) {
                StatMostProject kjb = intermediateResult.getKjbByJgmcMap().get(jgmc);
                analyzeAllResult.setProjectKjb(kjb == null ? 0 : kjb.getXzxms());
            }

            if(intermediateResult.getXdByJgmcMap().containsKey(jgmc)) {
                StatXdzx xd = intermediateResult.getXdByJgmcMap().get(jgmc);
                analyzeAllResult.setProjectXd(xd == null ? 0 : xd.getXzxms());
            }

            if(intermediateResult.getFundsByJgmcMap().containsKey(jgmc)) {
                Funds funds = intermediateResult.getFundsByJgmcMap().get(jgmc);
                analyzeAllResult.setFinance(funds == null ? 0 : funds.getZsr());
            }

            if(intermediateResult.getCasByGzdwGf1Map().containsKey(jgmc)) {
                CasAcademicianChina cas = intermediateResult.getCasByGzdwGf1Map().get(jgmc);
                analyzeAllResult.setCas(cas == null ? 0 : 1);
            }

            if(intermediateResult.getCaeByGzdwGf1Map().containsKey(jgmc)) {
                CasAcademicianCaeChina cae = intermediateResult.getCaeByGzdwGf1Map().get(jgmc);
                analyzeAllResult.setCae(cae == null ? 0 : 1);
            }

            if(intermediateResult.getAwardByFirstWcdwMap().containsKey(jgmc)) {
                StatChinaAward10yearFinalCount award = intermediateResult.getAwardByFirstWcdwMap().get(jgmc);
                analyzeAllResult.setAward(award == null ? 0 : award.getNum());
            }

            analyzeAllResultList.add(analyzeAllResult);
            index ++;
        }

        /*List<String> fField = analyzeAllResultList.stream().map(obj -> obj.getField()).distinct()
                .collect(Collectors.toList());*/
        List<String> distinctResearchField = intermediateResult.getAfList().stream().map(i -> i
                .getResearchField()).distinct().collect(Collectors.toList());


        List<List<Object>> newAnalyzeAllResultList = Lists.newArrayList();
        analyzeAllResultList.stream().forEach(obj -> {
            List<Object> list = Lists.newArrayList();
            list.add(obj.getTalent100());
            list.add(obj.getPatent());
            list.add(obj.getPaper());
            list.add(obj.getProjectNsfc() + obj.getProjectKjb() + obj.getProjectXd());
            list.add(obj.getFinance());
            list.add(obj.getAcadenician());
            list.add(obj.getAward());
            list.add(distinctResearchField.indexOf(obj.getField()));
            list.add(obj.getFaf());
            list.add(obj.getMentor());
            list.add(obj.getConcurrent());
            list.add(obj.getIndex());
            newAnalyzeAllResultList.add(list);
        });

        Map<String, String> fieldMap = Maps.newHashMap();
        int idx = 0;
        for(String str : distinctResearchField) {
            fieldMap.put(String.valueOf(idx), str);
            idx ++;
        }

        AnalyzeInfoDTO analyzeInfo = new AnalyzeInfoDTO();
        analyzeInfo.setAll(newAnalyzeAllResultList);
        analyzeInfo.setFieldMap(fieldMap);

        return analyzeInfo;
    }


    private <T> void doAddCallback(ListenableFuture<T> feture, Consumer<T> consumer,
                                   List<ListenableFuture> fetureList, CountDownLatch latch) {
        Futures.addCallback(feture, new FutureCallback<T>() {
            @Override
            public void onSuccess(@Nullable T t) {
                doSuccess((p) -> {
                    consumer.accept(p);
                }, t, latch);
            }
            @Override
            public void onFailure(Throwable throwable) {
                doFailure(fetureList, throwable);
            }
        }, fixedThreadPool);
    }

    private void doFailure(List<ListenableFuture> fetureList, Throwable throwable) {
        // 尝试取消本次任务其他线程执行，试图节省算力
        for(ListenableFuture feture : fetureList) {
            if(!feture.isCancelled() && !feture.isDone()) {
                // TODO 这么写不能保证一定取消掉其他线程执行，严谨一点需要用信号量告知，这点并发问题不大
                feture.cancel(true);
            }
        }
        throw new RuntimeException("执行analyzeServiceImpl.query()异常", throwable);
    }

    private <T> void doSuccess(Consumer<T> consumer, T t, CountDownLatch latch) {
        consumer.accept(t);
        latch.countDown();
    }

}
