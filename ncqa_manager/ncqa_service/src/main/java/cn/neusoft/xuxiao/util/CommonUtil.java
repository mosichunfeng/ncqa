package cn.neusoft.xuxiao.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 通用工具
 * Created by Eric Xie on 2017/2/13 0013.
 */
public class CommonUtil {

    public static boolean isEmpty(Object... args) {
        for (Object arg : args) {
            if (null == arg) {
                return true;
            }
            if (arg instanceof String) {
                if (((String) arg).trim().length() == 0 || "null".equals(arg)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取手机号的后四位
     *
     * @return
     */
    public static String subMobile(String mobile) {
        if (null == mobile || mobile.length() <= 5) {
            return "0000";
        }
        return mobile.substring(mobile.length() - 4);
    }

    /**
     * 替换手机号中间四位
     * @param mobile 手机号
     * @param mark 替换的字符
     * @return 处理后的手机号
     */
    public static String subMobile(String mobile,String mark){
        if(isEmpty(mark,mobile)){
            return "";
        }
        if(mobile.length() != 11){
            return "";
        }
        mobile =  mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
        return mobile;
    }

    /**
     * 订单号生成规则
     *
     * @return
     */
    public static String buildOrderNumber() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmssSSSS");
        return format.format(new Date());
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static String formatCityName(String cityName) {
        if (isEmpty(cityName)) {
            return "";
        }
        String s = cityName.substring(2, cityName.length()).replaceAll(",", "");
        if (s.indexOf("省") < 0) {
            s = s.substring(2, s.length());
        }
        return s;
    }

    /**
     * 获取验证码
     * @param count
     * @return
     */
    public static String createRandomCode(int count) {
        if(count <= 0){
            return "";
        }
        String vcode = "";
        for (int i = 0; i < count; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }

    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static JSONObject doChangeJson(String url) throws Exception {
        JSONObject jsonObject = null;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (!CommonUtil.isEmpty(resp.getBody())) {
            jsonObject = JSONObject.parseObject(resp.getBody());
        }

        return jsonObject;
    }

}
