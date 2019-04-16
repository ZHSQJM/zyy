package com.kinglian.screeninquiry.model.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/16 09:26
 * @package: com.kinglian.screeninquiry.model.Vo
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PayWeiXinVo {

    /**
     *    result.put("appId", map.get("appid"));
     *             result.put("nonceStr", map.get("nonce_str"));
     *             result.put("package", "prepay_id="+map.get("prepay_id"));
     *             result.put("timeStamp", String.valueOf(times));
     */
    private String paySign;

    private  String  nonceStr;

    @JsonProperty(value = "package")
    private String package1;

    private  String timeStamp;
}
