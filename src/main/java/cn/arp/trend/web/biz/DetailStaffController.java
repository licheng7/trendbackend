package cn.arp.trend.web.biz;

import cn.arp.trend.service.biz.DetailStaffService;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /*@ApiOperation(value= "年龄分布【新】", notes= "年龄分布【新】")
    @ServiceExecuter(description = "年龄分布【新】")
    @RequestMapping(value = "/ageDistribution", method = RequestMethod.POST)
    @Audit(desc="年龄分布【新】")
    public AgeDistributionResponse ageDistributionQuery(@RequestBody @Validated
                                                                    AgeDistributionRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);

        return null;
    }*/
}
