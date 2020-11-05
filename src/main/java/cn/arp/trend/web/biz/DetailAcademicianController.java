package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;
import cn.arp.trend.data.model.request.CompareRequest;
import cn.arp.trend.data.model.request.ForeignRequest;
import cn.arp.trend.data.model.response.ForeignResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailAcademicianService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:下午1:38
 **/
@Api(value="detailAcademician",tags={"对应宏观部分detailAcademician.js"})
@RestController
@RequestMapping(value = "/detail/academician")
@RequirePermission(dataset=true)
public class DetailAcademicianController extends BaseController {

    @Resource
    private DetailAcademicianService detailAcademicianService;

    @ApiOperation(value= "外籍院士数据【新】", notes= "外籍院士数据【新】")
    @ServiceExecuter(description = "外籍院士数据【新】")
    @RequestMapping(value = "/foreign", method = RequestMethod.POST)
    @Audit(desc="外籍院士数据", value="Academician.Foreign")
    public ForeignResponse foreignQuery(@RequestBody ForeignRequest request) {
        ForeignInfoDTO foreignInfo = detailAcademicianService.foreignQuery(request.getAffiliation());
        return new ForeignResponse(foreignInfo.getTopCountry());
    }

    @ApiOperation(value= "detailAcademician.js对应的/", notes= "detailAcademician.js对应的/")
    @ServiceExecuter(description = "detailAcademician.js对应的/")
    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    @Audit(desc="本土院士数据", value="Academician.Domain")
    public List<Object> compareQuery(@RequestBody CompareRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        DACompareQueryDO query = new DACompareQueryDO();
        query.setAffiliation(request.getAffiliation());
        List<Object> result = detailAcademicianService.compareQuery(query);
        // 这玩意实在太复杂懒得做DTO转换了
        return result;
    }
}
