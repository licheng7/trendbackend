package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.response.contrast.ContrastPaperByFieldResponse;
import cn.arp.trend.service.biz.ContrastPaperService;
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
 * Date:2020/10/30
 * Time:21:38
 **/
@Api(value="contrastPaper",tags={"对应contrast/Paper.js"})
@RestController
@RequestMapping(value = "/contrast/paper")
public class ContrastPaperController extends BaseController {

    @Resource
    private ContrastPaperService contrastPaperService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="")
    public ContrastPaperByFieldResponse contrastByField(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 0;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList = contrastPaperService.byField(
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
            Long lws = Long.parseLong(""+item.get("lws"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStruct.containsKey(fieldName))
            {
                helpStruct.put(fieldName, new long[endYear - startYear + 1]);
            }
            helpStruct.get(fieldName)[nf - startYear] = lws;
        }

        // build response
        ContrastPaperByFieldResponse contrastPaperByFieldResponse = new ContrastPaperByFieldResponse();

        // build ageAry
        List<Map<String, Object>> ageAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStruct.get(fieldName)[j - startYear]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        contrastPaperByFieldResponse.setAgeAry(ageAry);

        // build unitAry
        List<Map<String, Object>> unitAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageunitAry = new HashMap<String, Object>();
            ageunitAry.put("name", fieldName);

            int allSum = 0;
            for(int j = startYear ; j <= endYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startYear];
            }
            ageunitAry.put("value", allSum);
            unitAry.add(ageunitAry);
        }
        contrastPaperByFieldResponse.setUnitAry(unitAry);

        // build yearsAry
        List<Long> yearsAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        contrastPaperByFieldResponse.setyearsAry(yearsAry);

        return contrastPaperByFieldResponse;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="")
    public ContrastPaperByFieldResponse contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 0;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList = contrastPaperService.byUnit(
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
            Long lws = Long.parseLong(""+item.get("lws"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStruct.containsKey(fieldName))
            {
                helpStruct.put(fieldName, new long[endYear - startYear + 1]);
            }
            helpStruct.get(fieldName)[nf - startYear] = lws;
        }

        // build response
        ContrastPaperByFieldResponse contrastPaperByFieldResponse = new ContrastPaperByFieldResponse();

        // build ageAry
        List<Map<String, Object>> ageAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStruct.get(fieldName)[j - startYear]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        contrastPaperByFieldResponse.setAgeAry(ageAry);

        // build unitAry
        List<Map<String, Object>> unitAry = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> ageunitAry = new HashMap<String, Object>();
            ageunitAry.put("name", fieldName);

            int allSum = 0;
            for(int j = startYear ; j <= endYear ; j++)
            {
                allSum += helpStruct.get(fieldName)[j - startYear];
            }
            ageunitAry.put("value", allSum);
            unitAry.add(ageunitAry);
        }
        contrastPaperByFieldResponse.setUnitAry(unitAry);

        // build yearsAry
        List<Long> yearsAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        contrastPaperByFieldResponse.setyearsAry(yearsAry);

        return contrastPaperByFieldResponse;
    }
}
