package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.AreaPaperQueryDO;
import cn.arp.trend.data.model.DTO.AreaPaperSciInfoDTO;
import cn.arp.trend.data.model.request.AreaPaperRequest;
import cn.arp.trend.data.model.response.AreaPaperSciResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.AreaPaperService;
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
@Api(value="paper",tags={"对应领域部分paper.js"})
@RestController
@RequestMapping(value = "/area/paper")
public class AreaPaperController extends BaseController {

    @Resource
    private AreaPaperService areaPaperService;

    /**
     * paper.js对应的/sci
     * @param request
     * @param bindingResult
     * @return
     * @throws RestError
     */
    @ApiOperation(value= "paper.js对应的/sci", notes= "paper.js对应的/sci")
    @ServiceExecuter(description = "paper.js对应的/sci")
    @RequestMapping(value = "/sci", method = RequestMethod.POST)
    @Audit(desc="paper.js对应的/sci")
    public AreaPaperSciResponse sciQuery(
            @RequestBody @Validated AreaPaperRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        AreaPaperQueryDO query = new AreaPaperQueryDO(
                request.getStartYear(), request.getEndYear(), request.getUnitIdAry());
        AreaPaperSciInfoDTO areaPaperSciInfo = areaPaperService.sciQuery(query);
        return new AreaPaperSciResponse(
                areaPaperSciInfo.getOfficialList(),
                areaPaperSciInfo.getArpList(),
                areaPaperSciInfo.getUnitAry(),
                areaPaperSciInfo.getPaperProportion(),
                areaPaperSciInfo.getYearList(),
                areaPaperSciInfo.getUpdateTime()
        );
    }
}
