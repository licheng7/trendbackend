package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.response.contrast.ContrastPatentByFieldResponse;
import cn.arp.trend.service.biz.ContrastPatentService;
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
@Api(value="contrastPatent",tags={"对应contrast/Patent.js"})
@RestController
@RequestMapping(value = "/contrast/patent")
public class ContrastPatentController extends BaseController {

    @Resource
    private ContrastPatentService contrastPatentService;

    @ApiOperation(value= "以field为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以field为维度做数据对比")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="近十年各领域中国发明专利、PCT专利趋势对比，近十年各领域中国发明专利、PCT专利总数对比", value="FieldContrast.Patent")
    public ContrastPatentByFieldResponse contrastByField(@RequestBody ContrastBaseRequest request) {

        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 0;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastPatentService.byField1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastPatentService.byField2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        if(resList1 == null || resList2 == null)
        {
            return null;
        }

        // build help data structure
        // research_field, nf, num
        Map<String, long[][]> helpStruct1 = new HashMap<>();
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String fieldName = (String) item.get("research_field");

            Integer nf = Integer.parseInt(""+item.get("nf"));
            Integer sx = Integer.parseInt(""+item.get("sx"));
            Integer pz = Integer.parseInt(""+item.get("pz"));
            Integer sq = Integer.parseInt(""+item.get("sq"));

            if(!helpStruct1.containsKey(fieldName))
            {
                helpStruct1.put(fieldName, new long[endYear - startYear + 1][4]);
            }
            helpStruct1.get(fieldName)[nf - startYear][0] = nf;
            helpStruct1.get(fieldName)[nf - startYear][1] = sx;
            helpStruct1.get(fieldName)[nf - startYear][2] = pz;
            helpStruct1.get(fieldName)[nf - startYear][3] = sq;
        }

        Map<String, long[][]> helpStruct2 = new HashMap<>();
        for(int i = 0;i<resList2.size();i++)
        {
            HashMap<String, Object> item = resList2.get(i);
            String fieldName = (String) item.get("research_field");

            Integer nf = Integer.parseInt(""+item.get("nf"));
            Integer xz = Integer.parseInt(""+item.get("xz"));

            if(!helpStruct2.containsKey(fieldName))
            {
                helpStruct2.put(fieldName, new long[endYear - startYear + 1][2]);
            }
            helpStruct2.get(fieldName)[nf - startYear][0] = nf;
            helpStruct2.get(fieldName)[nf - startYear][1] = xz;
        }


        // build response
        ContrastPatentByFieldResponse contrastPatentByFieldResponse = new ContrastPatentByFieldResponse();
        // build ancient_china
        HashMap<String, List<Object>> ancientChina = new HashMap<String, List<Object>>();

        ArrayList<Object> ancient_chinaAxisAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            ancient_chinaAxisAry.add("" + j);
        }
        ancientChina.put("ancient_chinaAxisAry", ancient_chinaAxisAry);

        List<Object> ancient_chinaAry = new ArrayList<Object>();
        for(String fieldName : helpStruct1.keySet())
        {
            HashMap<String, Object> ancient_chinaAryOne = new HashMap<String, Object>();
            ancient_chinaAryOne.put("name", fieldName);

            HashMap<String, Object> valueMap = new HashMap<String, Object>();

            ArrayList<Long> sx = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                sx.add(helpStruct1.get(fieldName)[j - startYear][1]);
            }
            valueMap.put("sx" , sx);

            ArrayList<Long> pz = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                pz.add(helpStruct1.get(fieldName)[j - startYear][2]);
            }
            valueMap.put("pz" , pz);

            ArrayList<Long> sq = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                sq.add(helpStruct1.get(fieldName)[j - startYear][3]);
            }
            valueMap.put("sq" , sq);

            ancient_chinaAryOne.put("value", valueMap);
            ancient_chinaAry.add(ancient_chinaAryOne);
        }
        ancientChina.put("ancient_chinaAry", ancient_chinaAry);
        contrastPatentByFieldResponse.setAncientChina(ancientChina);

        // build ancient_pct
        HashMap<String, List<Object>> ancientPct = new HashMap<String, List<Object>>();

        List<Object> yearsAry = new ArrayList<Object>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        ancientPct.put("yearsAry", yearsAry);

        List<Object> ancient_pctAry = new ArrayList<Object>();
        for(String fieldName : helpStruct2.keySet())
        {
            HashMap<String, Object> ancient_pctAryOne = new HashMap<String, Object>();
            ancient_pctAryOne.put("name", fieldName);

            ArrayList<Long> xz = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                xz.add(helpStruct2.get(fieldName)[j - startYear][1]);
            }

            ancient_pctAryOne.put("value", xz);
            ancient_pctAry.add(ancient_pctAryOne);
        }
        ancientPct.put("ancient_pctAry", ancient_pctAry);

        contrastPatentByFieldResponse.setAncientPct(ancientPct);

        return contrastPatentByFieldResponse;
    }

    @ApiOperation(value= "以unit为维度做数据对比", notes= "数据对比")
    @ServiceExecuter(description = "以unit为维度做数据对比")
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    @Audit(desc="近十年各机构中国发明专利、PCT专利趋势对比，近十年各机构中国发明专利、PCT专利总数对比", value="InstitutionContrast.Patent")
    public ContrastPatentByFieldResponse contrastByUnit(@RequestBody ContrastBaseRequest request) {
        Calendar cal = Calendar.getInstance();
        Integer endYear = cal.get(Calendar.YEAR) - 0;
        Integer startYear = endYear - 9;
        // Integer startNf = 1980;

        List<HashMap<String, Object>> resList1 = contrastPatentService.byUnit1(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        List<HashMap<String, Object>> resList2 = contrastPatentService.byUnit2(
                request.getUserId(),
                startYear,
                endYear,
                request.getDataAry());

        if(resList1 == null || resList2 == null)
        {
            return null;
        }

        // build help data structure
        // research_field, nf, num
        Map<String, long[][]> helpStruct1 = new HashMap<>();
        for(int i = 0;i<resList1.size();i++)
        {
            HashMap<String, Object> item = resList1.get(i);
            String fieldName = (String) item.get("jgmc");

            Integer nf = Integer.parseInt(""+item.get("nf"));
            Integer sx = Integer.parseInt(""+item.get("sx"));
            Integer pz = Integer.parseInt(""+item.get("pz"));
            Integer sq = Integer.parseInt(""+item.get("sq"));

            if(!helpStruct1.containsKey(fieldName))
            {
                helpStruct1.put(fieldName, new long[endYear - startYear + 1][4]);
            }
            helpStruct1.get(fieldName)[nf - startYear][0] = nf;
            helpStruct1.get(fieldName)[nf - startYear][1] = sx;
            helpStruct1.get(fieldName)[nf - startYear][2] = pz;
            helpStruct1.get(fieldName)[nf - startYear][3] = sq;
        }

        Map<String, long[][]> helpStruct2 = new HashMap<>();
        for(int i = 0;i<resList2.size();i++)
        {
            HashMap<String, Object> item = resList2.get(i);
            String fieldName = (String) item.get("jgmc");

            Integer nf = Integer.parseInt(""+item.get("nf"));
            Integer xz = Integer.parseInt(""+item.get("xz"));

            if(!helpStruct2.containsKey(fieldName))
            {
                helpStruct2.put(fieldName, new long[endYear - startYear + 1][2]);
            }
            helpStruct2.get(fieldName)[nf - startYear][0] = nf;
            helpStruct2.get(fieldName)[nf - startYear][1] = xz;
        }


        // build response
        ContrastPatentByFieldResponse contrastPatentByFieldResponse = new ContrastPatentByFieldResponse();
        // build ancient_china
        HashMap<String, List<Object>> ancientChina = new HashMap<String, List<Object>>();

        ArrayList<Object> ancient_chinaAxisAry = new ArrayList<>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            ancient_chinaAxisAry.add("" + j);
        }
        ancientChina.put("ancient_chinaAxisAry", ancient_chinaAxisAry);

        List<Object> ancient_chinaAry = new ArrayList<Object>();
        for(String fieldName : helpStruct1.keySet())
        {
            HashMap<String, Object> ancient_chinaAryOne = new HashMap<String, Object>();
            ancient_chinaAryOne.put("name", fieldName);

            HashMap<String, Object> valueMap = new HashMap<String, Object>();

            ArrayList<Long> sx = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                sx.add(helpStruct1.get(fieldName)[j - startYear][1]);
            }
            valueMap.put("sx" , sx);

            ArrayList<Long> pz = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                pz.add(helpStruct1.get(fieldName)[j - startYear][2]);
            }
            valueMap.put("pz" , pz);

            ArrayList<Long> sq = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                sq.add(helpStruct1.get(fieldName)[j - startYear][3]);
            }
            valueMap.put("sq" , sq);

            ancient_chinaAryOne.put("value", valueMap);
            ancient_chinaAry.add(ancient_chinaAryOne);
        }
        ancientChina.put("ancient_chinaAry", ancient_chinaAry);
        contrastPatentByFieldResponse.setAncientChina(ancientChina);

        // build ancient_pct
        HashMap<String, List<Object>> ancientPct = new HashMap<String, List<Object>>();

        List<Object> yearsAry = new ArrayList<Object>();
        for(int j = startYear ; j <= endYear ; j++)
        {
            yearsAry.add((long) j);
        }
        ancientPct.put("yearsAry", yearsAry);

        List<Object> ancient_pctAry = new ArrayList<Object>();
        for(String fieldName : helpStruct2.keySet())
        {
            HashMap<String, Object> ancient_pctAryOne = new HashMap<String, Object>();
            ancient_pctAryOne.put("name", fieldName);

            ArrayList<Long> xz = new ArrayList<Long>();
            for(int j = startYear ; j <= endYear ; j++)
            {
                xz.add(helpStruct2.get(fieldName)[j - startYear][1]);
            }

            ancient_pctAryOne.put("value", xz);
            ancient_pctAry.add(ancient_pctAryOne);
        }
        ancientPct.put("ancient_pctAry", ancient_pctAry);

        contrastPatentByFieldResponse.setAncientPct(ancientPct);

        return contrastPatentByFieldResponse;
    }
}
