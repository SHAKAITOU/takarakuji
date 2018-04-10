
package sha.framework.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;
import sha.framework.util.JsonUtility;

/**
 * <pre>
 * APIRequestResponseLoggingFilter<br>
 *     All requests will walk through this filter.<br>
 *     Request and response information will be logged.
 *
 * </pre>
 * @author Fast Retailing
 * @version $Revision$
 */
@Slf4j
public class ApiRequestResponseLoggingFilter extends CommonsRequestLoggingFilter {

    /**
     * key of token in request header.
     */
    private static final String KEY_TOKEN = "Postman-Token";

    /**
     * the attribute name of request id in log.
     */
    public static final String LOG_ATTRIBUTE_REQUEST_ID = "requestId";

    /**
     * the attribute name of request in log.
     */
    public static final String LOG_ATTRIBUTE_REQ = "req";

    /**
     * the attribute name of response in log.
     */
    public static final String LOG_ATTRIBUTE_RES = "res";

    /**
     * message.
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * message id (start).
     */
    private static final String MSG_REQUEST_START = "i.common.log.request.start";

    /**
     * message id (end).
     */
    private static final String MSG_REQUEST_END = "i.common.log.request.end";
    
    /**
     * message id (end).
     */
    private static final String MSG_RESPONSE_END = "i.common.log.response.end";

    /**
     * the name of api service.
     */
    @Value("${platform.name.full}")
    private String apiServiceName;

    /**
     * the version of aws.
     */
    @Value("${version.aws}")
    private String versionAws;

