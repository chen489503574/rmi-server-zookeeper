package rpc;

import rpc.anno.RpcAnnotation;
import rpc.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 屏蔽了网络访问的细节
 * Created by Chenjf on 2018/11/30.
 * 进行服务发布工作
 */
public class RpcServer {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    //发布服务需要在注册中心上注册
    private IRegisterCenter registerCenter;//注册中心
    //服务发布地址
    private String serviceAddress;

    //服务名称和服务对象的绑定，存放服务名称和服务对象之间的关系
    Map<String ,Object> handlerMap = new HashMap<String, Object>();


    /**
     * 构造函数，新建发布服务类的时候需要传入相应的参数
     * @param registerCenter
     * @param serviceAddress  服务发布到服务器上的地址
     */
    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     * @param services
     */
    public void bind(Object... services){
        for (Object service: services){
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            String version = annotation.version();
            if (version!=null && !"".equals(version)){
                serviceName = serviceName+"-"+version;
            }
            handlerMap.put(serviceName,service);//绑定服务接口名称对应的服务，放入map中
        }
    }

    public void publisher(){
        ServerSocket serverSocket = null;
        try {
            String[] addrs = serviceAddress.split(":");
            serverSocket = new ServerSocket(Integer.valueOf(addrs[1]));//启动一个监听

            for(String interfaceName: handlerMap.keySet()){
                registerCenter.register(interfaceName,serviceAddress);
                System.out.println("注册服务成功："+interfaceName+"->"+serviceAddress);
            }

            while (true){
                Socket socket = serverSocket.accept();
                //把封装成线程任务的  socket及需要对应的服务  提交到线程池中
                executorService.submit(new ProcessorHandler(socket,handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!= null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
