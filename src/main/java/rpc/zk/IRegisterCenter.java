package rpc.zk;

/**
 * Created by Chenjf on 2018/12/3.
 * zk  包中提供了一个注册中心的接口，以对外提供注册服务
 *
 */
public interface IRegisterCenter {

    /**
     * 注册服务名称和服务地址
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName,String serviceAddress);
}
