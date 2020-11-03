package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.response.contrast.ContrastEducationByFieldResponse;
import cn.arp.trend.service.biz.ContrastEducationService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IDEA
 * author:david
 * Date:2020/10/30
 * Time:21:38
 **/
@Api(value="contrastEducation",tags={"对应contrast/Education.js"})
@RestController
@RequestMapping(value = "/contrast/education")
public class ContrastEducationController extends BaseController {

    @Resource
    private ContrastEducationService contrastEducationService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="")
    public ContrastEducationByFieldResponse contrastByField(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastEducationService.byField1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastEducationService.byField2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        if(resList1 == null || resList2 == null)
        {
            return null;
        }

        // build help data structure
        Map<String, Double[][]> helpStruct = new HashMap<>();
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String fieldName = (String) item.get("research_field");

            Double ssrs = Double.parseDouble(""+item.get("ssrs"));
            Double sdrs = Double.parseDouble(""+item.get("sdrs"));
            Double bsrs = Double.parseDouble(""+item.get("bsrs"));
            Double bdrs = Double.parseDouble(""+item.get("bdrs"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStruct.containsKey(fieldName))
            {
                helpStruct.put(fieldName, new Double[endYear - startYear + 1][4]);
            }
            helpStruct.get(fieldName)[nf - startYear][0] = ssrs;
            helpStruct.get(fieldName)[nf - startYear][1] = sdrs;
            helpStruct.get(fieldName)[nf - startYear][2] = bsrs;
            helpStruct.get(fieldName)[nf - startYear][3] = bdrs;
        }

        // build Response
        ContrastEducationByFieldResponse contrastEducationByFieldResponse = new ContrastEducationByFieldResponse();

        contrastEducationByFieldResponse.setNumAry(resList2);

        List<Long> yearsAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        contrastEducationByFieldResponse.setyearsAry(yearsAry);

        // build ssssRatio
        List<HashMap<String, Object>> ssssRatio = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> sssRationOne = new HashMap<String, Object>();
            sssRationOne.put("name", fieldName);

            int allSum = 0;
            List<Double> valueList = new ArrayList<Double>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                if(helpStruct.get(fieldName) == null ||
                        helpStruct.get(fieldName)[j - startYear] == null ||
                        helpStruct.get(fieldName)[j-startYear].length != 4 ||
                        helpStruct.get(fieldName)[j-startYear][0] == null ||
                        helpStruct.get(fieldName)[j-startYear][0] == 0)
                {
                    valueList.add((Double) 0.0);
                    continue;
                }
                else
                {
                    Double ration = helpStruct.get(fieldName)[j - startYear][1]/helpStruct.get(fieldName)[j - startYear][0];
                    BigDecimal b = new BigDecimal(ration);
                    ration = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    valueList.add(ration);;
                }
            }
            sssRationOne.put("value", valueList);
            ssssRatio.add(sssRationOne);
        }
        contrastEducationByFieldResponse.setSsssRatio(ssssRatio);

        // build bsssRatio
        List<HashMap<String, Object>> bsssRatio = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> bssRationOne = new HashMap<String, Object>();
            bssRationOne.put("name", fieldName);

            int allSum = 0;
            List<Double> valueList = new ArrayList<Double>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                if(helpStruct.get(fieldName) == null ||
                        helpStruct.get(fieldName)[j - startYear] == null ||
                        helpStruct.get(fieldName)[j-startYear].length != 4 ||
                        helpStruct.get(fieldName)[j-startYear][2] == null ||
                        helpStruct.get(fieldName)[j-startYear][2] == 0)
                {
                    valueList.add((Double) 0.0);
                    continue;
                }
                else
                {
                    Double ration = helpStruct.get(fieldName)[j - startYear][3]/helpStruct.get(fieldName)[j - startYear][2];
                    BigDecimal b = new BigDecimal(ration);
                    ration = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    valueList.add(ration);
                }
            }
            bssRationOne.put("value", valueList);
            bsssRatio.add(bssRationOne);
        }
        contrastEducationByFieldResponse.setBsssRatio(bsssRatio);

        // build

        return contrastEducationByFieldResponse;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="")
    public ContrastEducationByFieldResponse contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 1;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastEducationService.byUnit1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastEducationService.byUnit2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        if(resList1 == null || resList2 == null)
        {
            return null;
        }

        // build help data structure
        Map<String, Double[][]> helpStruct = new HashMap<>();
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String fieldName = (String) item.get("jgmc");

            Double ssrs = Double.parseDouble(""+item.get("ssrs"));
            Double sdrs = Double.parseDouble(""+item.get("sdrs"));
            Double bsrs = Double.parseDouble(""+item.get("bsrs"));
            Double bdrs = Double.parseDouble(""+item.get("bdrs"));
            Integer nf = Integer.parseInt(""+item.get("nf"));

            if(!helpStruct.containsKey(fieldName))
            {
                helpStruct.put(fieldName, new Double[endYear - startYear + 1][4]);
            }
            helpStruct.get(fieldName)[nf - startYear][0] = ssrs;
            helpStruct.get(fieldName)[nf - startYear][1] = sdrs;
            helpStruct.get(fieldName)[nf - startYear][2] = bsrs;
            helpStruct.get(fieldName)[nf - startYear][3] = bdrs;
        }

        // build Response
        ContrastEducationByFieldResponse contrastEducationByFieldResponse = new ContrastEducationByFieldResponse();

        contrastEducationByFieldResponse.setNumAry(resList2);

        List<Long> yearsAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        contrastEducationByFieldResponse.setyearsAry(yearsAry);

        // build ssssRatio
        List<HashMap<String, Object>> ssssRatio = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> sssRationOne = new HashMap<String, Object>();
            sssRationOne.put("name", fieldName);

            int allSum = 0;
            List<Double> valueList = new ArrayList<Double>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                if(helpStruct.get(fieldName) == null ||
                        helpStruct.get(fieldName)[j - startYear] == null ||
                        helpStruct.get(fieldName)[j-startYear].length != 4 ||
                        helpStruct.get(fieldName)[j-startYear][0] == null ||
                        helpStruct.get(fieldName)[j-startYear][0] == 0)
                {
                    valueList.add((Double) 0.0);
                    continue;
                }
                else
                {
                    Double ration = helpStruct.get(fieldName)[j - startYear][1]/helpStruct.get(fieldName)[j - startYear][0];
                    BigDecimal b = new BigDecimal(ration);
                    ration = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    valueList.add(ration);;
                }
            }
            sssRationOne.put("value", valueList);
            ssssRatio.add(sssRationOne);
        }
        contrastEducationByFieldResponse.setSsssRatio(ssssRatio);

        // build bsssRatio
        List<HashMap<String, Object>> bsssRatio = new ArrayList<>();
        for(String fieldName : helpStruct.keySet())
        {
            HashMap<String, Object> bssRationOne = new HashMap<String, Object>();
            bssRationOne.put("name", fieldName);

            int allSum = 0;
            List<Double> valueList = new ArrayList<Double>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                if(helpStruct.get(fieldName) == null ||
                        helpStruct.get(fieldName)[j - startYear] == null ||
                        helpStruct.get(fieldName)[j-startYear].length != 4 ||
                        helpStruct.get(fieldName)[j-startYear][2] == null ||
                        helpStruct.get(fieldName)[j-startYear][2] == 0)
                {
                    valueList.add((Double) 0.0);
                    continue;
                }
                else
                {
                    Double ration = helpStruct.get(fieldName)[j - startYear][3]/helpStruct.get(fieldName)[j - startYear][2];
                    BigDecimal b = new BigDecimal(ration);
                    ration = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    valueList.add(ration);
                }
            }
            bssRationOne.put("value", valueList);
            bsssRatio.add(bssRationOne);
        }
        contrastEducationByFieldResponse.setBsssRatio(bsssRatio);

        // build

        return contrastEducationByFieldResponse;
    }
}
