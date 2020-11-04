package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.UpdateDataQueryDO;
import cn.arp.trend.data.model.request.FrequencyRequest;
import cn.arp.trend.data.model.response.FrequencyResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.UpdateDataService;
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
import java.util.Map;

/**
 * 对应领域部分updateData.js
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午2:38
 **/
@Api(value="updateData", tags={"对应领域部分updateData.js"})
@RestController
@RequestMapping(value = "/area/update/data")
public class AreaUpdateDataController extends BaseController {

    @Resource
    private UpdateDataService updateDataService;

    /**
     * 领域部分updateData.js对应的/
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "领域部分updateData.js对应的/", notes= "领域部分updateData.js对应的/")
    @ServiceExecuter(description = "领域部分updateData.js对应的/")
    @RequestMapping(value = "/frequency", method = RequestMethod.POST)
    @Audit(desc="领域部分updateData.js对应的/")
    public FrequencyResponse frequencyQuery(
            @RequestBody @Validated FrequencyRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        UpdateDataQueryDO query = new UpdateDataQueryDO(
                request.getClassify(), request.getName());
        Map<String, String> result = updateDataService.queryAll(query);
        return new FrequencyResponse(
                result.get("unit"), result.get("upData"));
    }
}
