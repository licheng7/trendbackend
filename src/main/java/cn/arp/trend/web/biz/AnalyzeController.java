package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DTO.AnalyzeInfoDTO;
import cn.arp.trend.data.model.response.AnalyzeResponse;
import cn.arp.trend.service.biz.AnalyzeService;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:下午3:42
 **/
@Api(value="analyze",tags={"对应宏观部分analyze.js(数据结构一致，部分数据不一致，待查)"})
@RestController
@RequestMapping(value = "/analyze")
public class AnalyzeController extends BaseController {

    @Resource
    private AnalyzeService analyzeService;

    /**
     * 对应analyze.js的/
     * 数据结构一致，部分数据不一致，待查
     * @return
     */
    @ApiOperation(value= "对应analyze.js的/", notes= "对应analyze.js的/")
    @ServiceExecuter(description = "对应analyze.js的/")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @Audit(desc="对应analyze.js的/")
    public AnalyzeResponse analyzeQuery() {
        AnalyzeInfoDTO analyzeInfo = analyzeService.query();
        AnalyzeResponse response = new AnalyzeResponse();
        response.setAll(analyzeInfo.getAll());
        response.setFieldMap(analyzeInfo.getFieldMap());
        return response;
    }
}
