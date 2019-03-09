package cn.neusoft.xuxiao.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class SMSUtil {

    public static final String APIKEY="192f84208488bc010b583d91868de0bf";

    public static final String URL="https://sms.yunpian.com/v2/sms/single_send.json";
    public static void sendSMS(String text, String mobile){
        RestTemplate restTemplate=new RestTemplate();
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("apikey", APIKEY);
        params.add("text", text);
        params.add("mobile", mobile);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Map> entity = new HttpEntity<>(params,requestHeaders);

        restTemplate.exchange(URL,HttpMethod.POST, entity, String.class);

    }

    public static void main(String[] args) {
        /*String text= MessageFormat.format(TextMessage.SMS_TEMPLATE, "6666");

        SMSUtil.sendSMS(text,"13194873907");
        System.out.println(text);*/
        String str = "(1)adj. relating to or involving general ideas or " +
                "qualities rather than specific people, objects, or actions 笼统的(2)" +
                "adj. difficult to understand 抽象的，深奥的(3)n. a brief written statement of " +
                "the main points or facts in a longer report, speech, etc. 摘要(4)v. to obtain or " +
                "remove from a source 提取(5)v. to draw away the attention of 分散注意力(15)v. to draw away the attention of 意力";
        String[] strs = str.split("\\(\\d+\\)");
        for(String s : strs){
            System.out.println(s);
        }
    }
}
