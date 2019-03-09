package cn.neusoft.xuxiao.util;//package com.magicbeans.zjgre.cn.neusoft.xuxiao.util;
//
//import com.magicbeans.zjgre.cn.neusoft.xuxiao.entity.Push;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
//public class TaskUtil {
//
//    public static final void pushTask(Push push) throws Exception{
//        SchedulerFactory sf = new StdSchedulerFactory();
//        Scheduler scheduler;
//        scheduler = sf.getScheduler();
//        scheduler.start();
//        String num = CommonUtil.get32UUID();
//        String jobStaName = "Job"+num;
//        JobDetail job = JobBuilder.newJob(RealizeJob.class).withIdentity(jobStaName, "Group1").build();
//        job.getJobDataMap().put("content", push.getContent());
//        job.getJobDataMap().put("title",push.getTitle());
//        String triggerName = "trigger"+num;
//        // 触发时间
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity(triggerName, "Group1")
//                .startAt(push.getStartTime())
//                .build();
//        // 交由Scheduler安排触发
//        scheduler.scheduleJob(job, trigger);
//    }
//}
