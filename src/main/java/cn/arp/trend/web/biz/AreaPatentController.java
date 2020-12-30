package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.DO.AreaPatentQueryDO;
import cn.arp.trend.data.model.DTO.AreaPatentInfoDTO;
import cn.arp.trend.data.model.request.AreaPatentRequest;
import cn.arp.trend.data.model.response.AreaPatentQueryResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.AreaPatentService;
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
@Api(value="patent",tags={"对应领域部分patent.js"})
@RestController
@RequestMapping(value = "/area/patent")
@RequirePermission(dataset=true)
public class AreaPatentController extends BaseController {

    @Resource
    private AreaPatentService areaPatentService;

    /**
     * patent.js对应的/
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "patent.js对应的/", notes= "patent.js对应的/")
    @ServiceExecuter(description = "patent.js对应的/")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @Audit(desc="历年中国发明专利、PCT专利申请数量、占比、各单位统计", value="Project.Patent")
    public AreaPatentQueryResponse query(
            @RequestBody @Validated AreaPatentRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaPatentQueryDO query = new AreaPatentQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaPatentInfoDTO areaPatentInfo = areaPatentService.query(query);
        return new AreaPatentQueryResponse(
                areaPatentInfo.getInventAry(),
                areaPatentInfo.getaRPInventAry(),
                areaPatentInfo.getpCTInventAry(),
                areaPatentInfo.getInventproportion(),
                areaPatentInfo.getpCTInventProportion(),
                areaPatentInfo.getOriginalUnitAry(),
                areaPatentInfo.getpCTOriginalUnitAry(),
                areaPatentInfo.getYearList(),
                areaPatentInfo.getUpdateTime());
    }
}
