package rpc;

import rpc.zk.IRegisterCenter;
import rpc.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * Created by Chenjf on 2018/11/30.
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        IGpHello iGpHello = new GpHelloImpl();
        IGpHello iGpHello2 = new GpHelloImpl2();
        IRegisterCenter registerCenter = new RegisterCenterImpl();//通过注册中心进行发布
        RpcServer rpcServer = new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(iGpHello,iGpHello2);//绑定这个服务
        rpcServer.publisher();//进行发布
        System.in.read();
    }
}
