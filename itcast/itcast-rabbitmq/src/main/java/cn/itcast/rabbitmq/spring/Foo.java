package cn.itcast.rabbitmq.spring;

/**
 * 消费者
 *
 * @author gp6
 * @date 2018/9/10
 */
public class Foo {

    /**
     * 具体执行业务的方法
     *
     * @param foo foo
     */
    public void listen(String foo) {
        System.out.println("消费者： " + foo);
    }
}