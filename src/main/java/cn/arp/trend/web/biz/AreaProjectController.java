package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.DO.AreaProjectNsfcDistQueryDO;
import cn.arp.trend.data.model.DO.AreaProjectNsfcTrendQueryDO;
import cn.arp.trend.data.model.DO.AreaProjectQueryDO;
import cn.arp.trend.data.model.DTO.AreaProjectNsfcDistInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectNsfcTrendInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectXdzxInfoDTO;
import cn.arp.trend.data.model.DTO.AreaProjectZdyfInfoDTO;
import cn.arp.trend.data.model.request.AreaProjectNsfcDistRequest;
import cn.arp.trend.data.model.request.AreaProjectNsfcTrendRequest;
import cn.arp.trend.data.model.request.AreaProjectRequest;
import cn.arp.trend.data.model.response.AreaProjectNsfcDistResponse;
import cn.arp.trend.data.model.response.AreaProjectNsfcTrendResponse;
import cn.arp.trend.data.model.response.AreaProjectXdzxResponse;
import cn.arp.trend.data.model.response.AreaProjectZdyfResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.AreaProjectService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午2:38
 **/
@Api(value="project",tags={"对应领域部分project.js"})
@RestController
@RequestMapping(value = "/area/project")
@RequirePermission(dataset=true)
public class AreaProjectController extends BaseController {

    @Resource
    private AreaProjectService areaProjectService;

    /**
     * project.js对应的/xdzx
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "project.js对应的/xdzx", notes= "project.js对应的/xdzx")
    @ServiceExecuter(description = "project.js对应的/xdzx")
    @RequestMapping(value = "/xdzx", method = RequestMethod.POST)
    @Audit(desc="先导专项课题数量、经费数量趋势，课题数量、经费数量占比，各单位课题数量、经费数量统计", value="Area.XDZX")
    public AreaProjectXdzxResponse xdzxQuery(
            @RequestBody @Validated AreaProjectRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        AreaProjectQueryDO query = new AreaProjectQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaProjectXdzxInfoDTO areaProjectXdzxInfo = areaProjectService.xdzxQuery(query);
        return new AreaProjectXdzxResponse(
                areaProjectXdzxInfo.getYearList(),
                areaProjectXdzxInfo.getTaskNumData(),
                areaProjectXdzxInfo.getExpenditureNumData(),
                areaProjectXdzxInfo.getUnitTaskData(),
                areaProjectXdzxInfo.getUnitExpenditureData(),
                areaProjectXdzxInfo.getUpdateTime(),
                areaProjectXdzxInfo.getTaskTotal(),
                areaProjectXdzxInfo.getExpenditureTotal()
        );
    }

    /**
     * project.js对应的/zdyf
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "project.js对应的/zdyf", notes= "project.js对应的/zdyf")
    @ServiceExecuter(description = "project.js对应的/zdyf")
    @RequestMapping(value = "/zdyf", method = RequestMethod.POST)
    @Audit(desc="重点研发计划项目项目数量、经费数量趋势，项目数量、经费数量占比，各单位项目数量、经费数量统计", value="Area.ZDYF")
    public AreaProjectZdyfResponse zdyfQuery(
            @RequestBody @Validated AreaProjectRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        AreaProjectQueryDO query = new AreaProjectQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaProjectZdyfInfoDTO areaProjectZdyfInfo = areaProjectService.zdyfQuery(query);
        return new AreaProjectZdyfResponse(
                areaProjectZdyfInfo.getYearList(),
                areaProjectZdyfInfo.getTaskNumData(),
                areaProjectZdyfInfo.getExpenditureNumData(),
                areaProjectZdyfInfo.getUnitTaskData(),
                areaProjectZdyfInfo.getUnitExpenditureData(),
                areaProjectZdyfInfo.getUpdateTime(),
                areaProjectZdyfInfo.getTaskTotal(),
                areaProjectZdyfInfo.getExpenditureTotal()
        );
    }

    /**
     * project.js对应的/nsfcTrend
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "project.js对应的/nsfcTrend", notes= "project.js对应的/nsfcTrend")
    @ServiceExecuter(description = "project.js对应的/nsfcTrend")
    @RequestMapping(value = "/nsfcTrend", method = RequestMethod.POST)
    @Audit(desc="自然科学基金项目数量、经费数量趋势", value="Area.NSFCTrend")
    public AreaProjectNsfcTrendResponse nsfcTrendQuery(
            @RequestBody @Validated AreaProjectNsfcTrendRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        AreaProjectNsfcTrendQueryDO query = new AreaProjectNsfcTrendQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry(), request.getCategory());
        AreaProjectNsfcTrendInfoDTO areaProjectNsfcTrendInfo = areaProjectService.nsfcTrendQuery(query);
        return new AreaProjectNsfcTrendResponse(
                areaProjectNsfcTrendInfo.getYearList(),
                areaProjectNsfcTrendInfo.getProjectNumData(),
                areaProjectNsfcTrendInfo.getARPExpenditureNumData(),
                areaProjectNsfcTrendInfo.getUpdateTime(),
                areaProjectNsfcTrendInfo.getResultArray()
        );
    }

    /**
     * project.js对应的/nsfcDist
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "project.js对应的/nsfcDist", notes= "project.js对应的/nsfcDist")
    @ServiceExecuter(description = "project.js对应的/nsfcDist")
    @RequestMapping(value = "/nsfcDist", method = RequestMethod.POST)
    @Audit(desc="自然科学基金项目项目数量、经费数量占比，各单位项目数量、经费数量统计", value="Area.NSFCDist")
    public AreaProjectNsfcDistResponse nsfcDistQuery(
            @RequestBody @Validated AreaProjectNsfcDistRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        AreaProjectNsfcDistQueryDO query = new AreaProjectNsfcDistQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry(), request.getCategory());
        AreaProjectNsfcDistInfoDTO areaProjectNsfcDistInfo =
                areaProjectService.nsfcDistQuery(query);
        return new AreaProjectNsfcDistResponse(
                areaProjectNsfcDistInfo.getUnitProjectData(),
                areaProjectNsfcDistInfo.getUnitExpenditureData(),
                areaProjectNsfcDistInfo.getProjectTotal(),
                areaProjectNsfcDistInfo.getExpenditureTotal(),
                areaProjectNsfcDistInfo.getUpdateTime(),
                areaProjectNsfcDistInfo.getResultArray()
        );
    }
}
