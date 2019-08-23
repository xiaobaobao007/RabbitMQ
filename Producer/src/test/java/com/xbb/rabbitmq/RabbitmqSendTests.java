package com.xbb.rabbitmq;

        import com.xbb.rabbitmq.entity.Order;
        import com.xbb.rabbitmq.producer.OrderSender;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.test.context.junit4.SpringRunner;
        import sun.plugin.util.UIUtil;

        import java.util.Random;
        import java.util.UUID;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqSendTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderSender orderSender;

    @Test
    public void send(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        Random random = new Random();
        Runnable runnable = () -> {
            while (true) {
                int i = random.nextInt(1000000);
                try {
                    Thread.sleep(1000);
                    Order order = new Order();
                    order.setId("id:" + i);
                    order.setName("name:" + i);
                    order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID());
                    orderSender.send(order);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        service.execute(runnable);
        try {
            Thread.sleep(99999999999999L);//Junit特性：所有线程是守护线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
