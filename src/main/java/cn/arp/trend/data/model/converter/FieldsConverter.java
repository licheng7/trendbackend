package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.AcademicianInfoDTO;
import cn.arp.trend.data.model.response.FieldsResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午12:37
 **/
@Mapper
public interface FieldsConverter {

    FieldsConverter INSTANCE = Mappers.getMapper(FieldsConverter.class);

    @Mappings({
            @Mapping(source = "fieldsZKY", target = "fieldsZKY"),
            @Mapping(source = "fieldsGCY", target = "fieldsGCY")
    })
    FieldsResult domain2dto(AcademicianInfoDTO academicianInfo);

    List<FieldsResult> domain2dto(List<AcademicianInfoDTO> academicianInfoList);
}
