package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Chenjf on 2018/11/29.
 * 此类是发布服务端 服务的
 */
public class Server {
    public static void main(String[] args) {
        try {
            IHelloService helloService = new HelloServiceImpl();//实例一个服务对象，在实例的过程中调用构造方法中的super()，就发布了一个代理服务对象
            LocateRegistry.createRegistry(1099);//可以理解为在注册中心上注册了,在这里会创建一个监听
            Naming.rebind("rmi://127.0.0.1/Hello",helloService);//通过 rmi 协议把 访问地址 和 hello绑定在一起
            System.out.println("Hello 服务启动成功！");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
