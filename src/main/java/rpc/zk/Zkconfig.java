package rpc.zk;

/**
 * Created by Chenjf on 2018/12/3.
 * 注册服务使用的配置文件，zookeeper集群的地址和根节点的配置
 */
public class Zkconfig {

    public final static String CONNNECTION_STR  = "192.168.11.153:2181,192.168.11.153:2181,192.168.11.153:2181";

    public final static String ZK_REGISTER_PATH = "/registrys";

}
