/**
 * @(#)ResponseLogInfo.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import java.io.Serializable;
import lombok.Data;

/**
 * ResponseLogInfo.
 * log information of response
 *
 * @author Fast Retailing
 * @version $Revision$
 */
@Data
public class ResponseLogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * response info.
     */
    private ResponseInfo res = new ResponseInfo();
}
