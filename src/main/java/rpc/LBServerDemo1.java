package rpc;

import rpc.zk.IRegisterCenter;
import rpc.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * Created by Chenjf on 2018/11/30.
 * 这个是集群发布类，一个机器上有两个节点
 */
public class LBServerDemo1 {
    public static void main(String[] args) throws IOException {
        IGpHello iGpHello = new GpHelloImpl2();
        IRegisterCenter registerCenter = new RegisterCenterImpl();//通过注册中心进行发布
        RpcServer rpcServer = new RpcServer(registerCenter,"127.0.0.1:8081");
        rpcServer.bind(iGpHello);//绑定这个服务
        rpcServer.publisher();//进行发布
        System.in.read();
    }
}
