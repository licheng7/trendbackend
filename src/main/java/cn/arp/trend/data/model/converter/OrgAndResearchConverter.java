package cn.arp.trend.data.model.converter;

        import cn.arp.trend.data.model.DTO.OrgInfoDTO;
        import cn.arp.trend.data.model.response.OrgAndResearchResult;
        import org.mapstruct.Mapper;
        import org.mapstruct.Mapping;
        import org.mapstruct.Mappings;
        import org.mapstruct.factory.Mappers;

        import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:下午12:21
 **/
@Mapper
public interface OrgAndResearchConverter {

    OrgAndResearchConverter INSTANCE = Mappers.getMapper(OrgAndResearchConverter.class);

    @Mappings({
            @Mapping(source = "jgbh", target = "jgbh"),
            @Mapping(source = "jgmc", target = "jgmc"),
            @Mapping(source = "researchField", target = "researchField")
    })
    OrgAndResearchResult domain2dto(OrgInfoDTO.OrgAndResearchDTO orgAndResearch);

    List<OrgAndResearchResult> domain2dto(List<OrgInfoDTO.OrgAndResearchDTO> orgAndResearches);
}
