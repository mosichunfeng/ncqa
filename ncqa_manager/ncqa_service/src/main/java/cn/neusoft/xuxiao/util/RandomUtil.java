package cn.neusoft.xuxiao.util;

public class RandomUtil {

    public static String randomStr() {
        String baseStr = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char s1 = baseStr.charAt((int) (Math.random() * 62));
        char s2 = baseStr.charAt((int) (Math.random() * 62));
        char s3 = baseStr.charAt((int) (Math.random() * 62));
        char s4 = baseStr.charAt((int) (Math.random() * 62));
        char s5 = baseStr.charAt((int) (Math.random() * 62));
        char s6 = baseStr.charAt((int) (Math.random() * 62));
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        sb.append(s4);
        sb.append(s5);
        sb.append(s6);
        return sb.toString();
    }

}
