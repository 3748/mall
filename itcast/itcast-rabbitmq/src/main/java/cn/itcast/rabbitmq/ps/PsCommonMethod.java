package cn.itcast.rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import cn.itcast.rabbitmq.util.ConnectionUtil;

/**
 * 订阅模式----通用方法
 *
 * @author gp6
 * @date 2018/9/10
 */
public class PsCommonMethod {
    private final static String EXCHANGE_NAME = "ps_exchange_name";

    public PsCommonMethod(String queueName) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(queueName, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(queueName, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(queueName + message);
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
