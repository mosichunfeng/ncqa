package cn.neusoft.xuxiao;


import cn.neusoft.xuxiao.annotation.BusinessLog;
import cn.neusoft.xuxiao.mybatis.inteface.EnumIdentifiable;

import java.util.Map;

public  interface  AbstractLogService {

    /**
     * 保存业务日志
     * @param businessLog
     */
    void saveBusinessLog(BusinessLog businessLog, String ip, LogType logType);


    /**
     * 保存请求日志
     * @param url
     * @param method
     * @param ip
     * @param className
     * @param paramsMap
     */
    void saveRequestLog(LogType type, String url, String method, String ip, String className, Map<String, String[]> paramsMap);


    /**
     * 异常日志
     * @param exception
     * @param log
     * @param e
     */
    void saveExceptionLog(LogType exception, BusinessLog log, Throwable e);


    public enum LogType implements EnumIdentifiable {
        CONTROLLER("请求日志"),SERVICE("业务日志"), EXCEPTION("异常日志");

        public  String desc;

        LogType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

}
