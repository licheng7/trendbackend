package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.response.contrast.ContrastProjectByFieldResponse;
import cn.arp.trend.service.biz.ContrastProjectService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IDEA
 * author:david
 * Date:2020/11/2
 * Time:21:38
 **/
@Api(value="contrastProject",tags={"对应contrast/Project.js"})
@RestController
@RequestMapping(value = "/contrast/project")
@RequirePermission(dataset=true)
public class ContrastProjectController extends BaseController {

    @Resource
    private ContrastProjectService contrastProjectService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="近十年各领域自然科学基金项目数量、经费数量趋势对比，近十年各领域自然科学基金项目总数、经费总数对比", value="FieldContrast.Project")
    public ContrastProjectByFieldResponse contrastByField(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastProjectService.byField(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<Integer> resList4 = new ArrayList<Integer>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            resList4.add(j);
        }

        if(resList1 == null || resList4 == null)
        {
            return null;
        }

        ContrastProjectByFieldResponse contrastProjectByFieldResponse = new ContrastProjectByFieldResponse();
        contrastProjectByFieldResponse.setProjectData(resList1);
        contrastProjectByFieldResponse.setYearsAry(resList4);
        return contrastProjectByFieldResponse;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="近十年各机构自然科学基金项目数量、经费数量趋势对比，近十年各机构自然科学基金项目总数、经费总数对比", value="InstitutionContrast.Project")
    public ContrastProjectByFieldResponse contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastProjectService.byUnit(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<Integer> resList4 = new ArrayList<Integer>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            resList4.add(j);
        }

        if(resList1 == null || resList4 == null)
        {
            return null;
        }

        ContrastProjectByFieldResponse contrastProjectByFieldResponse = new ContrastProjectByFieldResponse();
        contrastProjectByFieldResponse.setProjectData(resList1);
        contrastProjectByFieldResponse.setYearsAry(resList4);
        return contrastProjectByFieldResponse;
    }
}
