package com.kinglian.screeninquiry.model.dto;
import lombok.Data;

import java.io.Serializable;

/**
 * @author
 */
@Data
public class RequestBaseParam <T> implements Serializable {

    private T body ;

    public T getBody() {
        return body;
    }

    private RequestHeadParam header;

    public  RequestHeadParam getHeader()
    {

        return header;
    }

}
