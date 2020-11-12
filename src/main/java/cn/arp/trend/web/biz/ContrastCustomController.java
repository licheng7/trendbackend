package cn.arp.trend.web.biz;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import cn.arp.trend.data.model.request.contrast.ContrastCustomUserIdFieldIdRequest;
import cn.arp.trend.data.model.request.contrast.ContrastCustomUserIdRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.request.contrast.ContrastCustomFieldRequest;
import cn.arp.trend.data.model.request.contrast.ContrastCustomUserFieldAffiliationRequest;
import cn.arp.trend.service.biz.ContrastCustomService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
    @Audit(desc="获取中科院所有研究所的机构编号、机构名称", value="")
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
    @RequestMapping(value = "/usertags", method = RequestMethod.POST)
    @Audit(desc="获取用户自定义的标签信息（标签ID、标签名", value="")
    public HashMap<String, Object> usertags(@RequestBody ContrastCustomUserIdRequest request) throws Exception {

        String userId = request.getUserId();
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
    @RequestMapping(value = "/fieldaffiliations", method = RequestMethod.POST)
    @Audit(desc="获取指定标签对应的单位列表", value="")
    public HashMap<String, Object> fieldAffiliations(@RequestBody ContrastCustomUserIdFieldIdRequest request) {

        String userId = request.getUserId();
        String fieldId = request.getFieldId();

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
    @Audit(desc="新建标签", value="")
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
    @Audit(desc="更新标签", value="")
    public HashMap<String, Object> userFieldAffiliation(@RequestBody ContrastCustomUserFieldAffiliationRequest request) {
       return contrastCustomService.updateRelationFieldAffiliation(
                request.getUserId(),
                request.getFieldId(),
                request.getResearchField(),
                request.getAffiliations());
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
    @RequestMapping(value = "/userfieldaffiliation", method = RequestMethod.DELETE)
    @Audit(desc="删除标签", value="")
    public HashMap<String, Object> deleteUserFieldAffiliation(@RequestBody ContrastCustomUserIdFieldIdRequest request) {

        String userId = request.getUserId();
        String fieldId = request.getFieldId();

        return contrastCustomService.deleteRelationFieldAffiliation(userId, fieldId);
    }
}
