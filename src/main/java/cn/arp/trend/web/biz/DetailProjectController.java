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
@RequestMapping(value = "/detail/project")
@RequirePermission(dataset=true)
public class DetailProjectController extends BaseController {

    @Resource
    private DetailProjectService detailProjectService;

    /**
     * 对应detailProject.js的/nsfc
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "对应detailProject.js的/nsfc", notes= "对应detailProject.js的/nsfc")
    @ServiceExecuter(description = "对应detailProject.js的/nsfc")
    @RequestMapping(value = "/nsfc", method = RequestMethod.POST)
    @Audit(desc="国家自然科学基金项目详情", value="Project.Nsfc")
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

    /**
     * 对应detailProject.js的/kjb
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "对应detailProject.js的/kjb", notes= "对应detailProject.js的/kjb")
    @ServiceExecuter(description = "对应detailProject.js的/kjb")
    @RequestMapping(value = "/kjb", method = RequestMethod.POST)
    @Audit(desc="国家重点研发计划项目详情", value="Project.Kjb")
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
     * 对应detailProject.js的/xd
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "对应detailProject.js的/xd", notes= "对应detailProject.js的/xd")
    @ServiceExecuter(description = "对应detailProject.js的/xd")
    @RequestMapping(value = "/xd", method = RequestMethod.POST)
    @Audit(desc="先导专项课题详情", value="Project.Xd")
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
     * 对应detailProject.js的/increase
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "对应detailProject.js的/increase", notes= "对应detailProject.js的/increase")
    @ServiceExecuter(description = "对应detailProject.js的/increase")
    @RequestMapping(value = "/increase", method = RequestMethod.POST)
    @Audit(desc="国自然项目、重点研发项目、先导课题新增项目数量和经费数量", value="Project.Increasement")
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
     * 对应detailProject.js的/nsfcRelation
     * 结果已经比对，返回值的第一项和第二项我的比node的要多，感觉是因为node少加了一次，我的更符合逻辑
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "对应detailProject.js的/nsfcRelation", notes= "对应detailProject.js的/nsfcRelation")
    @ServiceExecuter(description = "对应detailProject.js的/nsfcRelation")
    @RequestMapping(value = "/nsfcRelation", method = RequestMethod.POST)
    @Audit(desc="各单位国家自然科学基金项目数量、经费数量和人员数量关系", value="Project.NsfcRelation")
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
     * 对应detailProject.js的/kjbRelation
     * 结果已经比对，返回值的第一项和第二项我的比node的要多，感觉是因为node少加了一次，我的更符合逻辑
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "对应detailProject.js的/kjbRelation", notes= "对应detailProject.js的/kjbRelation")
    @ServiceExecuter(description = "对应detailProject.js的/kjbRelation")
    @RequestMapping(value = "/kjbRelation", method = RequestMethod.POST)
    @Audit(desc="各单位国家重点研发计划项目数量、经费数量和人员数量关系", value="Project.KjbRelation")
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
     * 对应detailProject.js的/xdRelation
     * 结果已经比对，返回值的第一项和第二项我的比node的要多，感觉是因为node少加了一次，我的更符合逻辑
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "对应detailProject.js的/xdRelation", notes= "对应detailProject.js的/xdRelation")
    @ServiceExecuter(description = "对应detailProject.js的/xdRelation")
    @RequestMapping(value = "/xdRelation", method = RequestMethod.POST)
    @Audit(desc="各单位先导专项课题数量、经费数量和人员数量关系", value="Project.XdRelation")
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
