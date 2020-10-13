package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AgeDistributionQueryDO;
import cn.arp.trend.data.model.DTO.AgeDistributionInfoDTO;
import cn.arp.trend.data.model.converter.MapResultConverter;
import cn.arp.trend.data.model.request.AgeDistributionRequest;
import cn.arp.trend.data.model.response.AgeDistributionResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailStaffService;
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
@Api(value="detailStaff",tags={"对应宏观部分detailStaff.js"})
@RestController
@RequestMapping(value = "/detail/staff")
public class DetailStaffController extends BaseController {

    @Resource
    private DetailStaffService detailStaffService;

    @ApiOperation(value= "年龄分布【新】", notes= "年龄分布【新】")
    @ServiceExecuter(description = "年龄分布【新】")
    @RequestMapping(value = "/ageDistribution", method = RequestMethod.POST)
    @Audit(desc="年龄分布【新】")
    public AgeDistributionResponse ageDistributionQuery(
            @RequestBody @Validated AgeDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AgeDistributionQueryDO query = new AgeDistributionQueryDO(
                request.getEndYear(), request.getAffiliationId());
        AgeDistributionInfoDTO ageDistributionInfo = detailStaffService.ageDistributionQuery(query);
        AgeDistributionResponse response = new AgeDistributionResponse(
                ageDistributionInfo.getUpdateTime(),
                MapResultConverter.INSTANCE.domain2dto(ageDistributionInfo.getDetail()),
                ageDistributionInfo.getResultList()
        );
        return response;
    }

    /*@ApiOperation(value= "学历分布【新】", notes= "学历分布【新】")
    @ServiceExecuter(description = "学历分布【新】")
    @RequestMapping(value = "/childLevelDistribution", method = RequestMethod.POST)
    @Audit(desc="学历分布【新】")
    public AgeDistributionResponse childLevelDistributionQuery(
            @RequestBody @Validated AgeDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AgeDistributionQueryDO query = new AgeDistributionQueryDO(
                request.getEndYear(), request.getAffiliationId());
        AgeDistributionInfoDTO ageDistributionInfo = detailStaffService.ageDistributionQuery(query);
        AgeDistributionResponse response = new AgeDistributionResponse(
                ageDistributionInfo.getUpdateTime(),
                MapResultConverter.INSTANCE.domain2dto(ageDistributionInfo.getDetail()),
                ageDistributionInfo.getResultList()
        );
        return response;
    }*/
}
