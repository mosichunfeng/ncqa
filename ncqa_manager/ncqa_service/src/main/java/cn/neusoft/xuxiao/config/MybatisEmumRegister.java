package cn.neusoft.xuxiao.config;

import cn.neusoft.xuxiao.mybatis.inteface.EnumIdentifiable;
import com.baomidou.mybatisplus.spring.boot.starter.ConfigurationCustomizer;
import com.magicbeans.base.kit.StrKit;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import java.util.Set;

@org.springframework.context.annotation.Configuration
public class MybatisEmumRegister implements ConfigurationCustomizer {
    private String enumBasePackage = "cn.neusoft.xuxiao";
    @Override
    public void customize(Configuration configuration) {
        //注册枚举类的自动转换
        try {
            if (!StrKit.isEmpty(this.enumBasePackage)) {
                ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<Class<?>>();
                resolverUtil.find(new ResolverUtil.IsA(EnumIdentifiable.class), this.enumBasePackage);
                Set<Class<? extends Class<?>>> mTypes = resolverUtil.getClasses();
                if(!mTypes.isEmpty()){
                    for (Class<?> javaTypeClass : mTypes) {
                        if(EnumIdentifiable.class.getName().equals(javaTypeClass.getName())){
                            continue;
                        }
                        configuration.getTypeAliasRegistry().registerAlias(javaTypeClass.getName(), javaTypeClass);
                        configuration.getTypeAliasRegistry().registerAlias(javaTypeClass.getName().replace("$", "."), javaTypeClass);
                        configuration.getTypeHandlerRegistry().register(javaTypeClass, EnumOrdinalTypeHandler.class);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
