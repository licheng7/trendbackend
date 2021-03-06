package cn.arp.trend.web.biz;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.DO.AgeDistributionQueryDO;
import cn.arp.trend.data.model.DO.ChildLevelDistributionQueryDO;
import cn.arp.trend.data.model.DO.DrRankQueryDO;
import cn.arp.trend.data.model.DO.IncreaseTrendQueryDO;
import cn.arp.trend.data.model.DO.PersonTypeDistributionQueryDO;
import cn.arp.trend.data.model.DO.PositionDistributionQueryDO;
import cn.arp.trend.data.model.DO.PostAnalyzeQueryDO;
import cn.arp.trend.data.model.DO.PostDistributionQueryDO;
import cn.arp.trend.data.model.DTO.AgeDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.ChildLevelDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.DrRankInfoDTO;
import cn.arp.trend.data.model.DTO.IncreaseTrendInfoDTO;
import cn.arp.trend.data.model.DTO.PersonTypeDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.PositionDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.PostAnalyzeInfoDTO;
import cn.arp.trend.data.model.DTO.PostDistributionInfoDTO;
import cn.arp.trend.data.model.converter.IncreaseTrendDetailConverter;
import cn.arp.trend.data.model.converter.MapResultConverter;
import cn.arp.trend.data.model.request.AgeDistributionRequest;
import cn.arp.trend.data.model.request.ChildLevelDistributionRequest;
import cn.arp.trend.data.model.request.DrRankRequest;
import cn.arp.trend.data.model.request.IncreaseTrendRequest;
import cn.arp.trend.data.model.request.PersonTypeDistributionRequest;
import cn.arp.trend.data.model.request.PositionDistributionRequest;
import cn.arp.trend.data.model.request.PostAnalyzeRequest;
import cn.arp.trend.data.model.request.PostDistributionRequest;
import cn.arp.trend.data.model.response.AgeDistributionResponse;
import cn.arp.trend.data.model.response.ChildLevelDistributionResponse;
import cn.arp.trend.data.model.response.DrRankResponse;
import cn.arp.trend.data.model.response.IncreaseTrendResponse;
import cn.arp.trend.data.model.response.PersonTypeDistributionResponse;
import cn.arp.trend.data.model.response.PositionDistributionResponse;
import cn.arp.trend.data.model.response.PostAnalyzeResponse;
import cn.arp.trend.data.model.response.PostDistributionResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailStaffService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:??????12:56
 **/
@Api(value="detailStaff",tags={"??????????????????detailStaff.js"})
@RestController
@RequestMapping(value = "/detail/staff")
@RequirePermission(dataset=true)
public class DetailStaffController extends BaseController {

    @Resource
    private DetailStaffService detailStaffService;

