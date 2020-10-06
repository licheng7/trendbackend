package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.AcademicianInfoDTO;
import cn.arp.trend.data.model.DTO.InternationInfoDTO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;
import cn.arp.trend.data.model.converter.AcademicianRequestConverter;
import cn.arp.trend.data.model.converter.FieldsConverter;
import cn.arp.trend.data.model.converter.OrgAndResearchConverter;
import cn.arp.trend.data.model.converter.OrgInfoRequestConverter;
import cn.arp.trend.data.model.request.AcademicianQueryRequest;
import cn.arp.trend.data.model.request.OrgInfoQueryRequest;
import cn.arp.trend.data.model.response.*;
import cn.arp.trend.service.biz.BasicService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
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
@Api(value="basic",tags={"basic"})
@RestController
@RequestMapping(value = "/basic")
public class BasicController extends BaseController {

    @Resource
    private BasicService basicService;

    @ApiOperation(value= "查询菜单", notes= "无参查询菜单")
    @ServiceExecuter(description = "查询菜单")
    @RequestMapping(value = "/nav", method = RequestMethod.POST)
    @Audit(desc="查询菜单")
    public MenuResponse menuQuery() {
        List<MenuResult> menuList = Lists.newArrayList();
        menuList.add(new MenuResult("科研投入", "/rp"));
        menuList.add(new MenuResult("科研产出", "/po"));
        menuList.add(new MenuResult("科研人才", "/rt"));
        menuList.add(new MenuResult("科研发展", "/rd"));
        menuList.add(new MenuResult("教育态势", "/es"));
        menuList.add(new MenuResult("多维比较", "/md"));

        MenuResponse menuResponse = new MenuResponse(0, menuList);
        return menuResponse;
    }

    @ApiOperation(value= "查询单位信息、领域信息", notes= "查询单位信息、领域信息")
    @ServiceExecuter(description = "查询单位信息、领域信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @Audit(desc="查询单位信息、领域信息")
    public OrgInfoResponse orgQuery(OrgInfoQueryRequest request) {
        OrgInfoQueryDO orgInfoQueryDO = OrgInfoRequestConverter.INSTANCE.domain2dto(request);
        OrgInfoDTO bizResult = basicService.orgInfoQuery(orgInfoQueryDO);
        List<String> fields = bizResult.getFields();
        List<OrgInfoDTO.OrgAndResearchDTO> institutions = bizResult.getInstitutions();
        return new OrgInfoResponse(fields,
                OrgAndResearchConverter.INSTANCE.domain2dto(institutions));
    }

    @ApiOperation(value= "获取近十年的年份", notes= "获取近十年的年份，用于时间下拉菜单，供用户选择起止时间")
    @ServiceExecuter(description = "获取近十年的年份")
    @RequestMapping(value = "/year", method = RequestMethod.POST)
    @Audit(desc="获取近十年的年份")
    public List<String> yearQuery() {
        List<String> bizResult = basicService.yearQuery();
        return bizResult;
    }

    @ApiOperation(value= "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）", notes= "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    @ServiceExecuter(description = "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    @RequestMapping(value = "/academician", method = RequestMethod.POST)
    @Audit(desc="获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    public AcademicianResponse academicianQuery(AcademicianQueryRequest request) {
        AcademicianQueryDO academicianQueryDO =  AcademicianRequestConverter.INSTANCE.domain2dto
                (request);
        AcademicianInfoDTO academicianInfoDTO = basicService.academicianQuery(academicianQueryDO);
        return new AcademicianResponse(FieldsConverter.INSTANCE.domain2dto(academicianInfoDTO),
                academicianInfoDTO.getInstitutions());
    }

    @ApiOperation(value= "国际合作头部", notes= "国际合作头部")
    @ServiceExecuter(description = "国际合作头部")
    @RequestMapping(value = "/internationInfo", method = RequestMethod.POST)
    @Audit(desc="国际合作头部")
    public InternationInfoResponse internationInfoQuery() {
        InternationInfoDTO internationInfoDTO = basicService.internationInfoQuery();
        return new InternationInfoResponse(
                internationInfoDTO.getSortedCountryList(),
                internationInfoDTO.getSortedNationalityList(),
                internationInfoDTO.getSortedFormList(),
                internationInfoDTO.getAgeList(),
                Lists.newArrayList(new SexResult("all", "全部性别"),
                        new SexResult("man", "男"),
                        new SexResult("woman", "女"))
        );
    }
}
