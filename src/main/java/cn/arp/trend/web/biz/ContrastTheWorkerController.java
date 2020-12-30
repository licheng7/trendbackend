package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.response.contrast.ContrastTheWorkerByFieldResponse;
import cn.arp.trend.service.biz.ContrastTheWorkerService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.ObjectError;
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
@Api(value="contrastTheWorker",tags={"对应contrast/TheWorker.js"})
@RestController
@RequestMapping(value = "/contrast/theworker")
@RequirePermission(dataset=true)
public class ContrastTheWorkerController extends BaseController {

    @Resource
    private ContrastTheWorkerService contrastTheWorkerService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="近十年各领域在职职工总数对比，年龄分布对比，专业技术人员职称对比，学历对比", value="FieldContrast.staff")
    public ContrastTheWorkerByFieldResponse contrastByField(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastTheWorkerService.byField1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastTheWorkerService.byField2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList3 = contrastTheWorkerService.byField3(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList4 = contrastTheWorkerService.byField4(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        ArrayList<Object> yearList = new ArrayList<Object>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearList.add((long) j);
        }

        if(resList1 == null || resList2 == null || resList3 == null || resList4 == null)
        {
            return null;
        }

        // build contrastTheWorkerByFieldResponse
        ContrastTheWorkerByFieldResponse contrastTheWorkerByFieldResponse = new ContrastTheWorkerByFieldResponse();

        // build age
        // build age helper
        HashMap<String, Integer> nldIndexMap = new HashMap<String, Integer>();
        ArrayList<Object> nldIndexList = new ArrayList<Object>();
        Integer nldIndex = 0;
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String nld = ""+item.get("nld");
            if(!nldIndexMap.containsKey(nld))
            {
                nldIndexMap.put(nld, nldIndex);
                nldIndexList.add(nld);
                nldIndex++;
            }
        }

        Map<String, long[]> helpStructNld = new HashMap<>();
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String fieldName = (String) item.get("research_field");
            String nld = ""+item.get("nld");
            Integer rs = Integer.parseInt(""+item.get("rs"));

            if(!helpStructNld.containsKey(fieldName))
            {
                helpStructNld.put(fieldName, new long[nldIndex + 1]);
            }
            helpStructNld.get(fieldName)[nldIndexMap.get(nld)] = rs;
        }
        // build age helper(end)
        HashMap<String, ArrayList<Object>> age = new HashMap<String, ArrayList<Object>>();

        ArrayList<Object> ageAry = new ArrayList<Object>();
        for(String fieldName : helpStructNld.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int ii = 0; ii < nldIndexList.size() ; ii ++)
            {
                String nld = (String)nldIndexList.get(ii);
                arrayList.add(helpStructNld.get(fieldName)[nldIndexMap.get(nld)]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        age.put("ageAxisAry", nldIndexList);
        age.put("ageAry", ageAry);
        contrastTheWorkerByFieldResponse.setAge(age);
        // build age(end)

        // build tendency

        Map<String, long[]> helpStructTendency = new HashMap<>();
        for(int i = 0;i<resList2.size();i++)
        {
            HashMap<String, Object> item = resList2.get(i);
            String fieldName = (String) item.get("research_field");
            Long zs = Long.parseLong("" + item.get("zs"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStructTendency.containsKey(fieldName))
            {
                helpStructTendency.put(fieldName, new long[endYear - startYear + 1]);
            }
            helpStructTendency.get(fieldName)[nf - startYear] = zs;
        }

        HashMap<String, ArrayList<Object>> tendency = new HashMap<String, ArrayList<Object>>();

        ArrayList<Object> tendencyAry = new ArrayList<Object>();
        for(String fieldName : helpStructTendency.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStructTendency.get(fieldName)[j - startYear]);
            }
            ageAryOne.put("value", arrayList);
            tendencyAry.add(ageAryOne);
        }
        tendency.put("tendencyAry", tendencyAry);
        tendency.put("yearsAry", yearList);
        contrastTheWorkerByFieldResponse.setTendency(tendency);
        // build tendency(end)

        // build title
        contrastTheWorkerByFieldResponse.setTitle((ArrayList<HashMap<String, Object>>) resList3);
        // build title(end)

        ArrayList<HashMap<String, Object>> educationAry = new ArrayList<HashMap<String, Object>>();

        HashMap<String, HashMap<String, Object>> helperEducation = new HashMap<String, HashMap<String, Object>>();
        for(int i = 0;i<resList4.size();i++)
        {
            HashMap<String, Object> item = resList4.get(i);

            String fieldName = (String) item.get("research_field");
            Long rs = Long.parseLong("" + item.get("rs"));
            String xl = "" + item.get("xl");

            if(!helperEducation.containsKey(fieldName))
            {
                helperEducation.put(fieldName, new HashMap<String ,Object>());
            }
            helperEducation.get(fieldName).put(xl,rs);
        }
        for(String fieldName : helperEducation.keySet())
        {
            HashMap<String, Object> educationAryOne = new HashMap<String, Object>();
            educationAryOne.put("name", fieldName);

            HashMap<String, Object> item = helperEducation.get(fieldName);
            ArrayList<HashMap<String, Object>> oneValue = new ArrayList<HashMap<String, Object>>();

            for(String oneKey:item.keySet())
            {
                HashMap<String, Object> oneMap = new HashMap<String, Object>();
                oneMap.put("name", oneKey);
                oneMap.put("value", Integer.parseInt("" + item.get(oneKey)));
                oneValue.add(oneMap);
            }
            educationAryOne.put("value", oneValue);

            educationAry.add(educationAryOne);
        }
        contrastTheWorkerByFieldResponse.setEducationAry(educationAry);

        // keep format as nodejs server
        return contrastTheWorkerByFieldResponse;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="近十年各机构在职职工总数对比，年龄分布对比，专业技术人员职称对比，学历对比", value="InstitutionContrast.staff")
    public ContrastTheWorkerByFieldResponse contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastTheWorkerService.byUnit1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastTheWorkerService.byUnit2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList3 = contrastTheWorkerService.byUnit3(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList4 = contrastTheWorkerService.byUnit4(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        ArrayList<Object> yearList = new ArrayList<Object>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearList.add((long) j);
        }

        if(resList1 == null || resList2 == null || resList3 == null || resList4 == null)
        {
            return null;
        }

        // build contrastTheWorkerByFieldResponse
        ContrastTheWorkerByFieldResponse contrastTheWorkerByFieldResponse = new ContrastTheWorkerByFieldResponse();

        // build age
        // build age helper
        HashMap<String, Integer> nldIndexMap = new HashMap<String, Integer>();
        ArrayList<Object> nldIndexList = new ArrayList<Object>();
        Integer nldIndex = 0;
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String nld = ""+item.get("nld");
            if(!nldIndexMap.containsKey(nld))
            {
                nldIndexMap.put(nld, nldIndex);
                nldIndexList.add(nld);
                nldIndex++;
            }
        }

        Map<String, long[]> helpStructNld = new HashMap<>();
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String fieldName = (String) item.get("jgmc");
            String nld = ""+item.get("nld");
            Integer rs = Integer.parseInt(""+item.get("rs"));

            if(!helpStructNld.containsKey(fieldName))
            {
                helpStructNld.put(fieldName, new long[nldIndex + 1]);
            }
            helpStructNld.get(fieldName)[nldIndexMap.get(nld)] = rs;
        }
        // build age helper(end)
        HashMap<String, ArrayList<Object>> age = new HashMap<String, ArrayList<Object>>();

        ArrayList<Object> ageAry = new ArrayList<Object>();
        for(String fieldName : helpStructNld.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int ii = 0; ii < nldIndexList.size() ; ii ++)
            {
                String nld = (String)nldIndexList.get(ii);
                arrayList.add(helpStructNld.get(fieldName)[nldIndexMap.get(nld)]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        age.put("ageAxisAry", nldIndexList);
        age.put("ageAry", ageAry);
        contrastTheWorkerByFieldResponse.setAge(age);
        // build age(end)

        // build tendency
        Map<String, long[]> helpStructTendency = new HashMap<>();
        for(int i = 0;i<resList2.size();i++)
        {
            HashMap<String, Object> item = resList2.get(i);
            String fieldName = (String) item.get("jgmc");
            Long zs = Long.parseLong("" + item.get("zs"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStructTendency.containsKey(fieldName))
            {
                helpStructTendency.put(fieldName, new long[endYear - startYear + 1]);
            }
            helpStructTendency.get(fieldName)[nf - startYear] = zs;
        }

        HashMap<String, ArrayList<Object>> tendency = new HashMap<String, ArrayList<Object>>();

        ArrayList<Object> tendencyAry = new ArrayList<Object>();
        for(String fieldName : helpStructTendency.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStructTendency.get(fieldName)[j - startYear]);
            }
            ageAryOne.put("value", arrayList);
            tendencyAry.add(ageAryOne);
        }
        tendency.put("tendencyAry", tendencyAry);
        tendency.put("yearsAry", yearList);
        contrastTheWorkerByFieldResponse.setTendency(tendency);
        // build tendency(end)

        // build title
        contrastTheWorkerByFieldResponse.setTitle((ArrayList<HashMap<String, Object>>) resList3);
        // build title(end)

        ArrayList<HashMap<String, Object>> educationAry = new ArrayList<HashMap<String, Object>>();

        HashMap<String, HashMap<String, Object>> helperEducation = new HashMap<String, HashMap<String, Object>>();
        for(int i = 0;i<resList4.size();i++)
        {
            HashMap<String, Object> item = resList4.get(i);

            String fieldName = (String) item.get("jgmc");
            Long rs = Long.parseLong("" + item.get("rs"));
            String xl = "" + item.get("xl");

            if(!helperEducation.containsKey(fieldName))
            {
                helperEducation.put(fieldName, new HashMap<String ,Object>());
            }
            helperEducation.get(fieldName).put(xl,rs);
        }
        for(String fieldName : helperEducation.keySet())
        {
            HashMap<String, Object> educationAryOne = new HashMap<String, Object>();
            educationAryOne.put("name", fieldName);

            HashMap<String, Object> item = helperEducation.get(fieldName);
            ArrayList<HashMap<String, Object>> oneValue = new ArrayList<HashMap<String, Object>>();

            for(String oneKey:item.keySet())
            {
                HashMap<String, Object> oneMap = new HashMap<String, Object>();
                oneMap.put("name", oneKey);
                oneMap.put("value", Integer.parseInt("" + item.get(oneKey)));
                oneValue.add(oneMap);
            }
            educationAryOne.put("value", oneValue);

            educationAry.add(educationAryOne);
        }
        contrastTheWorkerByFieldResponse.setEducationAry(educationAry);

        // keep format as nodejs server
        return contrastTheWorkerByFieldResponse;
    }
}
