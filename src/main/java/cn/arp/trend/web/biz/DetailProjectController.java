package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.converter.*;
import cn.arp.trend.data.model.request.*;
import cn.arp.trend.data.model.response.*;
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
            @RequestBody @Validated DetailProjectNsfcQueryRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectNsfcInfoDTO detailProjectNsfcInfo = detailProjectService.nsfcQuery(query);
        DetailProjectNsfcResponse response = DetailProjectNsfcConverter.INSTANCE.domain2dto
                (detailProjectNsfcInfo);
        response.setFundsUnit("万元");
        return response;
    }

    @ApiOperation(value= "对应detailProject.js的/kjb", notes= "对应detailProject.js的/kjb")
    @ServiceExecuter(description = "对应detailProject.js的/kjb")
    @RequestMapping(value = "/kjb", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/kjb")
    public DetailProjectKjbResponse kjbQuery(
            @RequestBody @Validated DetailProjectKjbQueryRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectKjbInfoDTO detailProjectKjbInfo = detailProjectService.kjbQuery(query);
        return DetailProjectKjbConverter.INSTANCE.domain2dto(detailProjectKjbInfo);
    }

    @ApiOperation(value= "对应detailProject.js的/xd", notes= "对应detailProject.js的/xd")
    @ServiceExecuter(description = "对应detailProject.js的/xd")
    @RequestMapping(value = "/xd", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/xd")
    public DetailProjectXdResponse xdQuery(
            @RequestBody @Validated DetailProjectXdQueryRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectXdInfoDTO detailProjectXdInfo = detailProjectService.xdQuery(query);
        return DetailProjectXdConverter.INSTANCE.domain2dto(detailProjectXdInfo);
    }

    @ApiOperation(value= "对应detailProject.js的/increase", notes= "对应detailProject.js的/increase")
    @ServiceExecuter(description = "对应detailProject.js的/increase")
    @RequestMapping(value = "/increase", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/increase")
    public DetailProjectIncreaseResponse increaseQuery(
            @RequestBody @Validated DetailProjectIncreaseQueryRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                0,
                0,
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectIncreaseInfoDTO detailProjectIncreaseInfo = detailProjectService.increaseQuery(query);
        return DetailProjectIncreaseConverter.INSTANCE.domain2dto(detailProjectIncreaseInfo);
    }

    @ApiOperation(value= "对应detailProject.js的/nsfcRelation", notes= "对应detailProject.js的/nsfcRelation")
    @ServiceExecuter(description = "对应detailProject.js的/nsfcRelation")
    @RequestMapping(value = "/nsfcRelation", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/nsfcRelation")
    public DetailProjectNsfcRelationResponse nsfcRelationQuery(
            @RequestBody @Validated DetailProjectNsfcRelationQueryRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectNsfcRelationInfoDTO detailProjectNsfcRelationInfo
                = detailProjectService.nsfcRelationQuery(query);
        return DetailProjectNsfcRelationConverter.INSTANCE.domain2dto(detailProjectNsfcRelationInfo);
    }

    @ApiOperation(value= "对应detailProject.js的/kjbRelation", notes= "对应detailProject.js的/kjbRelation")
    @ServiceExecuter(description = "对应detailProject.js的/kjbRelation")
    @RequestMapping(value = "/kjbRelation", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/kjbRelation")
    public DetailProjectKjbRelationResponse kjbRelationQuery(
            @RequestBody @Validated DetailProjectKjbRelationQueryRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectKjbRelationInfoDTO detailProjectKjbRelationInfo
                = detailProjectService.kjbRelationQuery(query);
        return DetailProjectKjbRelationConverter.INSTANCE.domain2dto(detailProjectKjbRelationInfo);
    }

    @ApiOperation(value= "对应detailProject.js的/xdRelation", notes= "对应detailProject.js的/xdRelation")
    @ServiceExecuter(description = "对应detailProject.js的/xdRelation")
    @RequestMapping(value = "/xdRelation", method = RequestMethod.POST)
    @Audit(desc="对应detailProject.js的/xdRelation")
    public DetailProjectXdRelationResponse xdRelationQuery(
            @RequestBody @Validated DetailProjectXdRelationQueryRequest request,
            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        ProjectQueryDO query = new ProjectQueryDO(
                Integer.parseInt(request.getStartYear()),
                Integer.parseInt(request.getEndYear()),
                request.getAffiliationIds(),
                request.getFieldNames());
        DetailProjectXdRelationInfoDTO detailProjectXdRelationInfo
                = detailProjectService.xdRelationQuery(query);
        return DetailProjectXdRelationConverter.INSTANCE.domain2dto(detailProjectXdRelationInfo);
    }
}
