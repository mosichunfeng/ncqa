package cn.neusoft.xuxiao.api.entity;

import lombok.Data;

import java.util.Map;

@Data
public class SubmitContentRequest
{
  private String user_id;
  private String question_base_id;
  private String map;
  private Map<String,String> dataMap;
  private String end_time;
  private Map<String,String> dataMap2;
}