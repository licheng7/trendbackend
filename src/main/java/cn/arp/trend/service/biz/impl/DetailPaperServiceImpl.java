package cn.arp.trend.service.biz.impl;

import cn.arp.trend.data.model.DO.DetailPaperQueryDO;
import cn.arp.trend.data.model.DTO.PaperSciInfoDTO;
import cn.arp.trend.repository.biz.manual.StatCasPaperManualMapper;
import cn.arp.trend.service.biz.DetailPaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/22
 * Time:上午10:51
 **/
@Service
public class DetailPaperServiceImpl implements DetailPaperService {

    @Resource
    private StatCasPaperManualMapper statCasPaperManualMapper;

    @Override
    public PaperSciInfoDTO paperSciQuery(DetailPaperQueryDO query) {

        List<Map<String, Object>> queryResult1
                = statCasPaperManualMapper.querySci1(query);

        List<Map<String, Object>> queryResult2
                = statCasPaperManualMapper.querySci2(query);

        return null;
    }
}
