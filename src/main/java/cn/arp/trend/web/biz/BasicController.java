package cn.arp.trend.web.biz;

import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;
import cn.arp.trend.data.model.converter.OrgAndResearchConverter;
import cn.arp.trend.data.model.converter.OrgInfoRequestConverter;
import cn.arp.trend.data.model.request.OrgInfoQueryRequest;
import cn.arp.trend.data.model.response.MenuResponse;
import cn.arp.trend.data.model.response.MenuResult;
import cn.arp.trend.data.model.response.OrgInfoResponse;
import cn.arp.trend.data.model.response.common.DataResult;
import cn.arp.trend.service.biz.BasicService;
import cn.arp.trend.tools.ResultUtils;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对应Node代码中basic.js
 *
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:31
 **/
@RestController
@RequestMapping(value = "/basic")
public class BasicController {

    @Resource
    private BasicService basicService;

    @ApiOperation(value= "查询菜单", notes= "无参查询菜单")
    @ServiceExecuter(description = "查询菜单")
    @RequestMapping(value = "/nav", method = RequestMethod.POST)
    public DataResult<MenuResponse> menuQuery() {
        List menuList = Lists.newArrayList();
        menuList.add(new MenuResult("科研投入", "/rp"));
        menuList.add(new MenuResult("科研产出", "/po"));
        menuList.add(new MenuResult("科研人才", "/rt"));
        menuList.add(new MenuResult("科研发展", "/rd"));
        menuList.add(new MenuResult("教育态势", "/es"));
        menuList.add(new MenuResult("多维比较", "/md"));

        MenuResponse menuResponse = new MenuResponse(0, menuList);
        return ResultUtils.wrapSuccess(menuResponse);
    }

    @ApiOperation(value= "查询单位信息、领域信息", notes= "查询单位信息、领域信息")
    @ServiceExecuter(description = "查询单位信息、领域信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public DataResult<OrgInfoResponse> orgQuery(OrgInfoQueryRequest request) {
        OrgInfoQueryDO orgInfoQueryDO = OrgInfoRequestConverter.INSTANCE.domain2dto(request);
        OrgInfoDTO bizResult = basicService.orgInfoQuery(orgInfoQueryDO);
        List<String> fields = bizResult.getFields();
        List<OrgInfoDTO.OrgAndResearchDTO> institutions = bizResult.getInstitutions();
        return ResultUtils.wrapSuccess(
                new OrgInfoResponse(fields,
                        OrgAndResearchConverter.INSTANCE.domain2dto(institutions)));
    }

    @ApiOperation(value= "获取近十年的年份", notes= "获取近十年的年份，用于时间下拉菜单，供用户选择起止时间")
    @ServiceExecuter(description = "获取近十年的年份")
    @RequestMapping(value = "/year", method = RequestMethod.POST)
    public DataResult<List<String>> queryYear() {
        List<String> bizResult = basicService.queryYear();
        return ResultUtils.wrapSuccess(bizResult);
    }
}
