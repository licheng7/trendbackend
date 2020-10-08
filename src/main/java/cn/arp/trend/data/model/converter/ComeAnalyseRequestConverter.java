package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DO.ComeAnalyseQueryDO;
import cn.arp.trend.data.model.request.ComeAnalyseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午12:26
 **/
@Mapper
public interface ComeAnalyseRequestConverter {

    ComeAnalyseRequestConverter INSTANCE = Mappers.getMapper(ComeAnalyseRequestConverter.class);

    @Mappings({

    })
    ComeAnalyseQueryDO domain2dto(ComeAnalyseRequest comeAnalyseRequest);

    List<ComeAnalyseQueryDO> domain2dto(List<ComeAnalyseRequest> comeAnalyseRequests);
}
