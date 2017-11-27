/**
 * @(#)MyBatisLogInterceptor.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * MyBatisLogInterceptor.
 *
 * @author Fast Retailing
 * @version $Revision$
 */
@Intercepts({@Signature(type = StatementHandler.class,
        method = "query",
        args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class,
        method = "update",
        args = {Statement.class})})
@Component
@Slf4j
public class MyBatisLogInterceptor implements Interceptor {

    /**
     * message.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * message id (sql).
     */
    private static final String MSG_SQL = "e.common.log.sql";

    /**
     * message id (parameter).
     */
    private static final String MSG_SQL_PARAM = "e.common.log.sql.param";

    /**
     * message id (state).
     */
    private static final String MSG_SQL_STATE = "e.common.log.sql.state";

    /**
     * message id (times).
     */
    private static final String MSG_SQL_TIMES = "e.common.log.sql.times";

    /**
     * mybatis StatementHandler Interceptor.<br>
     * When error occurred in sql executing, This handler will log sql and parameters.
     * 
     * @param invocation Invocation
     * @return sql execute result
     * @throws Throwable Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!(invocation.getTarget() instanceof StatementHandler)) {
            return invocation.proceed();
        } else {
            StatementHandler handler = (StatementHandler) invocation.getTarget();
            // start timestamp
            long timestamp = System.currentTimeMillis();
            try {
                return invocation.proceed();
            } catch (InvocationTargetException e) {
                // write the error log
                writeErrorLog(handler, timestamp, e);
                throw e;
            }
        }
    }

    /**
     * write the sql log.
     * @param handler handler
     * @param timestamp start timestamp
     * @param e sql exception
     */
    private void writeErrorLog(StatementHandler handler,
            long timestamp,
            InvocationTargetException ex) {
        if (ex.getTargetException() instanceof SQLException) {
            // sql
            log.error(messageSource.getMessage(
                    MSG_SQL,
                    new Object[]{handler.getBoundSql().getSql()},
                    Locale.getDefault()));
            // parameter
            log.error(messageSource.getMessage(
                    MSG_SQL_PARAM,
                    new Object[]{handler.getBoundSql().getParameterObject()},
                    Locale.getDefault()));
            // state
            log.error(messageSource.getMessage(
                    MSG_SQL_STATE,
                    new Object[]{((SQLException) ex.getTargetException())
                            .getSQLState()},
                    Locale.getDefault()));
            // calculate the execute times
            long times = (System.currentTimeMillis() - timestamp);
            // times
            log.error(messageSource.getMessage(
                    MSG_SQL_TIMES,
                    new Object[]{times},
                    Locale.getDefault()));
        } else {
            // do nothing
        }
    }

    /**
     * plugin setting.
     * 
     * @param target wrap target
     * @return wrap result
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * properties setting.
     * 
     * @param properties Properties
     */
    @Override
    public void setProperties(Properties properties) {
        // ignore
    }
}
