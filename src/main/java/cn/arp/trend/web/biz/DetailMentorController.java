package cn.arp.trend.web.biz;

import javax.annotation.Resource;

import cn.arp.trend.data.model.request.*;
import cn.arp.trend.data.model.response.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.DO.AllSupervisorQueryDO;
import cn.arp.trend.data.model.DO.DoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DO.MasterSupervisorQueryDO;
import cn.arp.trend.data.model.DO.MentorDetailQueryDO;
import cn.arp.trend.data.model.DO.TrendAllQueryDO;
import cn.arp.trend.data.model.DO.TrendDoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DO.TrendMasterSupervisorQueryDO;
import cn.arp.trend.data.model.DTO.AllSupervisorInfoDTO;
import cn.arp.trend.data.model.DTO.DoctoralSupervisorInfoDTO;
import cn.arp.trend.data.model.DTO.MasterSupervisorInfoDTO;
import cn.arp.trend.data.model.DTO.MentorDetailInfoDTO;
import cn.arp.trend.data.model.DTO.TrendAllInfoDTO;
import cn.arp.trend.data.model.DTO.TrendDoctoralSupervisorInfoDTO;
import cn.arp.trend.data.model.DTO.TrendMasterSupervisorInfoDTO;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailMentorService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午12:56
 **/
@Api(value="detailMentor",tags={"对应宏观部分detailMentor.js"})
@RestController
@RequestMapping(value = "/detail/mentor")
@RequirePermission(dataset=true)
public class DetailMentorController extends BaseController {

    @Resource
    private DetailMentorService detailMentorService;

