package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DTO.Rank2InfoDTO;
import cn.arp.trend.data.model.DTO.RankInfoDTO;
import cn.arp.trend.data.model.converter.RankInfoConverter;
import cn.arp.trend.data.model.response.Rank2InfoResponse;
import cn.arp.trend.data.model.response.RankInfoResponse;
import cn.arp.trend.service.biz.CollaborationService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 对应Node代码中collaboration.js
 *
 * Created with IDEA
 * author:licheng
 * Date:2020/10/6
 * Time:下午11:49
 **/
@Api(value="collaboration",tags={"对应宏观部分collaboration.js"})
@RestController
@RequestMapping(value = "/collaboration")
public class CollaborationController extends BaseController {

    @Resource
    private CollaborationService collaborationService;

    @ApiOperation(value= "对应collaboration.js的/getRank", notes= "对应collaboration.js的/getRank")
    @ServiceExecuter(description = "对应collaboration.js的/getRank")
    @RequestMapping(value = "/getRank", method = RequestMethod.POST)
    @Audit(desc="对应collaboration.js的/getRank")
    public RankInfoResponse rankQuery() {
        RankInfoDTO rankInfo = collaborationService.rankQuery();
        RankInfoResponse rankInfoResponse = RankInfoConverter.INSTANCE.domain2dto(rankInfo);
        return rankInfoResponse;
    }

    @ApiOperation(value= "对应collaboration.js的/getRank2", notes= "对应collaboration.js的/getRank2")
    @ServiceExecuter(description = "对应collaboration.js的/getRank2")
    @RequestMapping(value = "/getRank2", method = RequestMethod.POST)
    @Audit(desc="对应collaboration.js的/getRank2")
    public Rank2InfoResponse rankQuery2() {
        Rank2InfoDTO rank2Info = collaborationService.rankQuery2();
        return new Rank2InfoResponse(rank2Info.getGotoUnitList(), rank2Info.getGotoCountryList());
    }
}
