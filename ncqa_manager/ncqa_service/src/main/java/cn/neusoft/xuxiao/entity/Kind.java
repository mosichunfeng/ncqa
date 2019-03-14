package cn.neusoft.xuxiao.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.magicbeans.base.BaseEntity;
import lombok.Data;

@Data
@TableName("kind")
public class Kind extends BaseEntity<Kind> {
    private static final long serialVersionUID = 1L;

    private String name;
}
