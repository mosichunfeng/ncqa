package cn.neusoft.xuxiao.aop;

import cn.neusoft.xuxiao.AbstractLogService;
import cn.neusoft.xuxiao.annotation.BusinessLog;
import com.magicbeans.base.kit.HttpKit;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAop {

    @Autowired(required =  false)
    private AbstractLogService logService;

    @Before("@annotation(log)")
    public void point(BusinessLog log){
        if(null !=logService){
            logService.saveBusinessLog(log, HttpKit.getIp(), AbstractLogService.LogType.SERVICE);
        }
    }


    @AfterThrowing(value = "@annotation(log)", throwing = "e")
    public void afterThrowing(BusinessLog log,Throwable e) {
        logService.saveExceptionLog(AbstractLogService.LogType.EXCEPTION,log,e);
    }

}
