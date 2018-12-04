package rpc;

import java.io.Serializable;

/**
 * Created by Chenjf on 2018/11/30.
 * 传输对象
 * 这是一个客户端提交的请求 类，需要远程传输，所以需要序列化
 *
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 5651131960519623549L;
    private String className;
    private String methodName;
    private Object[] parameters;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
