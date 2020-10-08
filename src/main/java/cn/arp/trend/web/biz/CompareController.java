package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DTO.FundsInfoDTO;
import cn.arp.trend.data.model.request.FundsRequest;
import cn.arp.trend.data.model.response.FundsResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.CompareService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
