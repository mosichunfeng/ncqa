package cn.neusoft.xuxiao.aop;

import cn.neusoft.xuxiao.AbstractLogService;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Aspect
@Component
public class WebRequestLogAop {


    @Autowired(required = false)
    private AbstractLogService abstractLogService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void controllerMethodPointcut() {
    }


    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint joinPoint) {
        Object reult = null;
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String ip = request.getRemoteAddr();
            String className =  joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            Map<String, String[]> paramsMap = request.getParameterMap();

            // 记录下请求内容
            logger.info("URL : " + url);
            logger.info("HTTP_METHOD : " + method);
            logger.info("IP : " + ip);
            logger.info("CLASS_METHOD : " +className);
            logger.info("ARGS : " + JSON.toJSONString(paramsMap));
            if(null!=abstractLogService){
                abstractLogService.saveRequestLog(AbstractLogService.LogType.CONTROLLER,url,method,ip,className,paramsMap);
            }
            reult = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return reult;

    }
}