    /**
     * ?????????????????????
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "?????????????????????", notes= "?????????????????????")
    @ServiceExecuter(description = "?????????????????????")
    @RequestMapping(value = "/ageDistribution", method = RequestMethod.POST)
    @Audit(desc="????????????????????????", value="Staff.AgeDistribution")
    public AgeDistributionResponse ageDistributionQuery(
            @RequestBody @Validated AgeDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AgeDistributionQueryDO query = new AgeDistributionQueryDO(
                request.getEndYear(), request.getAffiliationId());
        AgeDistributionInfoDTO ageDistributionInfo = detailStaffService.ageDistributionQuery(query);
        AgeDistributionResponse response = new AgeDistributionResponse(
                ageDistributionInfo.getUpdateTime(),
                MapResultConverter.INSTANCE.domain2dto(ageDistributionInfo.getDetail()),
                ageDistributionInfo.getResultList()
        );
        return response;
    }

    /**
     * ?????????????????????
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "?????????????????????", notes= "?????????????????????")
    @ServiceExecuter(description = "?????????????????????")
    @RequestMapping(value = "/childLevelDistribution", method = RequestMethod.POST)
    @Audit(desc="????????????????????????", value="Staff.EducationLevel")
    public ChildLevelDistributionResponse childLevelDistributionQuery(
            @RequestBody @Validated ChildLevelDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ChildLevelDistributionQueryDO query = new ChildLevelDistributionQueryDO(
                request.getEndYear(), request.getAffiliationId());
        ChildLevelDistributionInfoDTO childLevelDistributionInfo = detailStaffService
                .childLevelDistributionQuery(query);
        ChildLevelDistributionResponse response = new ChildLevelDistributionResponse(
                childLevelDistributionInfo.getUpdateTime(),
                childLevelDistributionInfo.getTotal(),
                childLevelDistributionInfo.getTotalNum()
        );
        return response;
    }

    @ApiOperation(value= "???????????????????????????????????????", notes= "???????????????????????????????????????")
    @ServiceExecuter(description = "???????????????????????????????????????")
    @RequestMapping(value = "/increaseTrend", method = RequestMethod.POST)
    @Audit(desc="??????????????????????????????", value="Staff.Trend")
    public IncreaseTrendResponse increaseTrendQuery(
            @RequestBody @Validated IncreaseTrendRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        IncreaseTrendQueryDO query = new IncreaseTrendQueryDO(
                request.getStartYear(), request.getEndYear(), request.getAffiliationId());
        IncreaseTrendInfoDTO increaseTrendInfo = detailStaffService.increaseTrendQuery(query);
        IncreaseTrendResponse response = new IncreaseTrendResponse();
        response.setYear(increaseTrendInfo.getYear());
        response.setUpdateTime(increaseTrendInfo.getUpdateTime());
        response.setDetail(IncreaseTrendDetailConverter.INSTANCE.domain2dto(increaseTrendInfo.getDetail()));
        return response;
    }

    @ApiOperation(value= "?????????????????????????????????", notes= "?????????????????????????????????")
    @ServiceExecuter(description = "?????????????????????????????????")
    @RequestMapping(value = "/personTypeDistribution", method = RequestMethod.POST)
    @Audit(desc="????????????????????????", value="Staff.CategoryDistribution")
    public PersonTypeDistributionResponse personTypeDistributionQuery(
            @RequestBody @Validated PersonTypeDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        PersonTypeDistributionQueryDO query = new PersonTypeDistributionQueryDO(
                request.getStartYear(), request.getEndYear(), request.getAffiliationId());
        PersonTypeDistributionInfoDTO personTypeDistributionInfo = detailStaffService
                .personTypeDistributionQuery(query);
        return new PersonTypeDistributionResponse(personTypeDistributionInfo.getDetail(),
                personTypeDistributionInfo.getUpdateTime(), personTypeDistributionInfo.getResultArray());
    }

    @ApiOperation(value= "?????????????????????????????????", notes= "?????????????????????????????????")
    @ServiceExecuter(description = "?????????????????????????????????")
    @RequestMapping(value = "/postDistribution", method = RequestMethod.POST)
    @Audit(desc="????????????????????????", value="Staff.PositionDistribution")
    public PostDistributionResponse postDistributionQuery(
            @RequestBody @Validated PostDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        PostDistributionQueryDO query = new PostDistributionQueryDO(
                request.getEndYear(), request.getAffiliationId());
        PostDistributionInfoDTO postDistributionInfo = detailStaffService
                .postDistributionQuery(query);
        return new PostDistributionResponse(postDistributionInfo.getDetail(),
                postDistributionInfo.getUpdateTime());
    }

    @ApiOperation(value= "???????????????????????????????????????", notes= "???????????????????????????????????????")
    @ServiceExecuter(description = "???????????????????????????????????????")
    @RequestMapping(value = "/positionDistribution", method = RequestMethod.POST)
    @Audit(desc="??????????????????????????????", value="Staff.TitleDistribution")
    public PositionDistributionResponse positionDistributionQuery(
            @RequestBody @Validated PositionDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        PositionDistributionQueryDO query = new PositionDistributionQueryDO(
                request.getEndYear(), request.getAffiliationId());
        PositionDistributionInfoDTO positionDistributionInfo = detailStaffService
                .positionDistributionQuery(query);
        return new PositionDistributionResponse(positionDistributionInfo.getDetail(),
                positionDistributionInfo.getUpdateTime());
    }

    @ApiOperation(value= "???????????????????????????????????????", notes= "???????????????????????????????????????")
    @ServiceExecuter(description = "???????????????????????????????????????")
    @RequestMapping(value = "/drRank", method = RequestMethod.POST)
    @Audit(desc="??????????????????????????????", value="Staff.DoctorRank")
    public DrRankResponse drRankQuery(
            @RequestBody @Validated DrRankRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        DrRankQueryDO query = new DrRankQueryDO(
                request.getEndYear(), request.getAffiliationId());
        DrRankInfoDTO drRankInfo = detailStaffService.drRankQuery(query);
        return new DrRankResponse(drRankInfo.getDetail(), drRankInfo.getUpdateTime());
    }

    @ApiOperation(value= "???????????????????????????", notes= "???????????????????????????")
    @ServiceExecuter(description = "???????????????????????????")
    @RequestMapping(value = "/postAnalyze", method = RequestMethod.POST)
    @Audit(desc="??????????????????", value="Staff.PromotionAnalysis")
    public PostAnalyzeResponse postAnalyzeQuery(
            @RequestBody @Validated PostAnalyzeRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        PostAnalyzeQueryDO query = new PostAnalyzeQueryDO(
                request.getEndYear(), request.getAffiliationId(), request.getPositionValue());
        PostAnalyzeInfoDTO postAnalyzeInfo = detailStaffService.postAnalyzeQuery(query);
        return new PostAnalyzeResponse(postAnalyzeInfo.getDetail(), postAnalyzeInfo.getUpdateTime());
    }
}
