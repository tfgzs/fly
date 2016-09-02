package net.tfgzs.rpc.demo

/**
 * 服务提供者：服务的实现类
 */
class EchoServiceImpl implements EchoService{
    @Override
    String echo(String ping) {
        return ping?"${ping} --> I am ok":" I am ok"
    }
}
