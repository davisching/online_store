package pers.dc.ols.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    // AOP REVIEW: 1.前置 2.后置(正常运行之后) 3.环绕 4.异常 5.最终

    public static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    private String className, serviceName;

    // -> AOP 通配符 需要复习
    @Around("execution(* pers.dc.ols.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        className = joinPoint.getTarget().getClass().getName();
        serviceName = joinPoint.getSignature().getName();

        logger.info(getStartStr());

        long startsAt = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeCost = System.currentTimeMillis() - startsAt;

        if (timeCost > 3000)
            logger.error(getEndStr(timeCost));
        else if (timeCost > 2000)
            logger.warn(getEndStr(timeCost));
        else
            logger.info(getEndStr(timeCost));

        return result;
    }

    private String getStartStr() {
        return "\n\n======【" + className + " ￿的 " + serviceName + " 服务】开始 ======";
    }

    private String getEndStr(long timeCost) {
        return "\n======【" + className + " 的 " + serviceName + " 服务】结束，用时 " + timeCost + " 毫秒 ======\n";
    }
}