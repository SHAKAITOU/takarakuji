/**
 * @(#)ServiceLogInterceptor.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * ServiceLogInterceptor.
 *
 * @author Fast Retailing
 * @version $Revision$
 */
@Aspect
@Component
@Slf4j
public class ServiceLogInterceptor {


    /**
     * message.
     */
    @Autowired
    private MessageSource msg;

    /**
     * message id (start).
     */
    private static final String MSG_START = "i.common.log.service.start";

    /**
     * message id (end).
     */
    private static final String MSG_END = "i.common.log.service.end";

    /**
     * output the method's log of service.
     *
     * @param joinPoint join point
     * @return the result of service's method
     * @throws Throwable Throwable
     */
    @Around("@within(org.springframework.stereotype.Service)")
    public Object serviceLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // get the executing class object
        Class<?> target = joinPoint.getTarget().getClass();
        // write the start log
        writeStartLog(joinPoint.getArgs(),
                target.getName(),
                joinPoint.getSignature().getName());
        // start timestamp
        long timestampStart = System.currentTimeMillis();
        Object result;
        try {
            // execute service method
            result = joinPoint.proceed();
        } finally {
            // write the end log
            writeEndLog(target.getName(),
                    joinPoint.getSignature().getName(),
                    timestampStart);
        }
        return result;
    }

    /**
     * write the start log.
     * @param args the arguments of method
     * @param className the name of class
     * @param methodName the name of method
     */
    private void writeStartLog(Object[] args,
            String className,
            String methodName) {
        // the message of parameters
        List<Object> argList = Arrays.asList(args);
        String parameter = null;
        if (!CollectionUtils.isEmpty(argList)) {
            StringBuilder params = new StringBuilder();
            argList.stream().findFirst()
                .ifPresent(arg -> params.append(arg.toString()));
            argList.stream().skip(1)
                    .forEach(arg -> params
                    .append(",")
                    .append(arg));
            parameter = params.toString();
        } else {
            parameter = StringUtils.EMPTY;
        }
//        log.info(
//                msg.getMessage(
//                        MSG_START,
//                        new Object[] {className, methodName, parameter}));
    }

    /**
     * write the end log.
     * @param className the name of class
     * @param methodName the name of method
     * @param timestampStart the start timestamp
     */
    private void writeEndLog(String className,
            String methodName,
            long timestampStart) {
        // calculate the execute times
        long times = (System.currentTimeMillis() - timestampStart);
        // end log
//        log.info(
//                msg.getMessage(
//                        MSG_END,
//                        new Object[] {
//                                className,
//                                methodName,
//                                times}));
    }
}