    /**
     * 博导
     *
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "博导", notes= "博导")
    @ServiceExecuter(description = "博导")
    @RequestMapping(value = "/distribution/d", method = RequestMethod.POST)
    @Audit(desc="博导的领域分布、年龄分布", value="Education.DoctorMentorDistribution")
    public DoctoralSupervisorResponse doctoralupervisorQuery(
            @RequestBody @Validated DoctoralSupervisorRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        DoctoralSupervisorQueryDO query = new DoctoralSupervisorQueryDO(
                request.getEndYear(), request.getAffiliationId(), request.getFieldName());
        DoctoralSupervisorInfoDTO doctoralSupervisorInfo = detailMentorService
                .doctoralSupervisorQuery(query);
        return new DoctoralSupervisorResponse(doctoralSupervisorInfo.getDistributionAge(),
                doctoralSupervisorInfo.getDistributionField());
    }

    /**
     * 硕导
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "硕导", notes= "硕导")
    @ServiceExecuter(description = "硕导")
    @RequestMapping(value = "/distribution/m", method = RequestMethod.POST)
    @Audit(desc="硕导的领域分布、年龄分布", value="Education.MasterMentorDistribution")
    public MasterSupervisorResponse masterSupervisorQuery(
            @RequestBody @Validated MasterSupervisorRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        MasterSupervisorQueryDO query = new MasterSupervisorQueryDO(
                request.getEndYear(), request.getAffiliationId(), request.getFieldName());
        MasterSupervisorInfoDTO masterSupervisorInfo = detailMentorService
                .masterSupervisorQuery(query);
        return new MasterSupervisorResponse(masterSupervisorInfo.getDistributionAge(),
                masterSupervisorInfo.getDistributionField());
    }

    /**
     * 导师
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "导师", notes= "导师")
    @ServiceExecuter(description = "导师")
    @RequestMapping(value = "/distribution/all", method = RequestMethod.POST)
    @Audit(desc="导师的领域分布、年龄分布", value="Education.MentorDistribution")
    public AllSupervisorResponse allSupervisorQuery(
            @RequestBody @Validated AllSupervisorRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AllSupervisorQueryDO query = new AllSupervisorQueryDO(
                request.getEndYear(), request.getAffiliationId(), request.getFieldName());
        AllSupervisorInfoDTO allSupervisorInfo = detailMentorService
                .allSupervisorQuery(query);
        return new AllSupervisorResponse(allSupervisorInfo.getDistributionAge(),
                allSupervisorInfo.getDistributionField());
    }

    /**
     * 导师
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "导师", notes= "导师")
    @ServiceExecuter(description = "导师")
    @RequestMapping(value = "/trend/d", method = RequestMethod.POST)
    @Audit(desc="历年博导、博士生数量", value="Education.DoctorMentorTrend")
    public TrendDoctoralSupervisorResponse trendDoctoralSupervisorQuery(
            @RequestBody @Validated TrendDoctoralSupervisorRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        TrendDoctoralSupervisorQueryDO query = new TrendDoctoralSupervisorQueryDO(
                request.getStartYear(), request.getEndYear(),
                request.getAffiliationId(), request.getFieldName());
        TrendDoctoralSupervisorInfoDTO trendDoctoralSupervisorInfo = detailMentorService
                .trendDoctoralSupervisorQuery(query);
        return new TrendDoctoralSupervisorResponse(trendDoctoralSupervisorInfo.getResult());
    }

    /**
     * 硕导
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "硕导", notes= "硕导")
    @ServiceExecuter(description = "硕导")
    @RequestMapping(value = "/trend/m", method = RequestMethod.POST)
    @Audit(desc="历年硕导、硕士生数量", value="Education.MasterMentorTrend")
    public TrendMasterSupervisorResponse trendMasterSupervisorQuery(
            @RequestBody @Validated TrendMasterSupervisorRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        TrendMasterSupervisorQueryDO query = new TrendMasterSupervisorQueryDO(
                request.getStartYear(), request.getEndYear(),
                request.getAffiliationId(), request.getFieldName());
        TrendMasterSupervisorInfoDTO trendDoctoralSupervisorInfo = detailMentorService
                .trendMasterSupervisorQuery(query);
        return new TrendMasterSupervisorResponse(trendDoctoralSupervisorInfo.getResult());
    }

    /**
     * 导师
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "导师", notes= "导师")
    @ServiceExecuter(description = "导师")
    @RequestMapping(value = "/trend/all", method = RequestMethod.POST)
    @Audit(desc="历年导师、学生数量", value="Education.MentorTrend")
    public TrendAllResponse trendMasterSupervisorQuery(
            @RequestBody @Validated TrendAllRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        TrendAllQueryDO query = new TrendAllQueryDO(
                request.getStartYear(), request.getEndYear(),
                request.getAffiliationId(), request.getFieldName());
        TrendAllInfoDTO trendAllInfo = detailMentorService.trendAllQuery(query);
        return new TrendAllResponse(trendAllInfo.getResult());
    }

    /**
     * 中间的详情列表
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "中间的详情列表", notes= "中间的详情列表")
    @ServiceExecuter(description = "导师")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @Audit(desc="导师、学生详情", value="Education.Detail")
    public MentorDetailResponse trendMasterSupervisorQuery(
            @RequestBody @Validated MentorDetailRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        MentorDetailQueryDO query = new MentorDetailQueryDO(
                request.getStartYear(), request.getEndYear(),
                request.getAffiliationId(), request.getFieldName());
        MentorDetailInfoDTO mentorDetailInfo = detailMentorService.detailQuery(query);
        return new MentorDetailResponse(mentorDetailInfo.getData());
    }

    /**
     * 中间的详情列表
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "获取雷达图所需的各个维度的数据", notes= "获取雷达图所需的各个维度的数据")
    @ServiceExecuter(description = "获取雷达图所需的各个维度的数据")
    @RequestMapping(value = "/radar", method = RequestMethod.POST)
    @Audit(desc="获取雷达图所需的各个维度的数据", value="Education.Radar")
    public List<MentorRadarResponse> radarQuery(
            @RequestBody @Validated MentorRadarDetailRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        List<MentorRadarResponse> mentorRadarResponseList = detailMentorService.radarDetail(request.getAffiliationId());
        return mentorRadarResponseList;
    }
}
