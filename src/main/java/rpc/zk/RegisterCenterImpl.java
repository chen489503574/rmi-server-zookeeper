package rpc.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by Chenjf on 2018/12/3.
 * 注册接口的实现类
 */
public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        /**
         * 创建  CuratorFramework  设置初始化参数
         */
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(Zkconfig.CONNNECTION_STR)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();
        curatorFramework.start();
    }
    @Override
    public void register(String serviceName, String serviceAddress) {
        //注册相应的服务节点，在根节点下注册 该服务节点
        String servicePath  = Zkconfig.ZK_REGISTER_PATH +"/" + serviceName;
        try {
            //判断/registrys/product-service（举例产品服务）是否存在，不存在则创建该节点
            if (curatorFramework.checkExists().forPath(servicePath)==null){
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(servicePath,"0".getBytes());
            }
            //上面创建完服务节点后，下面开始创建服务的子节点，临时节点
            String addressPath = servicePath+"/"+serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());
            System.out.println("服务注册成功："+rsNode);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
