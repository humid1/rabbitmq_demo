package com.humid.client.handle;

import com.humid.client.bean.MethodInfo;
import com.humid.client.bean.ServerInfo;

/**
 * rest调用 handle
 * @author qiujianping
 * @date Created in 2021/8/18 10:50
 */
public interface RestHandle {
    /**
     * 初始化服务器信息
     * @param serverInfo
     * @return
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用 rest 请求
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);

}
