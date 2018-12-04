package rpc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Chenjf on 2018/12/3.
 * 自定义注解
 */
@Target(ElementType.TYPE)//这个注解修饰的范围
@Retention(RetentionPolicy.RUNTIME)//注解的生命周期
public @interface RpcAnnotation {

    /**
     * 对外发布的服务的接口
     * @return
     */
    Class<?> value();

    /**
     * 版本
     */
    String version() default "";
}
