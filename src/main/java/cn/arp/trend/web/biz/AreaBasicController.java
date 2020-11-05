package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.DTO.AcademicianInfoDTO;
import cn.arp.trend.data.model.DTO.InternationInfoDTO;
import cn.arp.trend.data.model.DTO.OrgInfoDTO;
import cn.arp.trend.data.model.converter.AcademicianRequestConverter;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 对应Node代码中basic.js
 *
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:31
 **/
@Api(value="basic",tags={"对应领域部分basic.js"})
@RestController
@RequestMapping(value = "/area/basic")
public class AreaBasicController extends BaseController {

    @Resource
    private BasicService basicService;

    /**
     * 查询单位信息、领域信息
     * 结果已核对
     * @param request
     * @return
     */
    @ApiOperation(value= "查询单位信息、领域信息", notes= "查询单位信息、领域信息")
    @ServiceExecuter(description = "查询单位信息、领域信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @Audit(desc="查询单位信息、领域信息")
    public OrgInfoResponse orgQuery(@RequestBody OrgInfoQueryRequest request) {
        OrgInfoQueryDO orgInfoQuery = OrgInfoRequestConverter.INSTANCE.domain2dto(request);
        OrgInfoDTO bizResult = basicService.orgInfoQuery(orgInfoQuery);
        return new OrgInfoResponse(
                bizResult.getInstitutions(), bizResult.getFields());
    }

    /**
     * 获取近十年的年份
     * @return
     */
    @ApiOperation(value= "获取近十年的年份", notes= "获取近十年的年份，用于时间下拉菜单，供用户选择起止时间")
    @ServiceExecuter(description = "获取近十年的年份")
    @RequestMapping(value = "/year", method = RequestMethod.POST)
    @Audit(desc="获取近十年的年份")
    public List<String> yearQuery() {
        List<String> bizResult = basicService.yearQuery();
        return bizResult;
    }

    /**
     * 获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）
     * @param request
     * @return
     */
    @ApiOperation(value= "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）", notes= "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    @ServiceExecuter(description = "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    @RequestMapping(value = "/academicianOld", method = RequestMethod.POST)
    @Audit(desc="获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    public AcademicianResponse academicianOldQuery(@RequestBody AcademicianQueryRequest request) {
        AcademicianQueryDO academicianQuery =
                AcademicianRequestConverter.INSTANCE.domain2dto(request);
        AcademicianInfoDTO academicianInfo = basicService.academicianQuery(academicianQuery);
        return new AcademicianResponse(
                academicianInfo.getFields(), academicianInfo.getInstitutions());
    }

    /**
     * 获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）
     * @param request
     * @return
     */
    @ApiOperation(value= "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）", notes= "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    @ServiceExecuter(description = "获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    @RequestMapping(value = "/academician", method = RequestMethod.POST)
    @Audit(desc="获取中科院院士学部信息，工程院院士学部信息，单位信息（两者的并集）")
    public AcademicianResponse academicianNewQuery(@RequestBody AcademicianQueryRequest request) {
        AcademicianQueryDO academicianQuery =
                AcademicianRequestConverter.INSTANCE.domain2dto(request);
        AcademicianInfoDTO academicianInfo = basicService.academicianQuery(academicianQuery);
        return new AcademicianResponse(
                academicianInfo.getFields(), academicianInfo.getInstitutions());
    }

    /**
     *
     * @return
     */
    @ApiOperation(value= "领域部分/colormap", notes= "领域部分/colormap")
    @ServiceExecuter(description = "领域部分/colormap")
    @RequestMapping(value = "/colormap", method = RequestMethod.POST)
    @Audit(desc="领域部分/colormap")
    public ColormapResponse colormapQuery() {

        List<String> field = Lists.newArrayList("基础前沿交叉", "海洋", "材料", "能源", "光电空间", "资源生态环境", "生命与健康", "信息");
        List<String> fieldColor = Lists.newArrayList("#0094ff", "#ffae5d", "#eb754b", "#48c9b0", "#9f84db", "#456bf4", "#d1ae15", "#c93d37");

        return new ColormapResponse(field, fieldColor);
    }

    @ApiOperation(value= "国际合作头部", notes= "国际合作头部")
    @ServiceExecuter(description = "国际合作头部")
    @RequestMapping(value = "/internationInfo", method = RequestMethod.POST)
    @Audit(desc="国际合作头部")
    public InternationInfoResponse internationInfoQuery() {
        InternationInfoDTO internationInfoDTO = basicService.internationInfoQuery();
        return new InternationInfoResponse(
                internationInfoDTO.getCountry(),
                internationInfoDTO.getNationality(),
                internationInfoDTO.getForm(),
                internationInfoDTO.getAgeList(),
                Lists.newArrayList(
                        new SexResult("all", "全部性别"),
                        new SexResult("man", "男"),
                        new SexResult("woman", "女"))
        );
    }

    @ApiOperation(value= "获取分院单位数据", notes= "获取分院单位数据")
    @ServiceExecuter(description = "获取分院单位数据")
    @RequestMapping(value = "/sorting", method = RequestMethod.POST)
    @Audit(desc="获取分院单位数据")
    public List<Map<String, Object>> sortingQuery() {
        List<Map<String, Object>> country = basicService.sortingQuery();
        return country;
    }
}
