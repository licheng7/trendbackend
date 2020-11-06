package cn.arp.trend.web.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.data.model.DO.ComeAnalyseQueryDO;
import cn.arp.trend.data.model.DO.GoAnalyseQueryDO;
import cn.arp.trend.data.model.DTO.ComeAnalyseInfoDTO;
import cn.arp.trend.data.model.DTO.GoAnalyseInfoDTO;
import cn.arp.trend.data.model.DTO.LinksInfoDTO;
import cn.arp.trend.data.model.DTO.Rank2InfoDTO;
import cn.arp.trend.data.model.DTO.RankInfoDTO;
import cn.arp.trend.data.model.converter.ComeAnalyseInfoConverter;
import cn.arp.trend.data.model.converter.ComeAnalyseRequestConverter;
import cn.arp.trend.data.model.converter.GoAnalyseInfoConverter;
import cn.arp.trend.data.model.converter.GoAnalyseRequestConverter;
import cn.arp.trend.data.model.converter.RankInfoConverter;
import cn.arp.trend.data.model.request.ComeAnalyseRequest;
import cn.arp.trend.data.model.request.GoAnalyseRequest;
import cn.arp.trend.data.model.response.ComeAnalyseResponse;
import cn.arp.trend.data.model.response.CountryNumResponse;
import cn.arp.trend.data.model.response.GoAnalyseResponse;
import cn.arp.trend.data.model.response.LinksInfoReponse;
import cn.arp.trend.data.model.response.Rank2InfoResponse;
import cn.arp.trend.data.model.response.RankInfoResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.CollaborationService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@RequirePermission(dataset=true)
public class CollaborationController extends BaseController {

    @Resource
    private CollaborationService collaborationService;

    @ApiOperation(value= "出来访统计信息(对应collaboration.js的/getRank)", notes= "出来访统计信息(对应collaboration.js的/getRank)")
    @ServiceExecuter(description = "出来访统计信息(对应collaboration.js的/getRank)")
    @RequestMapping(value = "/getRank", method = RequestMethod.POST)
    @Audit(desc="出来访人次统计", value="Comparison.Collaboration.Statistics")
    public RankInfoResponse rankQuery() {
        RankInfoDTO rankInfo = collaborationService.rankQuery();
        RankInfoResponse rankInfoResponse = RankInfoConverter.INSTANCE.domain2dto(rankInfo);
        return rankInfoResponse;
    }

    @ApiOperation(value= "单位排名、国家排名(对应collaboration.js的/getRank2)", notes= "单位排名、国家排名(对应collaboration.js的/getRank2)")
    @ServiceExecuter(description = "单位排名、国家排名(对应collaboration.js的/getRank2)")
    @RequestMapping(value = "/getRank2", method = RequestMethod.POST)
    @Audit(desc="出来访单位排名、国家排名", value="Comparison.Collaboration.InstitutionCountryRank")
    public Rank2InfoResponse rankQuery2() {
        Rank2InfoDTO rank2Info = collaborationService.rankQuery2();
        return new Rank2InfoResponse(rank2Info.getGotoUnitList(), rank2Info.getGotoCountryList());
    }

    @ApiOperation(value= "地图飞线(对应collaboration.js的/getLinks)", notes= "地图飞线(对应collaboration" +
            ".js的/getLinks)")
    @ServiceExecuter(description = "地图飞线(对应collaboration.js的/getLinks)")
    @RequestMapping(value = "/getLinks", method = RequestMethod.POST)
    @Audit(desc="出来访地图飞线", value="Comparison.Collaboration.MapLink")
    public LinksInfoReponse linksQuery() {
        LinksInfoDTO linksInfo = collaborationService.linksQuery();
        return new LinksInfoReponse(linksInfo.getTimeList(), linksInfo.getTimeListObj());
    }

    /**
     * 国家列表(对应collaboration.js的/getCountryNum)
     *
     * @return
     */
    @ApiOperation(value= "国家列表(对应collaboration.js的/getCountryNum)",
            notes= "国家列表(对应collaboration.js的/getCountryNum)")
    @ServiceExecuter(description = "国家列表(对应collaboration.js的/getCountryNum)")
    @RequestMapping(value = "/getCountryNum", method = RequestMethod.POST)
    @Audit(desc="各国出来访人次统计", value="Comparison.Collaboration.CountryNumber")
    public CountryNumResponse countryNumQuery() {
        List<List<Map<String, Object>>> result = collaborationService.countryNumQuery();
        return new CountryNumResponse(result);
    }

    @ApiOperation(value= "对应collaboration.js的/goAnalyse", notes= "对应collaboration.js的/goAnalyse")
    @ServiceExecuter(description = "对应collaboration.js的/goAnalyse")
    @RequestMapping(value = "/goAnalyse", method = RequestMethod.POST)
    @Audit(desc="详细的出访数据", value="Collaboration.Go")
    public GoAnalyseResponse goAnalyseQuery(@RequestBody @Validated GoAnalyseRequest request,
                                            BindingResult bindingResult) throws RestError {
        validData(bindingResult);
        GoAnalyseQueryDO goAnalyseQueryDO = GoAnalyseRequestConverter.INSTANCE.domain2dto(request);
        GoAnalyseInfoDTO goAnalyseInfo = collaborationService.goAnalyseQuery(goAnalyseQueryDO);
        return GoAnalyseInfoConverter.INSTANCE.domain2dto(goAnalyseInfo);
    }

    @ApiOperation(value= "对应collaboration.js的/comeAnalyse", notes= "对应collaboration.js的/comeAnalyse")
    @ServiceExecuter(description = "对应collaboration.js的/comeAnalyse")
    @RequestMapping(value = "/comeAnalyse", method = RequestMethod.POST)
    @Audit(desc="详细的来访数据", value="Collaboration.Come")
    public ComeAnalyseResponse comeAnalyseQuery(
            @RequestBody @Validated ComeAnalyseRequest request, BindingResult bindingResult)
            throws RestError {
        validData(bindingResult);
        ComeAnalyseQueryDO comeAnalyseQueryDO =
                ComeAnalyseRequestConverter.INSTANCE.domain2dto(request);
        ComeAnalyseInfoDTO comeAnalyseInfo =
                collaborationService.comeAnalyseQuery(comeAnalyseQueryDO);
        return ComeAnalyseInfoConverter.INSTANCE.domain2dto(comeAnalyseInfo);
    }
}
