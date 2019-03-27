package cn.neusoft.xuxiao.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Utils
{
  public static String encoder(String src)
  {
    Encoder encoder = Base64.getEncoder();
    byte[] den = encoder.encode(src.getBytes());
    return new String(den);
  }

  public static String decoder(String src) {
    Decoder decoder = Base64.getDecoder();
    byte[] den = decoder.decode(src.getBytes());
    return new String(den);
  }
}