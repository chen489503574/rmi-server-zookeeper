package rpc;

import rpc.anno.RpcAnnotation;

/**
 * Created by Chenjf on 2018/11/30.
 */
@RpcAnnotation(IGpHello.class)
public class GpHelloImpl implements IGpHello {
    public String sayHi(String name) {
//        return "Hello,friends"+name;//单节点测试
        return "I'm  port  8080  的Node节点"+name;//集群（两个节点）
    }
}
