package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AreaEduQueryDO;
import cn.arp.trend.data.model.DTO.AreaEduDInfoDTO;
import cn.arp.trend.data.model.DTO.AreaEduMInfoDTO;
import cn.arp.trend.data.model.DTO.AreaEduStudentInfoDTO;
import cn.arp.trend.data.model.request.AreaEduRequest;
import cn.arp.trend.data.model.response.AreaEduDResponse;
import cn.arp.trend.data.model.response.AreaEduMResponse;
import cn.arp.trend.data.model.response.AreaEduStudentResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.AreaEduService;
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
@Api(value="areaEdu",tags={"对应领域部分edu.js"})
@RestController
@RequestMapping(value = "/area/edu")
public class AreaEduController extends BaseController {

    @Resource
    private AreaEduService areaEduService;

    /**
     * updateData.js对应的/
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "edu.js对应的/d/", notes= "edu.js对应的/d/")
    @ServiceExecuter(description = "edu.js对应的/d/")
    @RequestMapping(value = "/d", method = RequestMethod.POST)
    @Audit(desc="历年博导人数、博导年龄分布、各机构博导人数、所选单位博导人数占比", value="Area.Doctor")
    public AreaEduDResponse areaEduDQuery(@RequestBody @Validated AreaEduRequest request,
                                          BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaEduQueryDO query = new AreaEduQueryDO(request.getStartYear(), request.getEndYear(),
                request.getUnitIdAry());
        AreaEduDInfoDTO areaEduInfo = areaEduService.areaEduDQuery(query);
        return new AreaEduDResponse(
                areaEduInfo.getYearList(),
                areaEduInfo.getDoctorTeacherList(),
                areaEduInfo.getDoctorTeacherUnitList(),
                areaEduInfo.getDoctorTeacherYear(),
                areaEduInfo.getDoctorTeacherPie()
        );
    }

    /**
     * updateData.js对应的/
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "edu.js对应的/m/", notes= "edu.js对应的/m/")
    @ServiceExecuter(description = "edu.js对应的/m/")
    @RequestMapping(value = "/m", method = RequestMethod.POST)
    @Audit(desc="历年硕导人数、硕导年龄分布、各机构硕导人数、所选单位硕导人数占比", value="Area.Master")
    public AreaEduMResponse areaEduMQuery(@RequestBody @Validated AreaEduRequest request,
                                          BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaEduQueryDO query = new AreaEduQueryDO(request.getStartYear(), request.getEndYear(),
                request.getUnitIdAry());
        AreaEduMInfoDTO areaEduInfo = areaEduService.areaEduMQuery(query);
        return new AreaEduMResponse(
                areaEduInfo.getYearList(),
                areaEduInfo.getMasterTeacherList(),
                areaEduInfo.getMasterTeacherUnitList(),
                areaEduInfo.getMasterTeacherYear(),
                areaEduInfo.getMasterTeacherPie()
        );
    }

    /**
     * updateData.js对应的/
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "edu.js对应的/student/", notes= "edu.js对应的/student/")
    @ServiceExecuter(description = "edu.js对应的/student/")
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    @Audit(desc="历年博士生人数、硕士生人数", value="Area.Student")
    public AreaEduStudentResponse areaEduStudentQuery(@RequestBody @Validated AreaEduRequest request,
                                                      BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaEduQueryDO query = new AreaEduQueryDO(request.getStartYear(), request.getEndYear(),
                request.getUnitIdAry());
        AreaEduStudentInfoDTO areaEduInfo = areaEduService.areaEduStudentQuery(query);
        return new AreaEduStudentResponse(
                areaEduInfo.getYearList(),
                areaEduInfo.getDoctorListSchool(),
                areaEduInfo.getDoctorListGraduate(),
                areaEduInfo.getMasterListSchool(),
                areaEduInfo.getMasterListGraduate()
        );
    }
}
