package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.DistributionAffiliationQueryDO;
import cn.arp.trend.data.model.DO.DistributionFieldQueryDO;
import cn.arp.trend.data.model.DO.YoungEliteQueryDO;
import cn.arp.trend.data.model.DTO.DistributionAffiliationInfoDTO;
import cn.arp.trend.data.model.DTO.DistributionFieldInfoDTO;
import cn.arp.trend.data.model.DTO.YoungEliteTrendInfoDTO;
import cn.arp.trend.data.model.DTO.YoungProjectInfoDTO;
import cn.arp.trend.data.model.converter.YoungProjectInfoConverter;
import cn.arp.trend.data.model.request.DistributionAffiliationRequest;
import cn.arp.trend.data.model.request.DistributionFieldRequest;
import cn.arp.trend.data.model.request.YoungEliteRequest;
import cn.arp.trend.data.model.response.DistributionAffiliationResponse;
import cn.arp.trend.data.model.response.DistributionFieldResponse;
import cn.arp.trend.data.model.response.YoungProjectResponse;
import cn.arp.trend.data.model.response.YoungTrendResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailYoungEliteService;
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
 * Date:2020/10/13
 * Time:下午2:38
 **/
@Api(value="detailYoungElite",tags={"对应宏观部分detailYoungElite.js"})
@RestController
@RequestMapping(value = "/detail/young/elite")
public class DetailYoungEliteController extends BaseController {

    @Resource
    private DetailYoungEliteService detailYoungEliteService;

    /**
     * detailYoungElite.js对应的/trend
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "detailYoungElite.js对应的/trend", notes= "detailYoungElite.js对应的/trend")
    @ServiceExecuter(description = "detailYoungElite.js对应的/trend")
    @RequestMapping(value = "/trend", method = RequestMethod.POST)
    @Audit(desc="detailYoungElite.js对应的/trend")
    public YoungTrendResponse trendQuery(@RequestBody @Validated YoungEliteRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        YoungEliteQueryDO youngEliteQuery = new YoungEliteQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getAffiliationId(),
                request.getFieldName()
        );
        YoungEliteTrendInfoDTO youngEliteTrendInfo = detailYoungEliteService.trendQuery(youngEliteQuery);
        return new YoungTrendResponse(
                youngEliteTrendInfo.getYear(),
                youngEliteTrendInfo.getYoungElite(),
                youngEliteTrendInfo.getStaffNumber(),
                youngEliteTrendInfo.getProportion(),
                "2019年10月"
        );
    }

    /**
     * detailYoungElite.js对应的/distribution/field
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "detailYoungElite.js对应的/distribution/field", notes= "detailYoungElite.js对应的/distribution/field")
    @ServiceExecuter(description = "detailYoungElite.js对应的/distribution/field")
    @RequestMapping(value = "/distribution/field", method = RequestMethod.POST)
    @Audit(desc="detailYoungElite.js对应的/distribution/field")
    public DistributionFieldResponse distributionFieldQuery(
            @RequestBody @Validated DistributionFieldRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        DistributionFieldQueryDO distributionFieldQuery = new DistributionFieldQueryDO(
                request.getYear(),
                request.getAffiliationId(),
                request.getFieldName()
        );
        DistributionFieldInfoDTO distributionFieldInfo = detailYoungEliteService.distributionFieldQuery(distributionFieldQuery);
        return new DistributionFieldResponse(
                distributionFieldInfo.getFields(),
                distributionFieldInfo.getYoungElite(),
                distributionFieldInfo.getStaffNumber(),
                distributionFieldInfo.getProportion(),
                "2019年10月"
        );
    }

    /**
     * detailYoungElite.js对应的/distribution/affiliation
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "detailYoungElite.js对应的/distribution/affiliation", notes= "detailYoungElite.js对应的/distribution/affiliation")
    @ServiceExecuter(description = "detailYoungElite.js对应的/distribution/affiliation")
    @RequestMapping(value = "/distribution/affiliation", method = RequestMethod.POST)
    @Audit(desc="detailYoungElite.js对应的/distribution/affiliation")
    public DistributionAffiliationResponse distributionAffiliationQuery(
            @RequestBody @Validated DistributionAffiliationRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        DistributionAffiliationQueryDO distributionAffiliationQuery = new DistributionAffiliationQueryDO(
                request.getYear(),
                request.getAffiliationId(),
                request.getFieldName()
        );
        DistributionAffiliationInfoDTO distributionAffiliationInfo = detailYoungEliteService
                .distributionAffiliationQuery(distributionAffiliationQuery);
        return new DistributionAffiliationResponse(
                "2019年10月",
                distributionAffiliationInfo.getDetail()
        );
    }

    /**
     * detailYoungElite.js对应的/project
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "detailYoungElite.js对应的/project", notes= "detailYoungElite.js对应的/project")
    @ServiceExecuter(description = "detailYoungElite.js对应的/project")
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @Audit(desc="detailYoungElite.js对应的/project")
    public YoungProjectResponse projectQuery(
            @RequestBody @Validated YoungEliteRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        YoungEliteQueryDO youngEliteQuery = new YoungEliteQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getAffiliationId(),
                request.getFieldName()
        );
        YoungProjectInfoDTO distributionAffiliationInfo = detailYoungEliteService.projectQuery(youngEliteQuery);
        return YoungProjectInfoConverter.INSTANCE.domain2dto(distributionAffiliationInfo);
    }
}
