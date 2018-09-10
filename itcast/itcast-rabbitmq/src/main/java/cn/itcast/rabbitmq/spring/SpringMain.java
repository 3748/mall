package cn.itcast.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 生产者
 *
 * @author gp6
 * @date 2018/9/10
 */
public class SpringMain {
    public static void main(final String... args) throws Exception {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/rabbitmq-context.xml");
        // RabbitMQ模板
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        // 发送消息
        rabbitTemplate.convertAndSend("Hello, world!");
        // 休眠1秒
        Thread.sleep(10 * 100);
        // 容器销毁
        ctx.destroy();
    }
}
