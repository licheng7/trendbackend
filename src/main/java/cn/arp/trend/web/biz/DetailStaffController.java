package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.*;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.converter.IncreaseTrendDetailConverter;
import cn.arp.trend.data.model.converter.MapResultConverter;
import cn.arp.trend.data.model.request.*;
import cn.arp.trend.data.model.response.*;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailStaffService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午12:56
 **/
@Api(value="detailStaff",tags={"对应宏观部分detailStaff.js"})
@RestController
@RequestMapping(value = "/detail/staff")
@RequirePermission(dataset=true)
public class DetailStaffController extends BaseController {

    @Resource
    private DetailStaffService detailStaffService;

    /**
     * 年龄分布【新】
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "年龄分布【新】", notes= "年龄分布【新】")
    @ServiceExecuter(description = "年龄分布【新】")
    @RequestMapping(value = "/ageDistribution", method = RequestMethod.POST)
    @Audit(desc="在职员工年龄分布", value="Staff.AgeDistribution")
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
     * 学历分布【新】
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "学历分布【新】", notes= "学历分布【新】")
    @ServiceExecuter(description = "学历分布【新】")
    @RequestMapping(value = "/childLevelDistribution", method = RequestMethod.POST)
    @Audit(desc="在职员工学历分布", value="Staff.EducationLevel")
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

    @ApiOperation(value= "在职职工人员增长趋势【新】", notes= "在职职工人员增长趋势【新】")
    @ServiceExecuter(description = "在职职工人员增长趋势【新】")
    @RequestMapping(value = "/increaseTrend", method = RequestMethod.POST)
    @Audit(desc="在职职工人员增长趋势", value="Staff.Trend")
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

    @ApiOperation(value= "人员类型整体分布【新】", notes= "人员类型整体分布【新】")
    @ServiceExecuter(description = "人员类型整体分布【新】")
    @RequestMapping(value = "/personTypeDistribution", method = RequestMethod.POST)
    @Audit(desc="人员类型整体分布", value="Staff.CategoryDistribution")
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

    @ApiOperation(value= "在职职工岗位分布【新】", notes= "在职职工岗位分布【新】")
    @ServiceExecuter(description = "在职职工岗位分布【新】")
    @RequestMapping(value = "/postDistribution", method = RequestMethod.POST)
    @Audit(desc="在职职工岗位分布", value="Staff.PositionDistribution")
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

    @ApiOperation(value= "专业技术人员职称分布【新】", notes= "专业技术人员职称分布【新】")
    @ServiceExecuter(description = "专业技术人员职称分布【新】")
    @RequestMapping(value = "/positionDistribution", method = RequestMethod.POST)
    @Audit(desc="专业技术人员职称分布", value="Staff.TitleDistribution")
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

    @ApiOperation(value= "博士学历所占比例排名【新】", notes= "博士学历所占比例排名【新】")
    @ServiceExecuter(description = "博士学历所占比例排名【新】")
    @RequestMapping(value = "/drRank", method = RequestMethod.POST)
    @Audit(desc="各研究所博士学历人数", value="Staff.DoctorRank")
    public DrRankResponse drRankQuery(
            @RequestBody @Validated DrRankRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        DrRankQueryDO query = new DrRankQueryDO(
                request.getEndYear(), request.getAffiliationId());
        DrRankInfoDTO drRankInfo = detailStaffService.drRankQuery(query);
        return new DrRankResponse(drRankInfo.getDetail(), drRankInfo.getUpdateTime());
    }

    @ApiOperation(value= "岗位晋升分析【新】", notes= "岗位晋升分析【新】")
    @ServiceExecuter(description = "岗位晋升分析【新】")
    @RequestMapping(value = "/postAnalyze", method = RequestMethod.POST)
    @Audit(desc="岗位晋升分析", value="Staff.PromotionAnalysis")
    public PostAnalyzeResponse postAnalyzeQuery(
            @RequestBody @Validated PostAnalyzeRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        PostAnalyzeQueryDO query = new PostAnalyzeQueryDO(
                request.getEndYear(), request.getAffiliationId(), request.getPositionValue());
        PostAnalyzeInfoDTO postAnalyzeInfo = detailStaffService.postAnalyzeQuery(query);
        return new PostAnalyzeResponse(postAnalyzeInfo.getDetail(), postAnalyzeInfo.getUpdateTime());
    }
}
