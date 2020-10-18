package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.OverviewQueryDO;
import cn.arp.trend.data.model.DTO.OverviewInfoDTO;
import cn.arp.trend.data.model.converter.OverviewConverter;
import cn.arp.trend.data.model.request.OverviewRequest;
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
}
