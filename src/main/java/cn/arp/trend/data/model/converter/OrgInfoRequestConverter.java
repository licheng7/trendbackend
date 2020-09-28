package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DO.OrgInfoQueryDO;
import cn.arp.trend.data.model.request.OrgInfoQueryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午11:33
 **/
@Mapper
public interface OrgInfoRequestConverter {

    OrgInfoRequestConverter INSTANCE = Mappers.getMapper(OrgInfoRequestConverter.class);

    @Mappings({
            @Mapping(source = "affiliationIds", target = "affiliationIds"),
            @Mapping(source = "fieldNames", target = "fieldNames")
    })
    OrgInfoQueryDO domain2dto(OrgInfoQueryRequest orgInfoQueryRequest);

    List<OrgInfoQueryDO> domain2dto(List<OrgInfoQueryRequest> orgInfoQueryRequests);
}
