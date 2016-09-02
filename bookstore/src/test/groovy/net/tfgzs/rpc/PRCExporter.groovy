package net.tfgzs.rpc

import groovy.transform.CompileStatic

import java.lang.reflect.Method
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * 服务的发布者: 将本地服务发布成远程服务，供其他消费者调用
 * 1) 作为服务端，监听客户端的TCP连接，接收到新的客户端连接之后，将其封装成Task,由线程池执行
 * 2) 将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
 * 3) 将执行结果对象反序列化，通过socket发送给客户端
 * 4) 远程服务调用完成之后，释放Socket等连接资源，防止句柄谢了泄露
 */
@CompileStatic
public class PRCExporter {
    //创建固定大小的线程池。
    //每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
    //线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    public static void exporter(String hostName, int port) throws Exception {
        ServerSocket server = new ServerSocket()
        server.bind(new InetSocketAddress(hostName, port))
        try {
            while (true) {
                executor.execute(new ExporterTask(server.accept()))
            }
        } finally {
            server.close()
        }
    }

    private static class ExporterTask implements Runnable {
        Socket client = null

        public ExporterTask(Socket client) {
            this.client = client
        }

        @Override
        void run() {
            ObjectInputStream inputStream = null
            ObjectOutputStream outputStream = null
            try {
                inputStream = new ObjectInputStream(client.getInputStream())
                String interfaceName = inputStream.readUTF()
                Class<?> service = Class.forName(interfaceName)
                String methodName = inputStream.readUTF()
                Class<?>[] parameterTypes = inputStream.readObject() as Class<?>[]
                Object[] arguments = inputStream.readObject() as Object[]
                Method method = service.getMethod(methodName, parameterTypes)
                Object result = method.invoke(service.newInstance(), arguments)
                outputStream = new ObjectOutputStream(client.getOutputStream())
                outputStream.writeObject(result)
            } catch (Exception e) {
                e.printStackTrace()
            } finally {
                if (outputStream != null) outputStream.close()
                if (inputStream != null) inputStream.close()
                if (client != null) client.close()
            }
        }
    }
}
