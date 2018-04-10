package sha.framework.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sha.framework.util.LogCommonUtil;


@Aspect
@Component
public class PerformanceLogInterceptor {
	
	@Autowired
	private LogCommonUtil log;
	
	@Before("execution(* sha.work..*.*(..))")
	public void invokeBefore(JoinPoint jp) throws Throwable {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(jp.getTarget().getClass() + "#"
					+ jp.getSignature().getName());
		sb.append("]");
		log.info("性能ログ処理開始", sb.toString());
	}
	
	@After("execution(* sha.work..*.*(..))")
	public void invokeAfter(JoinPoint jp) throws Throwable {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(jp.getTarget().getClass() + "#"
					+ jp.getSignature().getName());
		sb.append("]");
		log.info("性能ログ処理終了", sb.toString());
	}

	
//	@Before("@annotation(org.springframework.transaction.annotation.Transactional)")
//	public void invokeServerBefore(JoinPoint jp) throws Throwable {
//		LogUtil.writeParamterLog("サービスパラメータ", jp);
//	}
//
//	@Around("@annotation(org.springframework.transaction.annotation.Transactional)")
//	public Object invokeServerAfter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		Object ret = null;
//		try {
//			ret = proceedingJoinPoint.proceed();
//		}finally {
//			LogUtil.writeLog(LogUtil.INFO_LEVEL, "サービスレスポンスデータ", ret.toString());
//		}
//		return ret;
//	}
//	/**
//	 * controller invoke log
//	 * @param proceedingJoinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.nec.jp.commercepf.example.controller.*.*(..))")
//	public Object invokeController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		return invoke("controller", proceedingJoinPoint);
//	}
//	
//	/**
//	 * services invoke log
//	 * @param proceedingJoinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.nec.jp.commercepf.framework.service.*.*(..))")
//	public Object invokeServices(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		return invoke("service", proceedingJoinPoint);
//	}
//	
//	/**
//	 * services invoke log
//	 * @param proceedingJoinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.nec.jp.commercepf.example.service.*.*(..))")
//	public Object invokeServicesImp(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		return invoke("serviceImp", proceedingJoinPoint);
//	}
//	
//	/**
//	 * services invoke log
//	 * @param proceedingJoinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.nec.jp.commercepf.framework.data.*.*(..))")
//	public Object invokeData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		return invoke("data", proceedingJoinPoint);
//	}
//	
//	/**
//	 * services invoke log
//	 * @param proceedingJoinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.nec.jp.commercepf.example.mapper.*.*(..))")
//	public Object invokeMapper(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		return invoke("mapper", proceedingJoinPoint);
//	}
	
	
	
//	@Before("execution(* com.nec.jp.commercepf.example..*.*(..))")
//	public void invokeBefore(JoinPoint jp) throws Throwable {
//		LogUtil.invokeLog(this.getClass(), "性能ログ処理開始", jp);
//		LogUtil.invokeParamterLog(this.getClass(), jp);
//	}
//	
//	@After("execution(* com.nec.jp.commercepf.example..*.*(..))")
//	public void invokeAfter(JoinPoint jp) throws Throwable {
//		LogUtil.invokeLog(this.getClass(), "性能ログ処理終了", jp);
//	}
//	/**
//	 * services invoke log
//	 * @param proceedingJoinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.nec.jp.commercepf.framework.util..*.*(..))")
//	public Object invokeCommon(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		LogUtil.invokeLog(this.getClass(), "common", proceedingJoinPoint);
//		return proceedingJoinPoint.proceed();
//	}
}
