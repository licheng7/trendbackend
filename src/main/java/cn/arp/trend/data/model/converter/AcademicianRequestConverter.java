package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DO.AcademicianQueryDO;
import cn.arp.trend.data.model.request.AcademicianQueryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午11:17
 **/
@Mapper
public interface AcademicianRequestConverter {

    AcademicianRequestConverter INSTANCE = Mappers.getMapper(AcademicianRequestConverter.class);

    @Mappings({
            @Mapping(source = "affiliationIds", target = "affiliationIds"),
            @Mapping(source = "afieldNameZky", target = "afieldNameZky"),
            @Mapping(source = "fieldNameGcy", target = "fieldNameGcy")
    })
    AcademicianQueryDO domain2dto(AcademicianQueryRequest academicianQueryRequest);

    List<AcademicianQueryDO> domain2dto(List<AcademicianQueryRequest> orgInfoQueryRequests);
}
