package rpc;

import rpc.anno.RpcAnnotation;

/**
 * Created by Chenjf on 2018/11/30.
 */
@RpcAnnotation(value = IGpHello.class)
public class GpHelloImpl2 implements IGpHello {
    @Override
    public String sayHi(String name) {
//        return "[I'm Version 2.0] Hello,friends"+name;//单节点
        return "[I'm port8081的节点] Hello,friends"+name;//集群（两个节点）
    }
}
