package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.DetailProjectKjbInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectNsfcInfoDTO;
import cn.arp.trend.data.model.converter.DetailProjectKjbConverter;
import cn.arp.trend.data.model.converter.DetailProjectNsfcConverter;
import cn.arp.trend.data.model.request.DetailProjectKjbQueryRequest;
import cn.arp.trend.data.model.request.DetailProjectNsfcQueryRequest;
import cn.arp.trend.data.model.response.DetailProjectKjbResponse;
import cn.arp.trend.data.model.response.DetailProjectNsfcResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailProjectService;
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
 * Date:2020/9/29
 * Time:下午3:42
 **/
@Api(value="detailProject",tags={"对应宏观部分detailProject.js"})
@RestController
@RequestMapping(value = "/detailProject")
public class DetailProjectController extends BaseController {

    @Resource
    private DetailProjectService detailProjectService;

    @ApiOperation(value= "对应detailProject.js的/nsfc", notes= "对应detailProject.js的/nsfc")
    @ServiceExecuter(description = "对应detailProject.js的/nsfc")
    @RequestMapping(value = "/nsfc", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/nsfc")
    public DetailProjectNsfcResponse nsfcQuery(
            @RequestBody @Validated DetailProjectNsfcQueryRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectNsfcInfoDTO detailProjectNsfcInfo = detailProjectService.nsfcQuery(query);
        return DetailProjectNsfcConverter.INSTANCE.domain2dto(detailProjectNsfcInfo);
    }

    @ApiOperation(value= "对应detailProject.js的/kjb", notes= "对应detailProject.js的/kjb")
    @ServiceExecuter(description = "对应detailProject.js的/kjb")
    @RequestMapping(value = "/kjb", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/kjb")
    public DetailProjectKjbResponse kjbQuery(
            @RequestBody @Validated DetailProjectKjbQueryRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectKjbInfoDTO detailProjectKjbInfo = detailProjectService.kjbQuery(query);
        return DetailProjectKjbConverter.INSTANCE.domain2dto(detailProjectKjbInfo);
    }
}
