package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chenjf on 2018/11/30.
 *  这是一个封装  客户端socket  及需要对应服务处理的  线程处理类
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;//监听接收到的客户端的socket

    Map<String ,Object> handlerMap = null;

    public ProcessorHandler(Socket socket, Map<String ,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    public void run() {
        //TODO 处理请求
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest)inputStream.readObject();
            Object result = invoke(request);
            //把处理结果写回客户端
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = request.getParameters();//接收请求参数
        //组建参数数组
        Class<?>[] types = new Class[args.length];
        for (int i=0;i<args.length;i++){
            types[i] = args[i].getClass();
        }

        String serviceName = request.getClassName();
        String version = request.getVersion();
        if (version!=null && !"".equals(version)){
            serviceName = serviceName+"-"+version;
        }
        //从handlerMap中，根据客户端请求的地址，去拿到相应的服务，通过反射发起调用
        Object service = handlerMap.get(serviceName);
        Method method = service.getClass().getMethod(request.getMethodName(), types);
        return method.invoke(service,args);

    }
}
