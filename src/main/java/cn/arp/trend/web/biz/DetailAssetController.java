package cn.arp.trend.web.biz;

import cn.arp.trend.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/12
 * Time:上午12:56
 **/
@Api(value="detailAsset",tags={"对应宏观部分detailAsset.js"})
@RestController
@RequestMapping(value = "/detail/asset")
public class DetailAssetController extends BaseController {

    /*@ApiOperation(value= "对应detailAsset.js/overview", notes= "对应detailAsset.js/overview")
    @ServiceExecuter(description = "对应detailAsset.js/overview")
    @RequestMapping(value = "/overview", method = RequestMethod.POST)
    @Audit(desc="对应detailAsset.js/overview")
    public OverviewResponse overviewQuery(
            @RequestBody @Validated OverviewRequest request, BindingResult bindingResult) throws RestError {
        validData(bindingResult);

        return null;
    }*/
}
