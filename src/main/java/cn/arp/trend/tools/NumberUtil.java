package cn.arp.trend.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/11/6
 * Time:下午6:08
 **/
public class NumberUtil {

    public static BigDecimal doubleFormat(double originNumber) {
        return new BigDecimal(originNumber).setScale(2, RoundingMode.UP);
    }

}
