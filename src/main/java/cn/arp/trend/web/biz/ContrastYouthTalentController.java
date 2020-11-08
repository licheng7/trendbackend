package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.service.biz.ContrastYouthTalentService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import com.sun.corba.se.spi.ior.ObjectKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created with IDEA
 * author:david
 * Date:2020/11/2
 * Time:21:38
 **/
@Api(value="contrastYouthTalent",tags={"对应contrast/YouthTalent.js"})
@RestController
@RequestMapping(value = "/contrast/youthtalent")
public class ContrastYouthTalentController extends BaseController {

    @Resource
    private ContrastYouthTalentService contrastYouthTalentService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="")
    public HashMap<String, Object> contrastByField(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastYouthTalentService.byField1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastYouthTalentService.byField2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList3 = contrastYouthTalentService.byField3(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList4 = contrastYouthTalentService.byField4(
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

        HashMap<String, Object> result = new HashMap<String, Object>();

        // project
        result.put("project", resList1);

        // ageAry
        Map<String, long[][]> helpStructAgeAry = new HashMap<>();
        for(int i = 0;i<resList2.size();i++)
        {
            HashMap<String, Object> item = resList2.get(i);
            String fieldName = (String) item.get("research_field");
            Long rcrs = Long.parseLong("" + item.get("rcrs"));
            Long zgrs = Long.parseLong("" + item.get("zgrs"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStructAgeAry.containsKey(fieldName))
            {
                helpStructAgeAry.put(fieldName, new long[endYear - startYear + 1][2]);
            }
            helpStructAgeAry.get(fieldName)[nf - startYear][0] = rcrs;
            helpStructAgeAry.get(fieldName)[nf - startYear][1] = zgrs;
        }

        ArrayList<Object> ageAry = new ArrayList<Object>();
        for(String fieldName : helpStructAgeAry.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStructAgeAry.get(fieldName)[j - startYear][0]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        result.put("ageAry", ageAry);

        // proportionAry,rcrs/zgrs
        ArrayList<Object> proportionAryAry = new ArrayList<Object>();
        for(String fieldName : helpStructAgeAry.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Double> arrayList = new ArrayList<Double>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                double ration = (0.0+helpStructAgeAry.get(fieldName)[j - startYear][0])/(0.0+helpStructAgeAry.get(fieldName)[j - startYear][1]);
                BigDecimal b = new BigDecimal(ration);
                double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                arrayList.add(f1);
            }
            ageAryOne.put("value", arrayList);
            proportionAryAry.add(ageAryOne);
        }
        result.put("proportionAry", proportionAryAry);

        // yearsAry
        result.put("yearsAry", yearList);

        // title
        HashMap<String, HashMap<String, Object>> titleHelper = new HashMap<String, HashMap<String, Object>>();
        for(int i = 0;i<resList3.size();i++)
        {
            HashMap<String, Object> item = resList3.get(i);

            String fieldName = (String) item.get("research_field");
            Long rs = Long.parseLong("" + item.get("rs"));
            String zwmc = "" + item.get("zwmc");

            if(!titleHelper.containsKey(fieldName))
            {
                titleHelper.put(fieldName, new HashMap<String, Object>());
            }
            titleHelper.get(fieldName).put(zwmc, rs);
        }
        ArrayList<Object> titleList = new ArrayList<Object>();
        for(String field:titleHelper.keySet())
        {
            HashMap<String, Object> one = new HashMap<String, Object>();
            one.put("jgmc", field);
            if(titleHelper.get(field).keySet().contains("正高级"))
            {
                one.put("zg", titleHelper.get(field).get("正高级"));
            }
            if(titleHelper.get(field).keySet().contains("副高级"))
            {
                one.put("fg", titleHelper.get(field).get("副高级"));
            }
            titleList.add(one);
        }
        result.put("title", titleList);

        // educationAry
        HashMap<String, HashMap<String, Object>> educationAryHelper = new HashMap<String, HashMap<String, Object>>();
        for(int i = 0;i<resList4.size();i++)
        {
            HashMap<String, Object> item = resList4.get(i);

            String fieldName = (String) item.get("research_field");
            Long rs = Long.parseLong("" + item.get("rs"));
            String xw = "" + item.get("xw");

            if(!educationAryHelper.containsKey(fieldName))
            {
                educationAryHelper.put(fieldName, new HashMap<String, Object>());
            }
            educationAryHelper.get(fieldName).put(xw, rs);
        }
        ArrayList<Object> educationAryList = new ArrayList<Object>();
        for(String field:educationAryHelper.keySet())
        {
            HashMap<String, Object> one = new HashMap<String, Object>();
            one.put("name", field);

            ArrayList<Object> valueList = new ArrayList<>();
            if(educationAryHelper.get(field).keySet().contains("博士"))
            {
                HashMap<String, Object> oneValue = new HashMap<String, Object>();
                oneValue.put("name", "博士");
                oneValue.put("value", educationAryHelper.get(field).get("博士"));
                valueList.add(oneValue);
            }
            if(educationAryHelper.get(field).keySet().contains("硕士"))
            {
                HashMap<String, Object> oneValue = new HashMap<String, Object>();
                oneValue.put("name", "硕士");
                oneValue.put("value", educationAryHelper.get(field).get("硕士"));
                valueList.add(oneValue);
            }
            one.put("value", valueList);
            educationAryList.add(one);
        }

        result.put("educationAry", educationAryList);

        // result_array
        ArrayList<Object> allResultList = new ArrayList<Object>();
        allResultList.add(resList1);
        allResultList.add(resList2);
        allResultList.add(resList3);
        allResultList.add(resList4);
        result.put("result_array", allResultList);

        // keep format as nodejs server
        return result;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="")
    public HashMap<String, Object> contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastYouthTalentService.byUnit1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastYouthTalentService.byUnit2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList3 = contrastYouthTalentService.byUnit3(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList4 = contrastYouthTalentService.byUnit4(
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

        HashMap<String, Object> result = new HashMap<String, Object>();

        // project
        result.put("project", resList1);

        // ageAry
        Map<String, long[][]> helpStructAgeAry = new HashMap<>();
        for(int i = 0;i<resList2.size();i++)
        {
            HashMap<String, Object> item = resList2.get(i);
            String fieldName = (String) item.get("jgmc");
            Long rcrs = Long.parseLong("" + item.get("rcrs"));
            Long zgrs = Long.parseLong("" + item.get("zgrs"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStructAgeAry.containsKey(fieldName))
            {
                helpStructAgeAry.put(fieldName, new long[endYear - startYear + 1][2]);
            }
            helpStructAgeAry.get(fieldName)[nf - startYear][0] = rcrs;
            helpStructAgeAry.get(fieldName)[nf - startYear][1] = zgrs;
        }

        ArrayList<Object> ageAry = new ArrayList<Object>();
        for(String fieldName : helpStructAgeAry.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Long> arrayList = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                arrayList.add(helpStructAgeAry.get(fieldName)[j - startYear][0]);
            }
            ageAryOne.put("value", arrayList);
            ageAry.add(ageAryOne);
        }
        result.put("ageAry", ageAry);

        // proportionAry,rcrs/zgrs
        ArrayList<Object> proportionAryAry = new ArrayList<Object>();
        for(String fieldName : helpStructAgeAry.keySet())
        {
            HashMap<String, Object> ageAryOne = new HashMap<String, Object>();
            ageAryOne.put("name", fieldName);

            ArrayList<Double> arrayList = new ArrayList<Double>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                double ration = (0.0+helpStructAgeAry.get(fieldName)[j - startYear][0])/(0.0+helpStructAgeAry.get(fieldName)[j - startYear][1]);
                BigDecimal b = new BigDecimal(ration);
                double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                arrayList.add(f1);
            }
            ageAryOne.put("value", arrayList);
            proportionAryAry.add(ageAryOne);
        }
        result.put("proportionAry", proportionAryAry);

        // yearsAry
        result.put("yearsAry", yearList);

        // title
        HashMap<String, HashMap<String, Object>> titleHelper = new HashMap<String, HashMap<String, Object>>();
        for(int i = 0;i<resList3.size();i++)
        {
            HashMap<String, Object> item = resList3.get(i);

            String fieldName = (String) item.get("jgmc");
            Long rs = Long.parseLong("" + item.get("rs"));
            String zwmc = "" + item.get("zwmc");

            if(!titleHelper.containsKey(fieldName))
            {
                titleHelper.put(fieldName, new HashMap<String, Object>());
            }
            titleHelper.get(fieldName).put(zwmc, rs);
        }
        ArrayList<Object> titleList = new ArrayList<Object>();
        for(String field:titleHelper.keySet())
        {
            HashMap<String, Object> one = new HashMap<String, Object>();
            one.put("jgmc", field);
            if(titleHelper.get(field).keySet().contains("正高级"))
            {
                one.put("zg", titleHelper.get(field).get("正高级"));
            }
            if(titleHelper.get(field).keySet().contains("副高级"))
            {
                one.put("fg", titleHelper.get(field).get("副高级"));
            }
            titleList.add(one);
        }
        result.put("title", titleList);

        // educationAry
        HashMap<String, HashMap<String, Object>> educationAryHelper = new HashMap<String, HashMap<String, Object>>();
        for(int i = 0;i<resList4.size();i++)
        {
            HashMap<String, Object> item = resList4.get(i);

            String fieldName = (String) item.get("jgmc");
            Long rs = Long.parseLong("" + item.get("rs"));
            String xw = "" + item.get("xw");

            if(!educationAryHelper.containsKey(fieldName))
            {
                educationAryHelper.put(fieldName, new HashMap<String, Object>());
            }
            educationAryHelper.get(fieldName).put(xw, rs);
        }
        ArrayList<Object> educationAryList = new ArrayList<Object>();
        for(String field:educationAryHelper.keySet())
        {
            HashMap<String, Object> one = new HashMap<String, Object>();
            one.put("name", field);

            ArrayList<Object> valueList = new ArrayList<>();
            if(educationAryHelper.get(field).keySet().contains("博士"))
            {
                HashMap<String, Object> oneValue = new HashMap<String, Object>();
                oneValue.put("name", "博士");
                oneValue.put("value", educationAryHelper.get(field).get("博士"));
                valueList.add(oneValue);
            }
            if(educationAryHelper.get(field).keySet().contains("硕士"))
            {
                HashMap<String, Object> oneValue = new HashMap<String, Object>();
                oneValue.put("name", "硕士");
                oneValue.put("value", educationAryHelper.get(field).get("硕士"));
                valueList.add(oneValue);
            }
            one.put("value", valueList);
            educationAryList.add(one);
        }

        result.put("educationAry", educationAryList);

        // result_array
        ArrayList<Object> allResultList = new ArrayList<Object>();
        allResultList.add(resList1);
        allResultList.add(resList2);
        allResultList.add(resList3);
        allResultList.add(resList4);
        result.put("result_array", allResultList);

        // keep format as nodejs server
        return result;
    }
}
