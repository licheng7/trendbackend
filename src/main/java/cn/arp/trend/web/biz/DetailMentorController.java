package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.DoctoralSupervisorQueryDO;
import cn.arp.trend.data.model.DTO.DoctoralSupervisorInfoDTO;
import cn.arp.trend.data.model.request.DoctoralSupervisorRequest;
import cn.arp.trend.data.model.response.DoctoralSupervisorResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailMentorService;
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
@Api(value="detailMentor",tags={"对应宏观部分detailMentor.js"})
@RestController
@RequestMapping(value = "/detail/mentor")
public class DetailMentorController extends BaseController {

    @Resource
    private DetailMentorService detailMentorService;

    @ApiOperation(value= "博导", notes= "博导")
    @ServiceExecuter(description = "博导")
    @RequestMapping(value = "/distribution/d", method = RequestMethod.POST)
    @Audit(desc="博导")
    public DoctoralSupervisorResponse doctoralupervisorQuery(
            @RequestBody @Validated DoctoralSupervisorRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        DoctoralSupervisorQueryDO query = new DoctoralSupervisorQueryDO(
                request.getEndYear(), request.getAffiliationId(), request.getFieldName());
        DoctoralSupervisorInfoDTO doctoralSupervisorInfo = detailMentorService
                .doctoralupervisorQuery(query);
        return new DoctoralSupervisorResponse(doctoralSupervisorInfo.getDistributionAge(),
                doctoralSupervisorInfo.getDistributionField());
    }
}
