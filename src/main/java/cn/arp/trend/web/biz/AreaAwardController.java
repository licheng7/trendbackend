package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.DetailAwardDetailQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardDistributionQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardTrendQueryDO;
import cn.arp.trend.data.model.DTO.AreaAwardDistributionInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardDetailInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardTrendInfoDTO;
import cn.arp.trend.data.model.converter.DetailAwardDetailInfoConverter;
import cn.arp.trend.data.model.request.DetailAwardDetailRequest;
import cn.arp.trend.data.model.request.DetailAwardDistributionRequest;
import cn.arp.trend.data.model.request.DetailAwardTrendRequest;
import cn.arp.trend.data.model.response.AwardDetailResponse;
import cn.arp.trend.data.model.response.AwardDistributionResponse;
import cn.arp.trend.data.model.response.AwardTrendResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailAwardService;
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
@Api(value="AreaAward", tags={"对应领域部分detailAward.js"})
@RestController
@RequestMapping(value = "/area/award")
public class AreaAwardController extends BaseController {

    @Resource
    private DetailAwardService detailAwardService;

    /**
     * detailAward.js对应的/trend
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "领域部分award.js对应的/trend", notes= "领域部分award.js对应的/trend")
    @ServiceExecuter(description = "领域部分award.js对应的/trend")
    @RequestMapping(value = "/trend", method = RequestMethod.POST)
    @Audit(desc="领域部分award.js对应的/trend")
    public AwardTrendResponse trendQuery(
            @RequestBody @Validated DetailAwardTrendRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        DetailAwardTrendQueryDO detailAwardTrendQuery = new DetailAwardTrendQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getUnitIdAry()
        );
        DetailAwardTrendInfoDTO detailAwardTrendInfo = detailAwardService.trendQuery(detailAwardTrendQuery);
        return new AwardTrendResponse(
                detailAwardTrendInfo.getUpdateTime(),
                detailAwardTrendInfo.getYearList(),
                detailAwardTrendInfo.getStateTendency(),
                detailAwardTrendInfo.getElseTendency(),
                detailAwardTrendInfo.getSumTendency()
        );
    }

    /**
     * detailAward.js对应的/detail
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "领域部分award.js对应的/detail", notes= "领域部分award.js对应的/detail")
    @ServiceExecuter(description = "领域部分award.js对应的/detail")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @Audit(desc="领域部分award.js对应的/detail")
    public AwardDetailResponse detailQuery(
            @RequestBody @Validated DetailAwardDetailRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        DetailAwardDetailQueryDO detailAwardDetailQuery = new DetailAwardDetailQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getUnitIdAry()
        );
        DetailAwardDetailInfoDTO detailAwardDetailInfo = detailAwardService.detailQuery
                (detailAwardDetailQuery);
        return DetailAwardDetailInfoConverter.INSTANCE.domain2dto(detailAwardDetailInfo);
    }

    /**
     * detailAward.js对应的/distribution
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "领域部分award.js对应的/distribution", notes= "领域部分award.js对应的/distribution")
    @ServiceExecuter(description = "领域部分award.js对应的/distribution")
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    @Audit(desc="领域部分award.js对应的/distribution")
    public AwardDistributionResponse distributionQuery(
            @RequestBody @Validated DetailAwardDistributionRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        DetailAwardDistributionQueryDO detailAwardDistributionQuery = new DetailAwardDistributionQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getUnitIdAry()
        );
        AreaAwardDistributionInfoDTO detailAwardDistributionInfo = detailAwardService.areaDistributionQuery
                (detailAwardDistributionQuery);
        return new AwardDistributionResponse(
                detailAwardDistributionInfo.getAwardOriginalAry(),
                detailAwardDistributionInfo.getAwardAry()
        );
    }
}
