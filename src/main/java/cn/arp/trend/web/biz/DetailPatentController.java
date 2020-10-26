package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.ZKYChinaPatentQueryDO;
import cn.arp.trend.data.model.DO.ZKYPCTPatentQueryDO;
import cn.arp.trend.data.model.DTO.ZKYChinaPatentInfoDTO;
import cn.arp.trend.data.model.DTO.ZKYPCTPatentInfoDTO;
import cn.arp.trend.data.model.request.ZKYChinaPatentRequest;
import cn.arp.trend.data.model.request.ZKYPCTPatentRequest;
import cn.arp.trend.data.model.response.ZKYChinaPatentResponse;
import cn.arp.trend.data.model.response.ZKYPCTPatentResponse;
import cn.arp.trend.service.biz.DetailPatentService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午2:38
 **/
@Api(value="detailPatent",tags={"对应宏观部分detailPatent.js(结果已经比对)"})
@RestController
@RequestMapping(value = "/detail/patent")
public class DetailPatentController extends BaseController {

    @Resource
    private DetailPatentService detailPatentService;

    /**
     * detailPatent.js对应的/ZKYPCTPatent
     * 接口已比对
     * @param request
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value= "detailPatent.js对应的/ZKYPCTPatent", notes= "detailPatent.js对应的/ZKYPCTPatent")
    @ServiceExecuter(description = "detailPatent.js对应的/ZKYPCTPatent")
    @RequestMapping(value = "/ZKYPCTPatent", method = RequestMethod.POST)
    @Audit(desc="detailPatent.js对应的/ZKYPCTPatent")
    public List<ZKYPCTPatentResponse> patentZKYPCTQuery(
            @RequestBody @Validated ZKYPCTPatentRequest request, BindingResult bindingResult) throws Exception {
        validData(bindingResult);
        ZKYPCTPatentQueryDO query = new ZKYPCTPatentQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getAffiliation(),
                request.getFields(),
                request.getCategory(),
                request.getSubDataType());
        ZKYPCTPatentInfoDTO xKYPCTPatentInfo = detailPatentService.patentZKYPCTQuery(query);
        return Lists.newArrayList(new ZKYPCTPatentResponse(
                xKYPCTPatentInfo.getClassify(),
                xKYPCTPatentInfo.getFields(),
                xKYPCTPatentInfo.getOrderChinapatent(),
                xKYPCTPatentInfo.getUpdateTime(),
                xKYPCTPatentInfo.getYearList()
        ));
    }

    /**
     * detailPatent.js对应的/ZKYChinaPatent
     * 结果已经比对
     * @param request
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value= "detailPatent.js对应的/ZKYChinaPatent", notes= "detailPatent.js对应的/ZKYChinaPatent")
    @ServiceExecuter(description = "detailPatent.js对应的/ZKYChinaPatent")
    @RequestMapping(value = "/ZKYChinaPatent", method = RequestMethod.POST)
    @Audit(desc="detailPatent.js对应的/ZKYChinaPatent")
    public ZKYChinaPatentResponse patentZKYChinaQuery(
            @RequestBody @Validated ZKYChinaPatentRequest request, BindingResult bindingResult) throws Exception {
        validData(bindingResult);
        ZKYChinaPatentQueryDO query = new ZKYChinaPatentQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getAffiliation(),
                request.getFields()
        );
        ZKYChinaPatentInfoDTO zKYChinaPatentInfo = detailPatentService.patentZKYChinaQuery(query);
        return new ZKYChinaPatentResponse(
                zKYChinaPatentInfo.getPie(),
                zKYChinaPatentInfo.getGraph(),
                zKYChinaPatentInfo.getArpGraph(),
                zKYChinaPatentInfo.getHistogramList(),
                zKYChinaPatentInfo.getYearList(),
                zKYChinaPatentInfo.getResultArray()
        );
    }
}
