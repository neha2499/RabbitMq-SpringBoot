package com.coen6731.springrabbitmqconsumer;


import com.coen6731.topic.JsonConfigMaker;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;


@Configuration
public class MQConfig {



    static JsonConfigMaker configMaker=new JsonConfigMaker();
    static ArrayList<JSONObject> Out= configMaker.setConfigFile();

    public static final String QUEUE_1 = (String) Out.get(0).get("Query Topic");
    public static final String QUEUE_2 = (String) Out.get(1).get("Query Topic");
    public static final String QUEUE_3 = (String) Out.get(2).get("Query Topic");
    public static final String QUEUE_4 = (String) Out.get(3).get("Query Topic");
    public static final String QUEUE_5 = (String) Out.get(4).get("Query Topic");



    public static final String EXCHANGE="message_exchange";


    public static final String ROUTING_KEY_1=(String) Out.get(0).get("Routing Name");
    public static final String ROUTING_KEY_2=(String) Out.get(1).get("Routing Name");
    public static final String ROUTING_KEY_3=(String) Out.get(2).get("Routing Name");
    public static final String ROUTING_KEY_4=(String) Out.get(3).get("Routing Name");
    public static final String ROUTING_KEY_5=(String) Out.get(4).get("Routing Name");


    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_2);
    }

    @Bean
    public Queue queue3() {
        return new Queue(QUEUE_3);
    }

    @Bean
    public Queue queue4() {
        return new Queue(QUEUE_4);
    }

    @Bean
    public Queue queue5() {
        return new Queue(QUEUE_5);
    }



    ///////////////////////////////////




    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }



    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY_1);
    }
    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY_2);
    }
    @Bean
    public Binding binding3(Queue queue3, TopicExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with(ROUTING_KEY_3);
    }
    @Bean
    public Binding binding4(Queue queue4, TopicExchange exchange) {
        return BindingBuilder.bind(queue4).to(exchange).with(ROUTING_KEY_4);
    }
    @Bean
    public Binding binding5(Queue queue5, TopicExchange exchange) {
        return BindingBuilder.bind(queue5).to(exchange).with(ROUTING_KEY_5);
    }






    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }



    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
