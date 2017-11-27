/**
 * @(#)RequestLogInfo.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import java.io.Serializable;
import lombok.Data;

/**
 * RequestLogInfo.
 * log information of request
 *
 * @author Fast Retailing
 * @version $Revision$
 */

@Data
public class RequestLogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * request info.
     */
    private RequestInfo req = new RequestInfo();

    /**
     * request id.
     */
    private String requestId;

    /**
     * service.
     */
    private String service;
}
