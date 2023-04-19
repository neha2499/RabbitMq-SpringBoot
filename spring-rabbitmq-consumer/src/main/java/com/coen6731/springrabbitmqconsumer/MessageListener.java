package com.coen6731.springrabbitmqconsumer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {


    //Task1
    @RabbitListener(queues = "#{MQConfig.QUEUE_1}")
    public void listener_1(String s){
        System.out.println("Topic 1 "+s);
    }

    //Task2
    @RabbitListener(queues = "#{MQConfig.QUEUE_2}")
    public void listener_2(String s1){
        System.out.println("Topic 2 "+s1);
    }

    //Task3
    @RabbitListener(queues = "#{MQConfig.QUEUE_3}")
    public void listener_3(String s2){
        System.out.println("Topic 3 "+s2);
    }

    //Task4
    @RabbitListener(queues = "#{MQConfig.QUEUE_4}")
    public void listener_4(String s3){
        System.out.println("Topic 4 "+s3);
    }

    //Task4
    @RabbitListener(queues = "#{MQConfig.QUEUE_5}")
    public void listener_5(String s4){
        System.out.println("Topic 5 "+s4);
    }

}
