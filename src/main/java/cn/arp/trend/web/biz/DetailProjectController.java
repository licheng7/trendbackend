package cn.arp.trend.web.biz;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.DetailProjectIncreaseInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectKjbInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectKjbRelationInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectNsfcInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectNsfcRelationInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectXdInfoDTO;
import cn.arp.trend.data.model.DTO.DetailProjectXdRelationInfoDTO;
import cn.arp.trend.data.model.converter.DetailProjectIncreaseConverter;
import cn.arp.trend.data.model.converter.DetailProjectKjbConverter;
import cn.arp.trend.data.model.converter.DetailProjectKjbRelationConverter;
import cn.arp.trend.data.model.converter.DetailProjectNsfcConverter;
import cn.arp.trend.data.model.converter.DetailProjectNsfcRelationConverter;
import cn.arp.trend.data.model.converter.DetailProjectXdConverter;
import cn.arp.trend.data.model.converter.DetailProjectXdRelationConverter;
import cn.arp.trend.data.model.request.DetailProjectIncreaseQueryRequest;
import cn.arp.trend.data.model.request.DetailProjectKjbQueryRequest;
import cn.arp.trend.data.model.request.DetailProjectKjbRelationQueryRequest;
import cn.arp.trend.data.model.request.DetailProjectNsfcQueryRequest;
import cn.arp.trend.data.model.request.DetailProjectNsfcRelationQueryRequest;
import cn.arp.trend.data.model.request.DetailProjectXdQueryRequest;
import cn.arp.trend.data.model.request.DetailProjectXdRelationQueryRequest;
import cn.arp.trend.data.model.response.DetailProjectIncreaseResponse;
import cn.arp.trend.data.model.response.DetailProjectKjbRelationResponse;
import cn.arp.trend.data.model.response.DetailProjectKjbResponse;
import cn.arp.trend.data.model.response.DetailProjectNsfcRelationResponse;
import cn.arp.trend.data.model.response.DetailProjectNsfcResponse;
import cn.arp.trend.data.model.response.DetailProjectXdRelationResponse;
import cn.arp.trend.data.model.response.DetailProjectXdResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailProjectService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:??????3:42
 **/
@Api(value="detailProject",tags={"??????????????????detailProject.js"})
@RestController
@RequestMapping(value = "/detail/project")
@RequirePermission(dataset=true)
public class DetailProjectController extends BaseController {

    @Resource
    private DetailProjectService detailProjectService;

    /**
     * ??????detailProject.js???/nsfc
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "??????detailProject.js???/nsfc", notes= "??????detailProject.js???/nsfc")
    @ServiceExecuter(description = "??????detailProject.js???/nsfc")
    @RequestMapping(value = "/nsfc", method = RequestMethod.POST)
    @Audit(desc="????????????????????????????????????", value="Project.Nsfc")
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
        response.setFundsUnit("??????");
        return response;
    }

    /**
     * ??????detailProject.js???/kjb
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "??????detailProject.js???/kjb", notes= "??????detailProject.js???/kjb")
    @ServiceExecuter(description = "??????detailProject.js???/kjb")
    @RequestMapping(value = "/kjb", method = RequestMethod.POST)
    @Audit(desc="????????????????????????????????????", value="Project.Kjb")
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

    /**
     * ??????detailProject.js???/xd
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "??????detailProject.js???/xd", notes= "??????detailProject.js???/xd")
    @ServiceExecuter(description = "??????detailProject.js???/xd")
    @RequestMapping(value = "/xd", method = RequestMethod.POST)
    @Audit(desc="????????????????????????", value="Project.Xd")
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

    /**
     * ??????detailProject.js???/increase
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "??????detailProject.js???/increase", notes= "??????detailProject.js???/increase")
    @ServiceExecuter(description = "??????detailProject.js???/increase")
    @RequestMapping(value = "/increase", method = RequestMethod.POST)
    @Audit(desc="????????????????????????????????????????????????????????????????????????????????????", value="Project.Increasement")
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

    /**
     * ??????detailProject.js???/nsfcRelation
     * ???????????????????????????????????????????????????????????????node???????????????????????????node???????????????????????????????????????
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "??????detailProject.js???/nsfcRelation", notes= "??????detailProject.js???/nsfcRelation")
    @ServiceExecuter(description = "??????detailProject.js???/nsfcRelation")
    @RequestMapping(value = "/nsfcRelation", method = RequestMethod.POST)
    @Audit(desc="?????????????????????????????????????????????????????????????????????????????????", value="Project.NsfcRelation")
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

    /**
     * ??????detailProject.js???/kjbRelation
     * ???????????????????????????????????????????????????????????????node???????????????????????????node???????????????????????????????????????
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "??????detailProject.js???/kjbRelation", notes= "??????detailProject.js???/kjbRelation")
    @ServiceExecuter(description = "??????detailProject.js???/kjbRelation")
    @RequestMapping(value = "/kjbRelation", method = RequestMethod.POST)
    @Audit(desc="?????????????????????????????????????????????????????????????????????????????????", value="Project.KjbRelation")
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

    /**
     * ??????detailProject.js???/xdRelation
     * ???????????????????????????????????????????????????????????????node???????????????????????????node???????????????????????????????????????
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "??????detailProject.js???/xdRelation", notes= "??????detailProject.js???/xdRelation")
    @ServiceExecuter(description = "??????detailProject.js???/xdRelation")
    @RequestMapping(value = "/xdRelation", method = RequestMethod.POST)
    @Audit(desc="?????????????????????????????????????????????????????????????????????", value="Project.XdRelation")
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
