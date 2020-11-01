package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AreaHrQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.request.AreaHrRequest;
import cn.arp.trend.data.model.response.*;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.AreaHrService;
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
@Api(value="hr",tags={"对应领域部分hr.js"})
@RestController
@RequestMapping(value = "/area/hr")
public class AreaHrController extends BaseController {

    @Resource
    private AreaHrService AreaHrService;

    /**
     * 在职职工 (hr.js对应的/staffTrend)
     * 结果已比对
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "hr.js对应的/staffTrend", notes= "hr.js对应的/staffTrend")
    @ServiceExecuter(description = "hr.js对应的/staffTrend")
    @RequestMapping(value = "/staffTrend", method = RequestMethod.POST)
    @Audit(desc="hr.js对应的/staffTrend")
    public AreaHrStaffTrendResponse areaStaffTrendQuery(
            @RequestBody @Validated AreaHrRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaHrQueryDO query = new AreaHrQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaHrStaffTrendInfoDTO areaHrInfo = AreaHrService.areaStaffTrendQuery(query);
        return new AreaHrStaffTrendResponse(
                areaHrInfo.getYearList(),
                areaHrInfo.getSumAry(),
                areaHrInfo.getMajorAry(),
                areaHrInfo.getAdministrativeAry(),
                areaHrInfo.getWorkerAry(),
                areaHrInfo.getUpdateTime()
        );
    }

    /**
     * hr.js对应的/staffDist
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "hr.js对应的/staffDist", notes= "hr.js对应的/staffDist")
    @ServiceExecuter(description = "hr.js对应的/staffDist")
    @RequestMapping(value = "/staffDist", method = RequestMethod.POST)
    @Audit(desc="hr.js对应的/staffDist")
    public AreaHrStaffDistResponse areaStaffDistQuery(
            @RequestBody @Validated AreaHrRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaHrQueryDO query = new AreaHrQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaHrStaffDistInfoDTO areaHrInfo = AreaHrService.areaStaffDistQuery(query);
        return new AreaHrStaffDistResponse(
                areaHrInfo.getUnitAry(),
                areaHrInfo.getAgeAry(),
                areaHrInfo.getEntiretyAry(),
                areaHrInfo.getPositionAry(),
                areaHrInfo.getEducationAry(),
                areaHrInfo.getTitleAry(),
                areaHrInfo.getResultArray(),
                areaHrInfo.getUpdateTime(),
                request
        );
    }

    /**
     * hr.js对应的/acadCaeTrend
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "hr.js对应的/acadCaeTrend", notes= "hr.js对应的/acadCaeTrend")
    @ServiceExecuter(description = "hr.js对应的/acadCaeTrend")
    @RequestMapping(value = "/acadCaeTrend", method = RequestMethod.POST)
    @Audit(desc="hr.js对应的/acadCaeTrend")
    public AreaHrAcadCaeTrendResponse areaAcadCaeTrendQuery(
            @RequestBody @Validated AreaHrRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaHrQueryDO query = new AreaHrQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaHrAcadCaeTrendInfoDTO areaHrInfo = AreaHrService.areaAcadCaeTrendQuery(query);
        return new AreaHrAcadCaeTrendResponse(
                areaHrInfo.getYearList(),
                areaHrInfo.getDataList(),
                areaHrInfo.getUpdateTime()
        );
    }

    /**
     * hr.js对应的/acadCaeDist
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "hr.js对应的/acadCaeDist", notes= "hr.js对应的/acadCaeDist")
    @ServiceExecuter(description = "hr.js对应的/acadCaeDist")
    @RequestMapping(value = "/acadCaeDist", method = RequestMethod.POST)
    @Audit(desc="hr.js对应的/acadCaeDist")
    public AreaHrAcadCaeDistResponse areaAcadCaeDistQuery(
            @RequestBody @Validated AreaHrRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaHrQueryDO query = new AreaHrQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaHrAcadCaeDistInfoDTO areaHrInfo = AreaHrService.areaAcadCaeDistQuery(query);
        return new AreaHrAcadCaeDistResponse(
                areaHrInfo.getProportion(),
                areaHrInfo.getUnitData(),
                areaHrInfo.getUpdateTime()
        );
    }

    /**
     * hr.js对应的/acadCasTrend
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "hr.js对应的/acadCasTrend", notes= "hr.js对应的/acadCasTrend")
    @ServiceExecuter(description = "hr.js对应的/acadCasTrend")
    @RequestMapping(value = "/acadCasTrend", method = RequestMethod.POST)
    @Audit(desc="hr.js对应的/acadCasTrend")
    public AreaHrAcadCasTrendResponse areaAcadCasTrendQuery(
            @RequestBody @Validated AreaHrRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaHrQueryDO query = new AreaHrQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaHrAcadCasTrendInfoDTO areaHrInfo = AreaHrService.areaAcadCasTrendQuery(query);
        return new AreaHrAcadCasTrendResponse(
                areaHrInfo.getYearList(),
                areaHrInfo.getDataList(),
                areaHrInfo.getUpdateTime(),
                areaHrInfo.getResultArray()
        );
    }

    /**
     * hr.js对应的/acadCasDist
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "hr.js对应的/acadCasDist", notes= "hr.js对应的/acadCasDist")
    @ServiceExecuter(description = "hr.js对应的/acadCasDist")
    @RequestMapping(value = "/acadCasDist", method = RequestMethod.POST)
    @Audit(desc="hr.js对应的/acadCasDist")
    public AreaHrAcadCasDistResponse areaAcadCasDistQuery(
            @RequestBody @Validated AreaHrRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaHrQueryDO query = new AreaHrQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaHrAcadCasDistInfoDTO areaHrInfo = AreaHrService.areaAcadCasDistQuery(query);
        return new AreaHrAcadCasDistResponse(
                areaHrInfo.getProportion(),
                areaHrInfo.getUnitData(),
                areaHrInfo.getUpdateTime());
    }

    /**
     * hr.js对应的/youngElite
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "hr.js对应的/youngElite", notes= "hr.js对应的/youngElite")
    @ServiceExecuter(description = "hr.js对应的/youngElite")
    @RequestMapping(value = "/youngElite", method = RequestMethod.POST)
    @Audit(desc="hr.js对应的/youngElite")
    public AreaHrYoungEliteResponse areaYoungEliteQuery(
            @RequestBody @Validated AreaHrRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaHrQueryDO query = new AreaHrQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaHrYoungEliteInfoDTO areaHrInfo = AreaHrService.areaYoungEliteQuery(query);
        return new AreaHrYoungEliteResponse(
                areaHrInfo.getYearList(),
                areaHrInfo.getRsYoungList(),
                areaHrInfo.getRsStaffList(),
                areaHrInfo.getUnitList());
    }
}
