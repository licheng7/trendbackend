package cn.arp.trend.service.biz;

import cn.arp.trend.data.model.DTO.AnalyzeInfoDTO;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:下午3:50
 **/
public interface AnalyzeService {

    /**
     * 对应analyze.js的/
     * @return
     */
    AnalyzeInfoDTO query();
}
