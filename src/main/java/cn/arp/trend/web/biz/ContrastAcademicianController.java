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
import java.util.*;

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

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        Integer startNf = 1980;

        List<HashMap<String, Object>> resList = contrastAcademicianService.byField(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());
        if(resList == null)
        {
            return null;
        }

        // build help data structure
        // research_field, nf, num
        Map<String, long[]> helpStruct = new HashMap<>();
        for(int i = 0;i<resList.size();i++)
        {
            HashMap<String, Object> item = resList.get(i);
            String fieldName = (String) item.get("research_field");
            Long num = (Long) item.get("num");
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStruct.containsKey(fieldName))
            {
                helpStruct.put(fieldName, new long[endYear - startNf + 1]);
            }
            helpStruct.get(fieldName)[nf - startNf] = num;
        }

        // build response
        ContrastAcademicianByFieldResponse contrastAcademicianByFieldResponse = new ContrastAcademicianByFieldResponse();

        // build ageAry
        List<Map<String, Object>> ageAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStruct.get(fieldName)[j - startNf]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        contrastAcademicianByFieldResponse.setAgeAry(ageAry);

        // build unitAry
        List<Map<String, Object>> unitAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageunitAry = new HashMap<String, Object>();
            ageunitAry.put("name", fieldName);

            int allSum = 0;
            for(int j = startNf ; j <= endYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startNf];
            }
            ageunitAry.put("value", allSum);
            unitAry.add(ageunitAry);
        }
        contrastAcademicianByFieldResponse.setUnitAry(unitAry);

        // build yearsAry
        List<Long> yearsAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        contrastAcademicianByFieldResponse.setyearsAry(yearsAry);

        // build cumulativeAry
        List<Map<String, Object>> cumulativeAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> cumulativeAryOne = new HashMap<String, Object>();
            cumulativeAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            int allSum = 0;
            for(int j = startNf ; j < startYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startNf];
            }
            for(int j = startYear ; j <= endYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startNf];
                arrayList.add((long) allSum);
            }
            cumulativeAryOne.put("value", arrayList);
            cumulativeAry.add(cumulativeAryOne);
        }
        contrastAcademicianByFieldResponse.setCumulativeAry(cumulativeAry);

        return contrastAcademicianByFieldResponse;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="")
    public ContrastAcademicianByFieldResponse contrastByUnit(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        Integer startNf = 1980;

        List<HashMap<String, Object>> resList = contrastAcademicianService.byUnit(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());
        if(resList == null)
        {
            return null;
        }

        // build help data structure
        // research_field, nf, num
        Map<String, long[]> helpStruct = new HashMap<>();
        for(int i = 0;i<resList.size();i++)
        {
            HashMap<String, Object> item = resList.get(i);
            String fieldName = (String) item.get("jgmc");
            Long num = (Long) item.get("num");
            Integer nf = Integer.parseInt(""+item.get("dxnf"));

            if(!helpStruct.containsKey(fieldName))
            {
                helpStruct.put(fieldName, new long[endYear - startNf + 1]);
            }
            helpStruct.get(fieldName)[nf - startNf] = num;
        }

        // build response
        ContrastAcademicianByFieldResponse contrastAcademicianByUnitResponse = new ContrastAcademicianByFieldResponse();

        // build ageAry
        List<Map<String, Object>> ageAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStruct.get(fieldName)[j - startNf]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        contrastAcademicianByUnitResponse.setAgeAry(ageAry);

        // build unitAry
        List<Map<String, Object>> unitAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageunitAry = new HashMap<String, Object>();
            ageunitAry.put("name", fieldName);

            int allSum = 0;
            for(int j = startNf ; j <= endYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startNf];
            }
            ageunitAry.put("value", allSum);
            unitAry.add(ageunitAry);
        }
        contrastAcademicianByUnitResponse.setUnitAry(unitAry);

        // build yearsAry
        List<Long> yearsAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        contrastAcademicianByUnitResponse.setyearsAry(yearsAry);

        // build cumulativeAry
        List<Map<String, Object>> cumulativeAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> cumulativeAryOne = new HashMap<String, Object>();
            cumulativeAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            int allSum = 0;
            for(int j = startNf ; j < startYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startNf];
            }
            for(int j = startYear ; j <= endYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startNf];
                arrayList.add((long) allSum);
            }
            cumulativeAryOne.put("value", arrayList);
            cumulativeAry.add(cumulativeAryOne);
        }
        contrastAcademicianByUnitResponse.setCumulativeAry(cumulativeAry);

        return contrastAcademicianByUnitResponse;
    }
}
