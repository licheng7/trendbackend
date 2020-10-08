package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.ComeAnalyseQueryDO;
import cn.arp.trend.data.model.DO.GoAnalyseQueryDO;
import cn.arp.trend.data.model.DTO.*;
import cn.arp.trend.data.model.converter.*;
import cn.arp.trend.data.model.request.ComeAnalyseRequest;
import cn.arp.trend.data.model.request.GoAnalyseRequest;
import cn.arp.trend.data.model.response.*;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.CollaborationService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

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
@Validated
public class CollaborationController extends BaseController {

    @Resource
    private CollaborationService collaborationService;

    @ApiOperation(value= "出来访统计信息(对应collaboration.js的/getRank)", notes= "出来访统计信息(对应collaboration.js的/getRank)")
    @ServiceExecuter(description = "出来访统计信息(对应collaboration.js的/getRank)")
    @RequestMapping(value = "/getRank", method = RequestMethod.POST)
    @Audit(desc="出来访统计信息(对应collaboration.js的/getRank)")
    public RankInfoResponse rankQuery() {
        RankInfoDTO rankInfo = collaborationService.rankQuery();
        RankInfoResponse rankInfoResponse = RankInfoConverter.INSTANCE.domain2dto(rankInfo);
        return rankInfoResponse;
    }

    @ApiOperation(value= "单位排名、国家排名(对应collaboration.js的/getRank2)", notes= "单位排名、国家排名(对应collaboration.js的/getRank2)")
    @ServiceExecuter(description = "单位排名、国家排名(对应collaboration.js的/getRank2)")
    @RequestMapping(value = "/getRank2", method = RequestMethod.POST)
    @Audit(desc="单位排名、国家排名(对应collaboration.js的/getRank2)")
    public Rank2InfoResponse rankQuery2() {
        Rank2InfoDTO rank2Info = collaborationService.rankQuery2();
        return new Rank2InfoResponse(rank2Info.getGotoUnitList(), rank2Info.getGotoCountryList());
    }

    @ApiOperation(value= "地图飞线(对应collaboration.js的/getLinks)", notes= "地图飞线(对应collaboration" +
            ".js的/getLinks)")
    @ServiceExecuter(description = "地图飞线(对应collaboration.js的/getLinks)")
    @RequestMapping(value = "/getLinks", method = RequestMethod.POST)
    @Audit(desc="地图飞线(对应collaboration.js的/getLinks)")
    public LinksInfoReponse linksQuery() {
        LinksInfoDTO linksInfo = collaborationService.linksQuery();
        return new LinksInfoReponse(linksInfo.getTimeList(), linksInfo.getTimeListObj());
    }

    @ApiOperation(value= "国家列表(对应collaboration.js的/getCountryNum)", notes= "国家列表(对应collaboration.js的/getCountryNum)")
    @ServiceExecuter(description = "国家列表(对应collaboration.js的/getCountryNum)")
    @RequestMapping(value = "/getCountryNum", method = RequestMethod.POST)
    @Audit(desc="国家列表(对应collaboration.js的/getCountryNum)")
    public CountryNumResponse countryNumQuery() {
        Map<String, Map<String, Integer>> result = collaborationService.countryNumQuery();
        return new CountryNumResponse(result);
    }

    @ApiOperation(value= "对应collaboration.js的/goAnalyse", notes= "对应collaboration.js的/goAnalyse")
    @ServiceExecuter(description = "对应collaboration.js的/goAnalyse")
    @RequestMapping(value = "/goAnalyse", method = RequestMethod.POST)
    @Audit(desc="对应collaboration.js的/goAnalyse")
    public GoAnalyseResponse goAnalyseQuery(@Valid GoAnalyseRequest request) throws RestError {
        GoAnalyseQueryDO goAnalyseQueryDO = GoAnalyseRequestConverter.INSTANCE.domain2dto(request);
        GoAnalyseInfoDTO goAnalyseInfo = collaborationService.goAnalyseQuery(goAnalyseQueryDO);
        return GoAnalyseInfoConverter.INSTANCE.domain2dto(goAnalyseInfo);
    }

    @ApiOperation(value= "对应collaboration.js的/comeAnalyse", notes= "对应collaboration.js的/comeAnalyse")
    @ServiceExecuter(description = "对应collaboration.js的/comeAnalyse")
    @RequestMapping(value = "/comeAnalyse", method = RequestMethod.POST)
    @Audit(desc="对应collaboration.js的/comeAnalyse")
    public ComeAnalyseResponse comeAnalyseQuery(@Valid ComeAnalyseRequest request) throws RestError {
        ComeAnalyseQueryDO comeAnalyseQueryDO = ComeAnalyseRequestConverter.INSTANCE.domain2dto
                (request);
        ComeAnalyseInfoDTO comeAnalyseInfo = collaborationService.comeAnalyseQuery
                (comeAnalyseQueryDO);
        return ComeAnalyseInfoConverter.INSTANCE.domain2dto(comeAnalyseInfo);
    }
}
