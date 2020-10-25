package cn.arp.trend.web.biz;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.data.model.DO.PaperHCAuthorsQueryDO;
import cn.arp.trend.data.model.DO.PaperSciQueryDO;
import cn.arp.trend.data.model.DTO.PaperHCAuthorsInfoDTO;
import cn.arp.trend.data.model.DTO.PaperSciInfoDTO;
import cn.arp.trend.data.model.converter.PaperHCAuthorsQueryConverter;
import cn.arp.trend.data.model.converter.PaperSciInfoConverter;
import cn.arp.trend.data.model.request.DetailPaperSciRequest;
import cn.arp.trend.data.model.request.PaperHCAuthorsRequest;
import cn.arp.trend.data.model.response.PaperHCAuthorsResponse;
import cn.arp.trend.data.model.response.PaperSciResponse;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.biz.DetailPaperService;
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
@Api(value="detailPaper",tags={"对应宏观部分detailPaper.js"})
@RestController
@RequestMapping(value = "/detail/paper")
public class DetailPaperController extends BaseController {

    @Resource
    private DetailPaperService detailPaperService;

    @ApiOperation(value= "detailPaper.js对应的/SCI", notes= "detailPaper.js对应的/SCI")
    @ServiceExecuter(description = "detailPaper.js对应的/SCI")
    @RequestMapping(value = "/sci", method = RequestMethod.POST)
    @Audit(desc="detailPaper.js对应的/SCI")
    public PaperSciResponse sciQuery(@RequestBody @Validated DetailPaperSciRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        PaperSciQueryDO detailPaperQuery = new PaperSciQueryDO(
                request.getStartYear(),
                request.getEndYear(),
                request.getAffiliation(),
                request.getFields()
        );
        PaperSciInfoDTO paperSciInfo = detailPaperService.paperSciQuery(detailPaperQuery);
        return PaperSciInfoConverter.INSTANCE.domain2dto(paperSciInfo);
    }

    @ApiOperation(value= "detailPaper.js对应的/HCAuthors", notes= "detailPaper.js对应的/HCAuthors")
    @ServiceExecuter(description = "detailPaper.js对应的/HCAuthors")
    @RequestMapping(value = "/HCAuthors", method = RequestMethod.POST)
    @Audit(desc="detailPaper.js对应的/HCAuthors")
    public PaperHCAuthorsResponse hCAuthorsQuery(@RequestBody @Validated PaperHCAuthorsRequest request, BindingResult
            bindingResult) throws RestError {
        validData(bindingResult);
        PaperHCAuthorsQueryDO detailPaperQuery = PaperHCAuthorsQueryConverter.INSTANCE.domain2dto(request);
        PaperHCAuthorsInfoDTO paperHCAuthorsInfo = detailPaperService.hCAuthorsQuery(detailPaperQuery);
        return new PaperHCAuthorsResponse(
                paperHCAuthorsInfo.getClassify(),
                paperHCAuthorsInfo.getFields(),
                paperHCAuthorsInfo.getNewData(),
                paperHCAuthorsInfo.getTopAuthors(),
                paperHCAuthorsInfo.getYear(),
                paperHCAuthorsInfo.getUpdateTime()
        );
    }
}
