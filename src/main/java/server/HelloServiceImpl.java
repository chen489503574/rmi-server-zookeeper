package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Chenjf on 2018/11/29.
 * 如果要远程调用的话，该实现类就必须要继承  UnicastRemoteObject  类
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    public String sayHello(String msg) throws RemoteException{
        return "Hello,"+msg;
    }
}
