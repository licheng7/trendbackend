package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DO.GoAnalyseQueryDO;
import cn.arp.trend.data.model.request.GoAnalyseRequest;
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
public interface GoAnalyseRequestConverter {

    GoAnalyseRequestConverter INSTANCE = Mappers.getMapper(GoAnalyseRequestConverter.class);

    @Mappings({

    })
    GoAnalyseQueryDO domain2dto(GoAnalyseRequest goAnalyseRequest);

    List<GoAnalyseQueryDO> domain2dto(List<GoAnalyseRequest> goAnalyseRequest);
}
