package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AssetDetailQueryDO;
import cn.arp.trend.data.model.DO.AssetIncomeQueryDO;
import cn.arp.trend.data.model.DO.ExecutionTrendQueryDO;
import cn.arp.trend.data.model.DO.OverviewQueryDO;
import cn.arp.trend.data.model.DTO.AssetDetailInfoDTO;
import cn.arp.trend.data.model.DTO.AssetIncomeInfoDTO;
import cn.arp.trend.data.model.DTO.ExecutionTrendInfoDTO;
import cn.arp.trend.data.model.DTO.OverviewInfoDTO;
import cn.arp.trend.data.model.converter.OverviewConverter;
import cn.arp.trend.data.model.request.AssetIncomeRequest;
import cn.arp.trend.data.model.request.ExecutionTrendRequest;
import cn.arp.trend.data.model.request.OverviewRequest;
import cn.arp.trend.data.model.response.AssetDetailResponse;
import cn.arp.trend.data.model.response.AssetIncomeResponse;
import cn.arp.trend.data.model.response.ExecutionTrendResponse;
import cn.arp.trend.data.model.response.OverviewResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailAssetService;
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
@Api(value="detailAsset",tags={"对应宏观部分detailAsset.js"})
@RestController
@RequestMapping(value = "/detail/asset")
public class DetailAssetController extends BaseController {

    @Resource
    private DetailAssetService detailAssetService;

    @ApiOperation(value= "对应detailAsset.js/overview", notes= "对应detailAsset.js/overview")
    @ServiceExecuter(description = "对应detailAsset.js/overview")
    @RequestMapping(value = "/overview", method = RequestMethod.POST)
    @Audit(desc="对应detailAsset.js/overview")
    public OverviewResponse overviewQuery(
            @RequestBody @Validated OverviewRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        OverviewQueryDO query = new OverviewQueryDO(request.getYear(), request.getAffiliationId());
        OverviewInfoDTO overviewInfo = detailAssetService.overviewQuery(query);
        return OverviewConverter.INSTANCE.domain2dto(overviewInfo);
    }

    @ApiOperation(value= "对应detailAsset.js/detail", notes= "对应detailAsset.js/detail")
    @ServiceExecuter(description = "对应detailAsset.js/detail")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @Audit(desc="对应detailAsset.js/detail")
    public AssetDetailResponse detailQuery(
            @RequestBody @Validated AssetIncomeRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AssetDetailQueryDO query = new AssetDetailQueryDO(request.getYear(), request.getAffiliationId());
        AssetDetailInfoDTO assetDetailInfo = detailAssetService.detailQuery(query);
        return new AssetDetailResponse(
                assetDetailInfo.getUpdateTime(),
                assetDetailInfo.getDetail(),
                assetDetailInfo.getIncomeDistribution(),
                assetDetailInfo.getOutcomeDistribution(),
                assetDetailInfo.getResultArray()
        );
    }

    @ApiOperation(value= "对应detailAsset.js/income", notes= "对应detailAsset.js/income")
    @ServiceExecuter(description = "对应detailAsset.js/income")
    @RequestMapping(value = "/income", method = RequestMethod.POST)
    @Audit(desc="对应detailAsset.js/income")
    public AssetIncomeResponse incomeQuery(
            @RequestBody @Validated AssetIncomeRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AssetIncomeQueryDO query = new AssetIncomeQueryDO(request.getYear(), String.valueOf(Integer
                .parseInt(request.getYear()) - 4), request.getAffiliationId(), request.getFieldName
                ());
        AssetIncomeInfoDTO sssetIncomeInfo = detailAssetService.incomeQuery(query);
        return new AssetIncomeResponse(
                sssetIncomeInfo.getUpdateTime(),
                sssetIncomeInfo.getDetail(),
                sssetIncomeInfo.getLegend(),
                sssetIncomeInfo.getLegendAttr()
        );
    }

    @ApiOperation(value= "对应detailAsset.js/execution_trend", notes= "对应detailAsset.js/execution_trend")
    @ServiceExecuter(description = "对应detailAsset.js/execution_trend")
    @RequestMapping(value = "/execution_trend", method = RequestMethod.POST)
    @Audit(desc="对应detailAsset.js/execution_trend")
    public ExecutionTrendResponse executionTrendQuery(
            @RequestBody @Validated ExecutionTrendRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ExecutionTrendQueryDO query = new ExecutionTrendQueryDO(request.getYear(), request.getAffiliationId());
        ExecutionTrendInfoDTO sssetIncomeInfo = detailAssetService.executionTrendQuery(query);
        return new ExecutionTrendResponse(sssetIncomeInfo.getDetail(), sssetIncomeInfo.getResultArray());
    }
}