    /**
     * Do request's filter.<br>
     *     This method was used to log response.And request will be logged in super class.
     *
     * @param request request
     * @param response response
     * @param filterChain filter chain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected final void doFilterInternal(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain filterChain)
            throws ServletException, IOException {
        String hostName = InetAddress.getLocalHost().getHostName();
        MDC.put(MdcKeyEnum.HOST_NAME.getKey(), hostName);
        String[] traceIds = getTraceIdFromRequest(request);
        MDC.put(MdcKeyEnum.AMAZON_TRACE_ID.getKey(), traceIds[1]);
        MDC.put(
                MdcKeyEnum.REQUEST_ID.getKey(),
                StringUtils.join(hostName, "/", traceIds[0])
        );
        MDC.put(MdcKeyEnum.ATTRIBUTE_SERVICE.getKey(), apiServiceName);
        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;
        if (isIncludePayload() && isFirstRequest
                && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request);
        }
        HttpServletResponse responseToUse = response;
        if (isIncludePayload() && !isAsyncDispatch(request)
                && !(response instanceof ContentCachingResponseWrapper)) {
            responseToUse = new ContentCachingResponseWrapper(response);
        }
        // response message
        // start instant
        Instant startInstant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        ResponseLogInfo responseLogInfo = null;
        try {
            super.doFilterInternal(requestToUse, responseToUse, filterChain);
            // calculate the execute times
            Instant endInstant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
            long responseTime = ChronoUnit.MILLIS.between(startInstant, endInstant);
            responseLogInfo = getResponseMessage(responseToUse, responseTime);
        } finally {
            if (!isAsyncStarted(request)) {
                Map<String, Object> logMap = new LinkedHashMap<>();
                logMap.put(LOG_ATTRIBUTE_RES, responseLogInfo);
                
                if (log.isDebugEnabled()) {
	                log.info(messageSource.getMessage(
	                        MSG_RESPONSE_END, new Object[]{}, Locale.getDefault()), logMap);
                } else {
                	log.info(messageSource.getMessage(
	                        MSG_RESPONSE_END, new Object[]{}, Locale.getDefault()));
                }
            }
        }
    }

    /**
     * Writes a log message before the request is processed.
     *
     * @param request request
     * @param message message content
     */
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        outputRequestMessage(message, MSG_REQUEST_START);
    }

    /**
     * Writes a log message after the request is processed.
     *
     * @param request request
     * @param message message content
     */
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        outputRequestMessage(message, MSG_REQUEST_END);
    }

    /**
     * Output the message of request.
     * @param message message
     * @param messageCode the message code
     */
    private void outputRequestMessage(String message, String messageCode) {
        try {
            RequestLogInfo requestLogInfo = JsonUtility.toObject(message, RequestLogInfo.class);
            Map<String, Object> logMap = new LinkedHashMap<>();
            logMap.put(LOG_ATTRIBUTE_REQUEST_ID, requestLogInfo.getRequestId());
            logMap.put(LOG_ATTRIBUTE_REQ, requestLogInfo.getReq());
            log.info(messageSource.getMessage(
                    messageCode, new Object[]{}, Locale.getDefault()), logMap);
        } catch (IOException e) {
            // shouldn't really happen
        }
    }

    /**
     * To extract information from response.
     * @param response response
     * @param responseTime response time
     * @return the response's information for logger.
     * @throws IOException IOException
     */
    private ResponseLogInfo getResponseMessage(HttpServletResponse response,
                                      long responseTime) throws IOException {
        ResponseLogInfo responseLogInfo = new ResponseLogInfo();

        responseLogInfo.setResponseTime(responseTime);
        responseLogInfo.setTime(
        		LocalDateTime.now().toInstant(ZoneOffset.UTC).toString());
        responseLogInfo.setStatusCode(response.getStatus());

        String contentType = response.getContentType();
        // payload output flag
        boolean isOutputPayLoad = true;
        // disable the  payload output when response is a picture or javascript or html or css
//        if (contentType != null
//                && (contentType.contains("image")
//                    || contentType.equalsIgnoreCase("application/javascript")
//                    || contentType.equalsIgnoreCase("text/html")
//                    || contentType.equalsIgnoreCase("text/css"))) {
//            isOutputPayLoad = false;
//        }
        String payload = null;
        if (isIncludePayload()) {
            ContentCachingResponseWrapper wrapper =
                    WebUtils.getNativeResponse(
                            response, ContentCachingResponseWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                // payload is exists and payload output is enable
                if (buf.length > 0 && isOutputPayLoad) {
                    try {
                        payload = new String(buf, wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException ex) {
                        payload = "\"[unknown]\"";
                    }
                }
                wrapper.copyBodyToResponse();
            }
        }
        if (payload != null) {
            responseLogInfo.setResponse(payload);
        }
        return responseLogInfo;
    }

    /**
     *  Create log message.
     * @param request request
     * @param prefix prefix
     * @param suffix  suffix
     * @return message
     */
    @Override
    protected final String createMessage(HttpServletRequest request,
                                         String prefix, String suffix) {
        RequestLogInfo requestLogInfo = new RequestLogInfo();

        
        // request id
        requestLogInfo.setRequestId(MDC.get(MdcKeyEnum.REQUEST_ID.getKey()));


        RequestInfo requestInfo = requestLogInfo.getReq();
        requestInfo.setParameterMap(getRequestParameterMap(request));
        
        requestLogInfo.setReq(requestInfo);
        // method
        requestInfo.setMethod(request.getMethod());
        // header
        if (this.isIncludeHeaders()) {
            HttpHeaders httpHeaders =
                    new ServletServerHttpRequest(request).getHeaders();
            // remove token info from header
            if (httpHeaders.containsKey(KEY_TOKEN)) {
                httpHeaders.remove(KEY_TOKEN);
            }
            // remove trace id from header
            if (httpHeaders.containsKey(MdcKeyEnum.AMAZON_TRACE_ID.getKey())) {
                httpHeaders.remove(MdcKeyEnum.AMAZON_TRACE_ID.getKey());
            }
            // remove hostname info from header
            if (httpHeaders.containsKey(HttpHeaders.HOST)) {
                httpHeaders.remove(HttpHeaders.HOST);
            }
            requestInfo.setHeader(httpHeaders);
        }
        // token
        requestInfo.setToken(request.getHeader(KEY_TOKEN));

        // query
        if (this.isIncludeQueryString()) {
            requestInfo.setQuery(request.getQueryString());
        }
        // trace id
        requestInfo.setTraceId(MDC.get(MdcKeyEnum.AMAZON_TRACE_ID.getKey()));
        String result = null;
        result = JsonUtility.toJson(requestLogInfo);
        return result;
    }

    /**
     * get trace id from request.
     * @param request request
     * @return the trace id array [0:Self; 1:Root]
     */
    private String[] getTraceIdFromRequest(HttpServletRequest request) {
        String requestTraceId = request.getHeader(MdcKeyEnum.AMAZON_TRACE_ID.getKey());
        String[] traceIds = new String[]{null, null};

        if (StringUtils.isNotEmpty(requestTraceId)) {
            Arrays.stream(requestTraceId.split(";"))
                    .forEach(traceInfo -> {
                        if (traceInfo.contains("Root=")) {
                            traceIds[1] = getValueFromKeyEqualValueString(traceInfo);
                        } else if (traceInfo.contains("Self=")) {
                            traceIds[0] = getValueFromKeyEqualValueString(traceInfo);
                        }
                    });
        }
        if (StringUtils.isEmpty(traceIds[1])) {
            // when root trace id is not exists, generate the trace id
            traceIds[1] = StringUtils.join(versionAws,
                    "-",
                    LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                    "-",
                    UUID.randomUUID().toString());
        }
        if (StringUtils.isEmpty(traceIds[0])) {
            // when self trace is not exists , set the value of root trace
            traceIds[0] = traceIds[1];
        }
        return traceIds;
    }

    /**
     * get the value from the string of format key=value.
     * @param keyAndValueString the string of format key=value
     * @return value
     */
    private String getValueFromKeyEqualValueString(String keyAndValueString) {
        return keyAndValueString
                .substring(keyAndValueString.indexOf("=") + 1);
    }

    /**
     * check the log output enable flag.
     * @param request request
     * @return check result
     */
    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isInfoEnabled();
    }
    
    private Map<String, String> getRequestParameterMap(HttpServletRequest request) {
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        Map<String, String> parameterMap = new HashMap<String, String>();
        requestParameterMap.forEach((key, value) -> {
            parameterMap.put(key, Arrays.toString(value));
        });
        return parameterMap;
    }
}
