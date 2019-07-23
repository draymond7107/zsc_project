package com.zsc.mina.mina;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

/**
 * 客户端业务处理
 *
 * @author ZhangSuchao
 * @create 2019/4/8
 * @since 1.0.0
 */
@Component
public class MinaClientHandler extends IoHandlerAdapter {
    // 这里我们使用的SLF4J作为日志门面，至于为什么在后面说明。
    // private final static Logger log = LoggerFactory.getLogger(TCPServerHandler.class);

    // 当客户端连接进入时
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("incomming 客户端: " + session.getRemoteAddress());

        session.write("客户端发送数据");
        System.out.println("客户端发送数据");
    }


    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("客户端发送信息异常....");
    }

    // 当客户端发送消息到达时
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        System.out.println("服务器返回的数据：" + str);



    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("客户端与服务端断开连接.....");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("one Client Connection" + session.getRemoteAddress());
        session.write("我来了······");
    }

}
