package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.ProjectQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.converter.DevelopmentInfoConverter;
import cn.arp.trend.data.model.converter.MapResultConverter;
import cn.arp.trend.data.model.converter.ProjectInfoConverter;
import cn.arp.trend.data.model.converter.ProjectQueryConverter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
public class CompareController extends BaseController {

    @Resource
    private CompareService compareService;

    @ApiOperation(value= "科研投入-总经费", notes= "科研投入-总经费")
    @ServiceExecuter(description = "科研投入-总经费")
    @RequestMapping(value = "/funds", method = RequestMethod.POST)
    @Audit(desc="科研投入-总经费")
    public FundsResponse fundsQuery(@Validated FundsRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        FundsInfoDTO fundsInfo = compareService.fundsQuery(request.getStartYear(), request
                .getEndYear());
        return new FundsResponse(fundsInfo.getYear(), fundsInfo.getDetail(), fundsInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-财政拨款", notes= "科研投入-财政拨款")
    @ServiceExecuter(description = "科研投入-财政拨款")
    @RequestMapping(value = "/finance", method = RequestMethod.POST)
    @Audit(desc="科研投入-财政拨款")
    public FinanceResponse financeQuery(@Validated FinanceRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        FinanceInfoDTO financeInfo = compareService.financeQuery(request.getStartYear(), request
                .getEndYear());
        return new FinanceResponse(financeInfo.getYear(), financeInfo.getDetail(), financeInfo.getUpdateTime());
    }

    @ApiOperation(value= "科研投入-国家科研设施", notes= "科研投入-国家科研设施")
    @ServiceExecuter(description = "科研投入-国家科研设施")
    @RequestMapping(value = "/facility", method = RequestMethod.POST)
    @Audit(desc="科研投入-国家科研设施")
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
    @Audit(desc="科研产出-论文发表情况")
    public PaperResponse paperQuery(@Validated PaperRequest request, BindingResult
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
    @Audit(desc="科研投入-项目")
    public ProjectResponse projectQuery(@Validated ProjectQueryRequest request, BindingResult
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
    @Audit(desc="科研产出-论文 高被引科学家")
    public ScientistResponse scientistQuery(@Validated ScientistRequest request, BindingResult
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
    @Audit(desc="科研发展-科研影响-评选")
    public DevelopmentResponse developmentQuery() {
        DevelopmentInfoDTO developmentInfo = compareService.developmentQuery();
        DevelopmentResponse response = DevelopmentInfoConverter.INSTANCE.domain2dto
                (developmentInfo);
        response.setNewkj(MapResultConverter.INSTANCE.domain2dto(developmentInfo.getNewkj()));
        response.setNewkx(MapResultConverter.INSTANCE.domain2dto(developmentInfo.getNewkx()));
        return response;
    }
}
