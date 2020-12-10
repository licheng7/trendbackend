package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.converter.*;
import cn.arp.trend.data.model.request.*;
import cn.arp.trend.data.model.response.*;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.CompareService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * 对应Node代码中compare.js
 *
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:31
 **/
@Api(value="compare",tags={"对应宏观部分compare.js"})
@RestController
@RequestMapping(value = "/compare")
@RequirePermission(dataset=true)
public class CompareController extends BaseController {

    @Resource
    private CompareService compareService;

    @ApiOperation(value= "科研投入-总经费", notes= "科研投入-总经费")
    @ServiceExecuter(description = "科研投入-总经费")
    @RequestMapping(value = "/funds", method = RequestMethod.POST)
    @Audit(desc="中科院和C9高校总经费对比", value="Comparison.Funds")
    public FundsResponse fundsQuery(@RequestBody @Validated FundsRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        FundsInfoDTO fundsInfo = compareService.fundsQuery(request.getStartYear(), request
                .getEndYear());
        return new FundsResponse(fundsInfo.getYear(), fundsInfo.getDetail(), fundsInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-总经费(新)", notes= "科研投入-总经费(新)")
    @ServiceExecuter(description = "科研投入-总经费(新)")
    @RequestMapping(value = "/newfunds", method = RequestMethod.POST)
    @Audit(desc="中科院和C9高校总经费对比(新)", value="Comparison.Funds")
    public FundsResponse newfundsQuery() throws RestError {
        Calendar cal = Calendar.getInstance();
        Integer ysYear = cal.get(Calendar.YEAR);
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = ysYear - 10;
        FundsInfoDTO fundsInfo = compareService.newfundsQuery(startYear.toString(), endYear.toString(), ysYear.toString());
        return new FundsResponse(fundsInfo.getYear(), fundsInfo.getDetail(), fundsInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-财政拨款", notes= "科研投入-财政拨款")
    @ServiceExecuter(description = "科研投入-财政拨款")
    @RequestMapping(value = "/finance", method = RequestMethod.POST)
    @Audit(desc="中科院和C9高校财政拨款对比", value="Comparison.Finance")
    public FinanceResponse financeQuery(@RequestBody @Validated FinanceRequest request,
                                        BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        FinanceInfoDTO financeInfo = compareService.financeQuery(request.getStartYear(), request
                .getEndYear());
        return new FinanceResponse(
                financeInfo.getYear(),
                financeInfo.getDetail(),
                financeInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-财政拨款(新)", notes= "科研投入-财政拨款(新)")
    @ServiceExecuter(description = "科研投入-财政拨款(新)")
    @RequestMapping(value = "/newfinance", method = RequestMethod.POST)
    @Audit(desc="中科院和C9高校财政拨款对比(新)", value="Comparison.Finance")
    public FinanceResponse newfinanceQuery() throws RestError {
        Calendar cal = Calendar.getInstance();
        Integer ysYear = cal.get(Calendar.YEAR);
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = ysYear - 10;
        FinanceInfoDTO financeInfo = compareService.newfinanceQuery(startYear.toString(), endYear.toString(), ysYear.toString());
        return new FinanceResponse(
                financeInfo.getYear(),
                financeInfo.getDetail(),
                financeInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-国家科研设施", notes= "科研投入-国家科研设施")
    @ServiceExecuter(description = "科研投入-国家科研设施")
    @RequestMapping(value = "/facility", method = RequestMethod.POST)
    @Audit(desc="中科院和C9高校国家科研设施对比", value="Comparison.Facility")
    public FacilityResponse facilityQuery() {
        FacilityInfoDTO facilityInfo = compareService.facilityQuery();
        MapResultConverter mapResultConverter = MapResultConverter.INSTANCE;
        return new FacilityResponse(facilityInfo.getYearList(),
                mapResultConverter.domain2dto(facilityInfo.getPlatformList()),
                mapResultConverter.domain2dto(facilityInfo.getKeylabList()),
                facilityInfo.getUpdateTimeBas(),
                facilityInfo.getUpdateTimeLab());
    }

    @ApiOperation(value= "科研产出-论文发表情况", notes= "科研产出-论文发表情况")
    @ServiceExecuter(description = "科研产出-论文发表情况")
    @RequestMapping(value = "/paper", method = RequestMethod.POST)
    @Audit(desc="中科院、C9高校等机构的论文对比", value="Comparison.Paper")
    public PaperResponse paperQuery(@RequestBody @Validated PaperRequest request, BindingResult
            bindingResult) throws RestError{
        validData(bindingResult);
        PaperInfoDTO paperInfo = compareService.paperQuery(request.getStartYear(), request
                .getEndYear());
        MapResultConverter mapResultConverter = MapResultConverter.INSTANCE;
        return new PaperResponse(paperInfo.getYear(),
                mapResultConverter.domain2dto(paperInfo.getDetail()),
                paperInfo.getPaperUpdateTimeLw(),
                paperInfo.getPaperUpdateTimeGby());
    }

    @ApiOperation(value= "科研投入-项目", notes= "科研投入-项目")
    @ServiceExecuter(description = "科研投入-项目")
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @Audit(desc="中科院各研究所项目简况", value="Comparison.Project")
    public ProjectResponse projectQuery(@RequestBody @Validated ProjectQueryRequest request,
                                        BindingResult
            bindingResult) throws Exception {
        validData(bindingResult);
        ProjectQueryDO projectQuery = ProjectQueryConverter.INSTANCE.domain2dto(request);
        ProjectInfoDTO projectInfo = compareService.projectQuery(projectQuery);
        ProjectResponse response = ProjectInfoConverter.INSTANCE.domain2dto(projectInfo);
        List<ProjectOrderResult> order = Lists.newArrayList();
        for(ProjectInfoDTO.OrderDTO orderDTO : projectInfo.getOrder()) {
            order.add(new ProjectOrderResult(
                    MapResultConverter.INSTANCE.domain2dto(orderDTO.getNsfc()),
                    MapResultConverter.INSTANCE.domain2dto(orderDTO.getStd()),
                    MapResultConverter.INSTANCE.domain2dto(orderDTO.getXd())
            ));
        }
        response.setOrder(order);
        return response;
    }

    @ApiOperation(value= "科研产出-论文 高被引科学家", notes= "科研产出-论文 高被引科学家")
    @ServiceExecuter(description = "科研产出-论文 高被引科学家")
    @RequestMapping(value = "/scientist", method = RequestMethod.POST)
    @Audit(desc="中科院、C9高校等机构高被引科学家对比", value="Comparison.HCAuthors")
    public ScientistResponse scientistQuery(@RequestBody @Validated ScientistRequest request,
                                            BindingResult
            bindingResult) throws RestError{
        validData(bindingResult);
        ScientistInfoDTO scientistInfo = compareService.scientistQuery(request.getStartYear(),
                request.getEndYear());
        ScientistResponse response = new ScientistResponse();
        response.setYear(scientistInfo.getYear());
        List<MapResult<String, List<Integer>>> domestic = Lists.newArrayList();
        for(MapResultDTO<String, List<Integer>> obj : scientistInfo.getDomestic()) {
            domestic.add(MapResultConverter.INSTANCE.domain2dto(obj));
        }
        response.setDomestic(domestic);
        List<MapResult<String, List<Integer>>> newWorldlist = Lists.newArrayList();
        for(MapResultDTO<String, List<Integer>> obj : scientistInfo.getNewWorldlist()) {
            newWorldlist.add(MapResultConverter.INSTANCE.domain2dto(obj));
        }
        response.setNewWorldlist(newWorldlist);
        return response;
    }

    @ApiOperation(value= "科研发展-科研影响-评选", notes= "科研发展-科研影响-评选")
    @ServiceExecuter(description = "科研发展-科研影响-评选")
    @RequestMapping(value = "/development", method = RequestMethod.POST)
    @Audit(desc="中科院、C9高校等机构评选对比", value="Comparison.Influence.Appraisal")
    public DevelopmentResponse developmentQuery() {
        DevelopmentInfoDTO developmentInfo = compareService.developmentQuery();
        DevelopmentResponse response = DevelopmentInfoConverter.INSTANCE.domain2dto
                (developmentInfo);
        response.setNewkj(MapResultConverter.INSTANCE.domain2dto(developmentInfo.getNewkj()));
        response.setNewkx(MapResultConverter.INSTANCE.domain2dto(developmentInfo.getNewkx()));
        return response;
    }

    @ApiOperation(value= "科研发展-科研影响-国家奖", notes= "科研发展-科研影响-国家奖")
    @ServiceExecuter(description = "科研发展-科研影响-国家奖")
    @RequestMapping(value = "/nationalAward", method = RequestMethod.POST)
    @Audit(desc="中科院和C9高校国家奖对比", value="Comparison.Influence.NationalAward")
    public NationalAwardResponse nationalAwardQuery() {
        NationalAwardInfoDTO nationalAwardInfoDTO = compareService.nationalAwardQuery();
        return NationalAwardInfoConverter.INSTANCE.domain2dto(nationalAwardInfoDTO);
    }

    /*@ApiOperation(value= "获取中科院、C9高校等机构的获奖数据", notes= "获取中科院、C9高校等机构的获奖数据")
    @ServiceExecuter(description = "获取中科院、C9高校等机构的获奖数据")
    @RequestMapping(value = "/award", method = RequestMethod.POST)
    @Audit(desc="获取中科院、C9高校等机构的获奖数据", value="Comparison.Influence.CompareAward")
    public CompareAwardResponse awardQuery() {
        CompareAwardInfoDTO compareAwardInfoDTO = compareService.awardQuery();
        return new CompareAwardResponse(
                compareAwardInfoDTO.getAllOrgNameList(),
                compareAwardInfoDTO.getZrkxList(),
                compareAwardInfoDTO.getJsfmList(),
                compareAwardInfoDTO.getJsjbList(),
                compareAwardInfoDTO.getQsjcList(),
                compareAwardInfoDTO.getHlhlList());
    }*/
}
