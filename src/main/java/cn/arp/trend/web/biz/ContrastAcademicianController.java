package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.DACompareQueryDO;
import cn.arp.trend.data.model.DTO.ForeignInfoDTO;
import cn.arp.trend.data.model.request.CompareRequest;
import cn.arp.trend.data.model.request.ForeignRequest;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.response.CompareResponse;
import cn.arp.trend.data.model.response.ForeignResponse;
import cn.arp.trend.data.model.response.contrast.ContrastAcademicianByFieldResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.ContrastAcademicianService;
import cn.arp.trend.service.biz.DetailAcademicianService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:david
 * Date:2020/10/30
 * Time:21:38
 **/
@Api(value="contrastAcademician",tags={"对应contrast/Academician.js"})
@RestController
@RequestMapping(value = "/contrast/academician")
public class ContrastAcademicianController extends BaseController {

    @Resource
    private ContrastAcademicianService contrastAcademicianService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="")
    public ContrastAcademicianByFieldResponse contrastByField(@RequestBody ContrastBaseRequest request) {
        Integer startYear = 1990;
        Integer endYear = 2020;
        List<HashMap<String, Object>> resList = contrastAcademicianService.byField(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());
        ContrastAcademicianByFieldResponse contrastAcademicianByFieldResponse = new ContrastAcademicianByFieldResponse();

        List<Map<String, Object>> ageAry = new ArrayList<>();
        HashMap<String, Object> ageAry1 = new HashMap<String, Object>();
        ageAry1.put("ageAry1", "ageAry1Value");
        ArrayList<Long> arrayList = new ArrayList<Long>();
        arrayList.add(1L);
        arrayList.add(2L);
        arrayList.add(3L);
        ageAry1.put("ageArylist", arrayList);
        ageAry.add(ageAry1);
        contrastAcademicianByFieldResponse.setAgeAry(ageAry);

        List<Map<String, Object>> unitAry;
        List<Map<String, Object>> cumulativeAry;

        List<Long> yearsAry = new ArrayList<>();
        yearsAry.add(2001L);
        yearsAry.add(2021L);
        contrastAcademicianByFieldResponse.setyearsAry(yearsAry);

        return contrastAcademicianByFieldResponse;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="")
    public ForeignResponse contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Integer startYear = 1990;
        Integer endYear = 2020;
        Object foreignInfo = contrastAcademicianService.byUnit(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());
        return null;
    }


}
