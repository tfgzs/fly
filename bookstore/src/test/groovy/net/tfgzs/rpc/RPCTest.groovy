package net.tfgzs.rpc

import groovy.transform.CompileStatic
import net.tfgzs.rpc.demo.EchoService
import net.tfgzs.rpc.demo.EchoServiceImpl
import org.junit.Test

/**
 * 测试代码
 */
@CompileStatic
class RPCTest {
    @Test
    public void demo() {
        //异步发布服务端，用于接收RPC客户端请求，根据请求参数调用服务实现类，返回结果给客户端
        new Thread(new Runnable() {
            @Override
            void run() {
                try {
                    PRCExporter.exporter("localhost", 8088)
                } catch (Exception e) {
                    e.printStackTrace()
                }
            }
        }).start()
        //创建卡客户端服务代理类，构建RPC请求参数，发起RPC调用
        RPCImporter<EchoService> importer = new RPCImporter<>()
        EchoService echo = importer.importer(EchoServiceImpl, new InetSocketAddress("localhost", 8088))
        println echo.echo("Are you ok ?")
    }
}
