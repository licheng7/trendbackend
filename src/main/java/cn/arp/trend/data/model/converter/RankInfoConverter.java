package cn.arp.trend.data.model.converter;

import cn.arp.trend.data.model.DTO.RankInfoDTO;
import cn.arp.trend.data.model.response.RankInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/7
 * Time:上午1:04
 **/
@Mapper
public interface RankInfoConverter {

    RankInfoConverter INSTANCE = Mappers.getMapper(RankInfoConverter.class);

    @Mappings({
            @Mapping(source = "goCountryNum", target = "goCountryNum"),
            @Mapping(source = "goCountryPeopleNum", target = "goCountryPeopleNum"),
            @Mapping(source = "goydylCountryNum", target = "goydylCountryNum"),
            @Mapping(source = "goydylCountryPeopleNum", target = "goydylCountryPeopleNum"),
            @Mapping(source = "comeCountryNum", target = "comeCountryNum"),
            @Mapping(source = "comeCountryPeopleNum", target = "comeCountryPeopleNum"),
            @Mapping(source = "comeydylCountryNum", target = "comeydylCountryNum"),
            @Mapping(source = "comeydylCountryPeopleNum", target = "comeydylCountryPeopleNum")
    })
    RankInfoResponse domain2dto(RankInfoDTO rankInfo);

    List<RankInfoResponse> domain2dto(List<RankInfoDTO> rankInfoList);
}
