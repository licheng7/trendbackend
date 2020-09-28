package cn.arp.trend.data.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/28
 * Time:上午12:50
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrgInfoResponse implements Serializable {

    private static final long serialVersionUID = 3127637399912081785L;

    private List<String> fields;

    private List<OrgAndResearchResult> institutions;
}
