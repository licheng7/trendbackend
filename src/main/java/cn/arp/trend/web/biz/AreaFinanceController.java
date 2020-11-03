package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AreaFinanceQueryDO;
import cn.arp.trend.data.model.DTO.AreaFinanceInfoDTO;
import cn.arp.trend.data.model.DTO.AreaFinanceOverviewInfoDTO;
import cn.arp.trend.data.model.request.AreaFinanceRequest;
import cn.arp.trend.data.model.response.AreaFinanceIncomeResponse;
import cn.arp.trend.data.model.response.AreaFinanceOutcomeResponse;
import cn.arp.trend.data.model.response.AreaFinanceOverviewResponse;
import cn.arp.trend.data.model.response.AreaFinanceRankResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.AreaFinanceService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午2:38
 **/
@Api(value="financeEdu",tags={"对应领域部分finance.js"})
@RestController
@RequestMapping(value = "/area/finance")
public class AreaFinanceController extends BaseController {

    @Resource
    private AreaFinanceService areaFinanceService;

    /**
     * finance.js对应的/overview
     *
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "finance.js对应的/overview", notes= "finance.js对应的/overview")
    @ServiceExecuter(description = "finance.js对应的/overview")
    @RequestMapping(value = "/overview", method = RequestMethod.POST)
    @Audit(desc="finance.js对应的/overview")
    public AreaFinanceOverviewResponse overviewQuery(
            @RequestBody @Validated AreaFinanceRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaFinanceQueryDO query = new AreaFinanceQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaFinanceOverviewInfoDTO areaFinanceInfo = areaFinanceService.overviewQuery(query);
        return new AreaFinanceOverviewResponse(areaFinanceInfo.getResultList());
    }

    /**
     * finance.js对应的/income
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "finance.js对应的/income", notes= "finance.js对应的/income")
    @ServiceExecuter(description = "finance.js对应的/income")
    @RequestMapping(value = "/income", method = RequestMethod.POST)
    @Audit(desc="finance.js对应的/income")
    public AreaFinanceIncomeResponse incomeQuery(
            @RequestBody @Validated AreaFinanceRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaFinanceQueryDO query = new AreaFinanceQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaFinanceInfoDTO areaFinanceInfo = areaFinanceService.incomeQuery(query);
        return new AreaFinanceIncomeResponse(
                areaFinanceInfo.getDataAry(), areaFinanceInfo.getYearList());
    }

    /**
     * finance.js对应的/outcome
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "finance.js对应的/outcome", notes= "finance.js对应的/outcome")
    @ServiceExecuter(description = "finance.js对应的/outcome")
    @RequestMapping(value = "/outcome", method = RequestMethod.POST)
    @Audit(desc="finance.js对应的/outcome")
    public AreaFinanceOutcomeResponse outcomeQuery(
            @RequestBody @Validated AreaFinanceRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaFinanceQueryDO query = new AreaFinanceQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaFinanceInfoDTO areaFinanceInfo = areaFinanceService.outcomeQuery(query);
        return new AreaFinanceOutcomeResponse(
                areaFinanceInfo.getDataAry(), areaFinanceInfo.getYearList());
    }

    /**
     * finance.js对应的/rank
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "finance.js对应的/rank", notes= "finance.js对应的/rank")
    @ServiceExecuter(description = "finance.js对应的/rank")
    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    @Audit(desc="finance.js对应的/rank")
    public AreaFinanceRankResponse rankQuery(
            @RequestBody @Validated AreaFinanceRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaFinanceQueryDO query = new AreaFinanceQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaFinanceInfoDTO areaFinanceInfo = areaFinanceService.rankQuery(query);
        List<Map<String, Object>> result = areaFinanceInfo.getResultList();
        List<List<Map<String, Object>>> response = Lists.newArrayList();
        response.add(result);
        return new AreaFinanceRankResponse(response);
    }
}
