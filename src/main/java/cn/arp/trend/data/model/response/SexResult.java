package cn.arp.trend.data.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/29
 * Time:上午2:19
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SexResult implements Serializable {

    private static final long serialVersionUID = -8344339157575146784L;

    private String id;

    private String name;
}
