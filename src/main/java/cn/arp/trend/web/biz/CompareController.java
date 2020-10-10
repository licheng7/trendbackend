package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.FacilityInfoDTO;
import cn.arp.trend.data.model.DTO.FinanceInfoDTO;
import cn.arp.trend.data.model.DTO.FundsInfoDTO;
import cn.arp.trend.data.model.DTO.ProjectInfoDTO;
import cn.arp.trend.data.model.converter.MapResultConverter;
import cn.arp.trend.data.model.converter.ProjectInfoConverter;
import cn.arp.trend.data.model.converter.ProjectQueryConverter;
import cn.arp.trend.data.model.request.FinanceRequest;
import cn.arp.trend.data.model.request.FundsRequest;
import cn.arp.trend.data.model.request.ProjectQueryRequest;
import cn.arp.trend.data.model.response.*;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.CompareService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对应Node代码中compare.js
 *
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:31
 **/
@Api(value="compare",tags={"对应宏观部分compare.js"})
@RestController
@RequestMapping(value = "/compare")
public class CompareController extends BaseController {

    @Resource
    private CompareService compareService;

    @ApiOperation(value= "科研投入-总经费", notes= "科研投入-总经费")
    @ServiceExecuter(description = "科研投入-总经费")
    @RequestMapping(value = "/funds", method = RequestMethod.POST)
    @Audit(desc="科研投入-总经费")
    public FundsResponse fundsQuery(@Validated FundsRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        FundsInfoDTO fundsInfo = compareService.fundsQuery(request.getStartYear(), request
                .getEndYear());
        return new FundsResponse(fundsInfo.getYear(), fundsInfo.getDetail(), fundsInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-财政拨款", notes= "科研投入-财政拨款")
    @ServiceExecuter(description = "科研投入-财政拨款")
    @RequestMapping(value = "/finance", method = RequestMethod.POST)
    @Audit(desc="科研投入-财政拨款")
    public FinanceResponse financeQuery(@Validated FinanceRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        FinanceInfoDTO financeInfo = compareService.financeQuery(request.getStartYear(), request
                .getEndYear());
        return new FinanceResponse(financeInfo.getYear(), financeInfo.getDetail(), financeInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-国家科研设施", notes= "科研投入-国家科研设施")
    @ServiceExecuter(description = "科研投入-国家科研设施")
    @RequestMapping(value = "/facility", method = RequestMethod.POST)
    @Audit(desc="科研投入-国家科研设施")
    public FacilityResponse facilityQuery() {
        FacilityInfoDTO facilityInfo = compareService.facilityQuery();
        MapResultConverter mapResultConverter = MapResultConverter.INSTANCE;
        return new FacilityResponse(facilityInfo.getYearList(),
                mapResultConverter.domain2dto(facilityInfo.getPlatformList()),
                mapResultConverter.domain2dto(facilityInfo.getKeylabList()),
                facilityInfo.getUpdateTimeBas(),
                facilityInfo.getUpdateTimeLab());
    }

    @ApiOperation(value= "科研投入-项目", notes= "科研投入-项目")
    @ServiceExecuter(description = "科研投入-项目")
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @Audit(desc="科研投入-项目")
    public ProjectResponse projectQuery(ProjectQueryRequest request) throws Exception {
        ProjectQueryDO projectQuery = ProjectQueryConverter.INSTANCE.domain2dto(request);
        ProjectInfoDTO projectInfo = compareService.projectQuery(projectQuery);
        ProjectResponse response = ProjectInfoConverter.INSTANCE.domain2dto(projectInfo);
        List<ProjectOrderResult> order = Lists.newArrayList();
        for(ProjectInfoDTO.OrderDTO orderDTO : projectInfo.getOrder()) {
            order.add(new ProjectOrderResult(
                    MapResultConverter.INSTANCE.domain2dto(orderDTO.getNsfc()),
                    MapResultConverter.INSTANCE.domain2dto(orderDTO.getStd()),
                    MapResultConverter.INSTANCE.domain2dto(orderDTO.getXd())
            ));
        }
        response.setOrder(order);
        return response;
    }
}
