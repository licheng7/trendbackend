package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.request.contrast.ContrastBaseRequest;
import cn.arp.trend.data.model.request.contrast.ContrastCustomFieldRequest;
import cn.arp.trend.data.model.request.contrast.ContrastCustomUserFieldAffiliationRequest;
import cn.arp.trend.service.biz.ContrastCustomService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;

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
@Api(value="contrastCustom",tags={"对应contrast/custom.js"})
@RestController
@RequestMapping(value = "/contrast/custom")
public class ContrastCustomController extends BaseController {

    @Resource
    private ContrastCustomService contrastCustomService;

    @ApiOperation(value= "获取所有中科院的机构编号、机构名称", notes= "获取数据")
    @ServiceExecuter(description = "获取所有中科院的机构编号、机构名称")
    @RequestMapping(value = "/affiliations", method = RequestMethod.GET)
    @Audit(desc="")
    public HashMap<String, Object> getAffiliations() {

        List<HashMap<String, Object>> res = contrastCustomService.getAffiliations();

        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        if(res == null)
        {
            returnMap.put("code", 500);
            returnMap.put("msg", "SQL查询出错");
            returnMap.put("data", "");
        }
        else
        {
            returnMap.put("code", 200);
            returnMap.put("msg", "");
            returnMap.put("data", res);
        }

        return returnMap;
    }

    @ApiOperation(value= "查某用户的领域标签信息，包括标签ID、标签名", notes= "获取数据")
    @ServiceExecuter(description = "查某用户的领域标签信息，包括标签ID、标签名")
    @RequestMapping(value = "/usertags/{userid}", method = RequestMethod.GET)
    @Audit(desc="")
    public HashMap<String, Object> usertags(@PathVariable(name = "userid", required = true) String userId) {

        List<HashMap<String, Object>> res = contrastCustomService.getUserTags(userId);

        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        if(res == null)
        {
            returnMap.put("code", 500);
            returnMap.put("msg", "SQL查询出错");
            returnMap.put("data", "");
        }
        else
        {
            returnMap.put("code", 200);
            returnMap.put("msg", "");
            returnMap.put("data", res);
        }

        return returnMap;
    }

    @ApiOperation(value= "查选中领域标签、正在编辑的领域标签对应的单位列表", notes= "获取数据")
    @ServiceExecuter(description = "查选中领域标签、正在编辑的领域标签对应的单位列表")
    @RequestMapping(value = "/fieldaffiliations/{userid}/{fieldid}", method = RequestMethod.GET)
    @Audit(desc="")
    public HashMap<String, Object> fieldAffiliations(@PathVariable(name = "userid", required = true) String userId,
                                            @PathVariable(name = "fieldid", required = true) String fieldId) {

        List<HashMap<String, Object>> res = contrastCustomService.getFieldAffiliations(userId, fieldId);

        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        if(res == null)
        {
            returnMap.put("code", 500);
            returnMap.put("msg", "SQL查询出错");
            returnMap.put("data", "");
        }
        else
        {
            returnMap.put("code", 200);
            returnMap.put("msg", "");
            returnMap.put("data", res);
        }

        return returnMap;
    }

    /*
    * POST /contrast/custom/field
    {
        "research_field": "",
        "user_id": "11"
    }
    */
    @ApiOperation(value= "增加当前用户新建的领域标签（自增的领域标签ID、用户ID、默认的领域标签的名称）", notes= "新增数据")
    @ServiceExecuter(description = "增加当前用户新建的领域标签（自增的领域标签ID、用户ID、默认的领域标签的名称）")
    @RequestMapping(value = "/field", method = RequestMethod.POST)
    @Audit(desc="")
    public HashMap<String, Object> field(@RequestBody ContrastCustomFieldRequest request) {

        HashMap<String, Object> res = new HashMap<String, Object>();
        if(request.getResearchField().equals(""))
        {
            res = contrastCustomService.addField1(request.getUserId());
        }
        else
        {
            res = contrastCustomService.addField2(request.getUserId(), request.getResearchField());
        }

        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        try
        {
            if(res != null
                    && res.containsKey("result")
                    && res.get("result").toString().equals("1")
                    && res.containsKey("id")
                    && Integer.parseInt(res.get("id").toString()) > 0)
            {
                returnMap.put("code", 200);
                returnMap.put("msg", "");
                HashMap<String, Object> data = new HashMap<String, Object>();
                data.put("id" , Integer.parseInt(res.get("id").toString()));
                data.put("research_field", "新建领域标签");
                returnMap.put("data", data);
            }
            else
            {
                returnMap.put("code", 500);
                returnMap.put("msg", "SQL查询出错");
                returnMap.put("data", "error");
            }
        }
        catch (Exception e)
        {
            returnMap.put("code", 500);
            returnMap.put("msg", "SQL查询出错");
            returnMap.put("data", "error");
        }
        return returnMap;
    }

    /*
     PUT /contrast/custom/userFieldAffiliation
     {
        "user_id":"11",
        "field_id": 9,
        "research_field": "string",
        "affiliations": [\"114A11\",\"171111\",\"171411\"]
     }
    */
    @ApiOperation(value= "更新当前用户选中的领域标签名称、该选中的领域标签对应的单位列表", notes= "新增/更新数据")
    @ServiceExecuter(description = "更新当前用户选中的领域标签名称、该选中的领域标签对应的单位列表")
    @RequestMapping(value = "/userfieldaffiliation", method = RequestMethod.PUT)
    @Audit(desc="")
    public HashMap<String, Object> userFieldAffiliation(@RequestBody ContrastCustomUserFieldAffiliationRequest request) {



        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        try
        {
            returnMap = contrastCustomService.updateRelationFieldAffiliation(
                    request.getUserId(),
                    request.getFieldId(),
                    request.getResearchField(),
                    request.getAffiliations());
        }
        catch (Exception e)
        {
            returnMap.put("code", 500);
            returnMap.put("msg", "SQL查询出错");
            returnMap.put("data", "error");
        }
        return returnMap;
    }

    /*
     PUT /contrast/custom/userFieldAffiliation
     {
        "user_id":"11",
        "field_id": 9,
        "research_field": "string",
        "affiliations": [\"114A11\",\"171111\",\"171411\"]
     }
    */
    @ApiOperation(value= "删除当前用户选中的领域标签", notes= "删除数据")
    @ServiceExecuter(description = "删除当前用户选中的领域标签")
    @RequestMapping(value = "/userfieldaffiliation/{userid}/{fieldid}", method = RequestMethod.DELETE)
    @Audit(desc="")
    public HashMap<String, Object> deleteUserFieldAffiliation(@PathVariable(name = "userid", required = true) String userId,
                                                              @PathVariable(name = "fieldid", required = true) String fieldId) {

        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        try
        {
            returnMap = contrastCustomService.deleteRelationFieldAffiliation(userId, fieldId);
        }
        catch (Exception e)
        {
            returnMap.put("code", 500);
            returnMap.put("msg", "SQL执行出错");
            returnMap.put("data", "error");
        }
        return returnMap;
    }






}
