package cn.arp.trend.web.biz;

import cn.arp.trend.service.biz.DetailPaperService;
import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/13
 * Time:下午2:38
 **/
@Api(value="detailPaper",tags={"对应宏观部分detailPaper.js"})
@RestController
@RequestMapping(value = "/detail/paper")
public class DetailPaperController extends BaseController {

    @Resource
    private DetailPaperService detailPaperService;

    /*@ApiOperation(value= "detailPaper.js对应的/SCI", notes= "detailPaper.js对应的/SCI")
    @ServiceExecuter(description = "detailPaper.js对应的/SCI")
    @RequestMapping(value = "/sci", method = RequestMethod.POST)
    @Audit(desc="detailPaper.js对应的/SCI")
    public PaperSciResponse sciQuery(@RequestBody @Validated DetailPaperRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        DetailPaperQueryDO detailPaperQuery = DetailPaperQueryConverter.INSTANCE.domain2dto
                (request);
        PaperSciInfoDTO paperSciInfo = detailPaperService.paperSciQuery(detailPaperQuery);
        return null;
    }*/
}
