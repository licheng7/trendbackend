package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.response.contrast.ContrastAcademicianByFieldResponse;
import cn.arp.trend.data.model.response.contrast.ContrastAwardByFieldResponse;
import cn.arp.trend.service.biz.ContrastAcademicianService;
import cn.arp.trend.service.biz.ContrastAwardService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IDEA
 * author:david
 * Date:2020/11/2
 * Time:21:38
 **/
@Api(value="contrastAward",tags={"对应contrast/Award.js"})
@RestController
@RequestMapping(value = "/contrast/award")
public class ContrastAwardController extends BaseController {

    @Resource
    private ContrastAwardService contrastAwardService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="近十年各领域获奖数量趋势对比、近十年各领域获奖总数对比、近十年各领域获奖详情", value="FieldContrast.Award")
    public List<Object> contrastByField(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastAwardService.byField1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastAwardService.byField2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList3 = contrastAwardService.byField3(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<Long> resList4 = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            resList4.add((long) j);
        }

        if(resList1 == null || resList2 == null || resList3 == null || resList4 == null)
        {
            return null;
        }

        ContrastAwardByFieldResponse contrastAwardByFieldResponse = new ContrastAwardByFieldResponse();
        List<Object> res = new ArrayList<Object>();
        res.add(resList1);
        res.add(resList2);
        res.add(resList3);
        res.add(resList4);
        contrastAwardByFieldResponse.setRes(res);

        // keep format as nodejs server
        return res;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="近十年各机构获奖数量趋势对比、近十年各机构获奖总数对比、近十年各机构获奖详情", value="InstitutionContrast.Award")
    public List<Object> contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastAwardService.byUnit1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastAwardService.byUnit2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList3 = contrastAwardService.byUnit3(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<Long> resList4 = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            resList4.add((long) j);
        }

        if(resList1 == null || resList2 == null || resList3 == null || resList4 == null)
        {
            return null;
        }

        ContrastAwardByFieldResponse contrastAwardByFieldResponse = new ContrastAwardByFieldResponse();
        List<Object> res = new ArrayList<Object>();
        res.add(resList1);
        res.add(resList2);
        res.add(resList3);
        res.add(resList4);
        contrastAwardByFieldResponse.setRes(res);

        // keep format as nodejs server
        return res;
    }
}
