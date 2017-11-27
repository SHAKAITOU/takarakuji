/**
 * @(#)RequestInfo.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import lombok.Data;

/**
 * RequestInfo.
 * the information of request
 *
 * @author Fast Retailing
 * @version $Revision$
 */

@Data
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * method.
     */
    private String method;

    /**
     * request header.
     */
    private HttpHeaders header;

    /**
     * token.
     */
    private String token;

    /**
     * query.
     */
    @Nullable
    private Map<String, Object> query = new HashMap<>();

    /**
     * trace id.
     */
    private String traceId;
}
