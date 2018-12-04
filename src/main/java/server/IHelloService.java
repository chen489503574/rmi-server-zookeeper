package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Chenjf on 2018/11/29.
 * 远程服务调用的话，该接口就必须实现 Remote 接口
 */
public interface IHelloService extends Remote {
    String sayHello(String msg) throws RemoteException;
}
