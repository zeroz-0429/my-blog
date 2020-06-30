package com.zz.my.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志测试切面类：拦截web包下的所有类的所有方法，在控制台和日志文件输出请求路径，ip地址，方法以及参数。
 * ClassName: LogAspect
 * Description: <br/>
 * date: 2020/6/22 20:00
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.zz.my.blog.web.*.*(..))")
    public void log(){};

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求路径
        String url = request.getRequestURL().toString();
        //获取ip地址
        String ip = request.getRemoteAddr();
        //获取请求方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request: {}",requestLog);
    }

    @After("log()")
    public void doAfter(){
//        logger.info("-----------doAfter----------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterResult(Object result){
        logger.info("Result: {}",result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
