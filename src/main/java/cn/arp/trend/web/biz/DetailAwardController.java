package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.DetailAwardDetailQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardDistributionQueryDO;
import cn.arp.trend.data.model.DO.DetailAwardTrendQueryDO;
import cn.arp.trend.data.model.DTO.DetailAwardDetailInfoDTO;
import cn.arp.trend.data.model.DTO.DetailAwardDistributionInfoDTO;
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
@Api(value="DetailAward", tags={"对应宏观部分detailAward.js"})
@RestController
@RequestMapping(value = "/detail/award")
@RequirePermission(dataset=true)
public class DetailAwardController extends BaseController {

    @Resource
    private DetailAwardService detailAwardService;

    /**
     * detailAward.js对应的/trend
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "detailAward.js对应的/trend", notes= "detailAward.js对应的/trend")
    @ServiceExecuter(description = "detailAward.js对应的/trend")
    @RequestMapping(value = "/trend", method = RequestMethod.POST)
    @Audit(desc="历年国家奖、社会奖获奖趋势", value="Award.Trend")
    public AwardTrendResponse trendQuery(@RequestBody @Validated DetailAwardTrendRequest request, BindingResult
            bindingResult) throws RestError {
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
    @ApiOperation(value= "detailAward.js对应的/detail", notes= "detailAward.js对应的/detail")
    @ServiceExecuter(description = "detailAward.js对应的/detail")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @Audit(desc="国家奖、社会奖获奖详情", value="Award.Detail")
    public AwardDetailResponse detailQuery(@RequestBody @Validated DetailAwardDetailRequest request, BindingResult
            bindingResult) throws RestError {
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
    @ApiOperation(value= "detailAward.js对应的/distribution", notes= "detailAward.js对应的/distribution")
    @ServiceExecuter(description = "detailAward.js对应的/distribution")
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    @Audit(desc="各单位获奖数量和奖项领域分布", value="Award.Distribution")
    public AwardDistributionResponse distributionQuery(@RequestBody @Validated DetailAwardDistributionRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        DetailAwardDistributionQueryDO detailAwardDistributionQuery = new DetailAwardDistributionQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getUnitIdAry()
        );
        DetailAwardDistributionInfoDTO detailAwardDistributionInfo = detailAwardService.distributionQuery
                (detailAwardDistributionQuery);
        return new AwardDistributionResponse(
                detailAwardDistributionInfo.getAwardPie(),
                detailAwardDistributionInfo.getAwardAry()
        );
    }
}
