/**
 * @(#)ResponseInfo.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import java.io.Serializable;
import javax.annotation.Nullable;
import lombok.Data;

/**
 * information of response.
 *
 * @author Fast Retailing
 * @version $Revision$
 */
@Data
public class ResponseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * status code.
     */
    private int statusCode;

    /**
     * response time.
     */
    private long responseTime;

    /**
     * response.
     */
    @Nullable
    private Object response;

    /**
     * time.
     */
    private String time;
}
