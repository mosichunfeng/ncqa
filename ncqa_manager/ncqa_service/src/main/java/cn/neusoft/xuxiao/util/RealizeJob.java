package cn.neusoft.xuxiao.util;//package com.magicbeans.zjgre.cn.neusoft.xuxiao.util;
//
//import com.magicbeans.base.db.Filter;
////import com.magicbeans.push.PushService;
////import com.magicbeans.push.bean.*;
//import com.magicbeans.zjgre.cn.neusoft.xuxiao.entity.User;
//import com.magicbeans.zjgre.cn.neusoft.xuxiao.service.IUserService;
//import org.quartz.Job;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.cn.neusoft.xuxiao.util.ArrayList;
//import java.cn.neusoft.xuxiao.util.List;
//
//public class RealizeJob implements Job {
//
////    @Autowired
////    private PushService pushService;
//
//    @Autowired
//    private IUserService userService;
//
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
//        String content=(String) data.get("content");
//        String title=(String) data.get("title");
//
//        List<Filter> filters=new ArrayList<>();
//        filters.add(Filter.eq("status",1));
//        filters.add(Filter.isNotNull("jiguang_id"));
//        List<User> users=userService.findList(filters,null);
//
//        /*ClientInfo.ClientInfoBuilder clientInfoBuilder=ClientInfo.builder();
//        //clientInfoBuilder.registerId() 该值没有赋予
//        clientInfoBuilder=clientInfoBuilder.platformType(PlatformType.JPUSH);
//        ClientInfo clientInfo=clientInfoBuilder.build();*/
//        for (User u:users) {
//            Message message=new Message();
//            message.setContent(content);
//            message.setTitle(title);
//            message.setDeviceId(u.getJiguangId());
//            if (u.getDeviceType() == 1) {
//                message.setDeviceType(DeviceType.ios);
//            } else if (u.getDeviceType() == 0) {
//                message.setDeviceType(DeviceType.android);
//            }else {
//                message.setDeviceType(DeviceType.all);
//            }
//            message.setNotificationType(NotificationType.notification_passthrough);
//
//            message.setAppType("member");//
//
////TODO 未验证
//            pushService.broadcastAll(message);
//
//        }
//    }
//}
