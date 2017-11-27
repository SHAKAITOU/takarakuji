/**
 * @(#)APIRequestResponseLoggingFilter.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;
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

public class ApiRequestResponseLoggingFilter extends CommonsRequestLoggingFilter {

    /**
     * key of token in request header.
     */
    private static final String KEY_TOKEN = "Postman-Token";

    /**
     * key of trace id in request header.
     */
    private static final String KEY_TRACE_ID = "X-Amzn-Trace-Id";

    /**
     * key of host in request header.
     */
    private static final String KEY_HOST = "host";

    /**
     * message.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * message id (response).
     */
    private static final String MSG_RESPONSE = "i.common.log.response";

    /**
     * the name of api service.
     */
    @Value("${api-service.name}")
    private String apiServiceName;

    /**
     * service id.
     */
    private String serviceId = UUID.randomUUID().toString();

    /**
     * trace id.
     */
    private String traceId;

    /**
     * host name.
     */
    private String hostName;

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
        hostName = request.getHeader(KEY_HOST);
        MDC.put("hostname", hostName);
        traceId = getTraceIdFromRequest(request);
        MDC.put("trace_id", traceId);
        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;
        if (isIncludePayload() && isFirstRequest
                && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request);
        } else {
            // do nothing
        }
        HttpServletResponse responseToUse = response;
        if (isIncludePayload() && !isAsyncDispatch(request)
                && !(response instanceof ContentCachingResponseWrapper)) {
            responseToUse = new ContentCachingResponseWrapper(response);
        } else {
            // do nothing
        }
        // response message
        String responseMsg = "";
        // start timestamp
        long timestamp = System.currentTimeMillis();
        try {
            super.doFilterInternal(requestToUse, responseToUse, filterChain);
            // calculate the execute times
            long responseTime = (System.currentTimeMillis() - timestamp);
            responseMsg = messageSource.getMessage(MSG_RESPONSE,
                    new Object[]{
                            getResponseMessage(responseToUse, responseTime)},
                    Locale.getDefault());
        } finally {
            if (!isAsyncStarted(request)) {
                logger.info(responseMsg);
            }
        }
    }

    /**
     * To extract information from response.
     * @param response response
     * @param responseTime response time
     * @return the response's information for logger.
     * @throws IOException IOException
     */
    private String getResponseMessage(HttpServletResponse response,
            long responseTime) throws IOException {
        ResponseLogInfo responseLogInfo = new ResponseLogInfo();

        ResponseInfo responseInfo = responseLogInfo.getRes();
        responseInfo.setResponseTime(responseTime);
        responseInfo.setTime(
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toString());
        responseInfo.setStatusCode(response.getStatus());

        String contentType = response.getContentType();
        // payload output flag
        boolean isOutputPayLoad = true;
        // disable the  payload output when response is a picture or javascript or html or css
        if (contentType != null
                && (contentType.contains("image")
                        || contentType.equalsIgnoreCase("application/javascript")
                        || contentType.equalsIgnoreCase("text/html")
                        || contentType.equalsIgnoreCase("text/css"))) {
            isOutputPayLoad = false;
        }
        String payload = null;
        if (isIncludePayload()) {
            ContentCachingResponseWrapper wrapper =
                    WebUtils.getNativeResponse(
                            response, ContentCachingResponseWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                // payload is exists and payload output is enable
                if (buf.length > 0 && isOutputPayLoad) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    try {
                        payload = new String(buf,
                                0,
                                length,
                                wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException ex) {
                        payload = "\"[unknown]\"";
                    }
                    responseInfo.setResponse(true);
                } else {
                    // do nothing
                }
                wrapper.copyBodyToResponse();
            } else {
                // do nothing
            }
        } else {
            // do nothing
        }
        String responseString = JsonUtility.toJson(responseLogInfo);
        if (payload != null) {
            responseString = responseString.replace(
                    "\"response\":true",
                    StringUtils.join("\"response\":", payload));
        } else {
            // do nothing
        }
        return responseString;
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
            String prefix, String suffix)  {
        RequestLogInfo requestLogInfo = new RequestLogInfo();

        // request id
        requestLogInfo.setRequestId(StringUtils.join(hostName, "/", serviceId));

        // service
        requestLogInfo.setService(apiServiceName);

        RequestInfo requestInfo = requestLogInfo.getReq();
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
            } else {
                // do nothing
            }
            // remove trace id from header
            if (httpHeaders.containsKey(KEY_TRACE_ID)) {
                httpHeaders.remove(KEY_TRACE_ID);
            } else {
                // do nothing
            }
            // remove hostname info from header
            if (httpHeaders.containsKey(KEY_HOST)) {
                httpHeaders.remove(KEY_HOST);
            } else {
                // do nothing
            }
            requestInfo.setHeader(httpHeaders);
        } else {
            // do nothing
        }
        // token
        requestInfo.setToken(request.getHeader(KEY_TOKEN));

        // query
        if (this.isIncludeQueryString()) {
            requestInfo.setQuery(
                    convertQueryStringToMap(request.getQueryString()));
        } else {
            // do nothing
        }
        // trace id
        requestInfo.setTraceId(traceId);

        return StringUtils.join(prefix,
                JsonUtility.toJson(requestLogInfo),
                suffix);
    }

    /**
     * convert the query string in request to map.
     * @param queryString query string in request
     * @return query map
     */
    private Map<String, Object> convertQueryStringToMap(String queryString) {
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isEmpty(queryString)) {
            return result;
        }
        String[] itemList = queryString.split("&");
        Arrays.asList(itemList).stream().forEach(item -> {
            String[] keyAndValue = item.split("=");
            String key = keyAndValue[0];
            String value = null;
            if (keyAndValue.length > 1) {
                value = keyAndValue[1];
            } else {
                // do nothing
            }
            if (key.contains(".")) {
                // if item is class
                putClassMapValue(result, key, value);
            } else {
                result.put(key, value);
            }
        });
        return result;
    }

    /**
     *  set the class map.
     * @param parentMap parent map
     * @param key map key
     * @param value map value
     */
    @SuppressWarnings("unchecked")
    private void putClassMapValue(Map<String, Object> parentMap,
            String key,
            String value) {
        String[] keys = key.split("\\.");
        String primaryKey;
        if (keys.length > 1) {
            // sub key is exist
            primaryKey = keys[0];
            Map<String, Object> classMap = null;
            if (parentMap.containsKey(primaryKey)) {
                classMap = (Map<String, Object>)parentMap.get(primaryKey);
            } else {
                classMap = new HashMap<>();
                parentMap.put(primaryKey, classMap);
            }
            String subKey = key.substring(primaryKey.length() + 1);
            if (StringUtils.isNoneEmpty(subKey)) {
                putClassMapValue(classMap, subKey, value);
            }
        } else {
            // sub key is not exist
            primaryKey = key;
            parentMap.put(primaryKey, value);
        }
    }

    /**
     * get trace id from rquest.
     * @param request request
     * @return trace id
     */
    private String getTraceIdFromRequest(HttpServletRequest request) {
        String requestTraceId = request.getHeader(KEY_TRACE_ID);
        String rootTraceId = null;
        if (StringUtils.isNotEmpty(requestTraceId)) {
            List<String> traceIdList = Arrays
                    .asList(requestTraceId.split(";"))
                    .stream()
                    .filter(traceId -> traceId.contains("Root="))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(traceIdList)) {
                rootTraceId = StringUtils.EMPTY;
            } else {
                rootTraceId = getValueFromKeyEqualValueString(traceIdList.get(0));
            }
        } else {
            rootTraceId = StringUtils.EMPTY;
        }
        return rootTraceId;
    }

    /**
     * get the value from the string of format key=value.
     * @param keyAndValueString the string of format key=value
     * @return value
     */
    private String getValueFromKeyEqualValueString(String keyAndValueString) {
        if (StringUtils.isNotEmpty(keyAndValueString)) {
            if (keyAndValueString.contains("=")) {
                return keyAndValueString
                        .substring(keyAndValueString.indexOf("=") + 1);
            } else {
                return StringUtils.EMPTY;
            }
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isInfoEnabled();
    }

    /**
     * Writes a log message before the request is processed.
     */
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    /**
     * Writes a log message after the request is processed.
     */
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }
}
