package com.coen6731.springrabbitmqproducer.producer;

import com.coen6731.springrabbitmqproducer.config.MQConfig;
import com.coen6731.springrabbitmqproducer.dao.*;
import com.coen6731.topic.JsonConfigMaker;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    static JsonConfigMaker configMaker = new JsonConfigMaker();
    static ArrayList<JSONObject> Out = configMaker.setConfigFile();

    @GetMapping("publishAll")
    public String queryAll() {
        String url = "mongodb+srv://neha2499:group86731@cluster.ue98hvc.mongodb.net/test?tls=true";

        //Topic1
        //Query the cost given specific year, state, type, length,expense
        long year1 = (long) Out.get(0).get("year");
        String state1 = (String) Out.get(0).get("state");
        String type1 = (String) Out.get(0).get("type");
        String length1 = (String) Out.get(0).get("length");
        String expense1 = (String) Out.get(0).get("expense");
        EduCostStatQueryOneDao eduCostStatQueryOneDao = new EduCostStatQueryOneDao(year1, state1, type1, length1, expense1, url);
        String s1 = eduCostStatQueryOneDao.queryOne();
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_1, s1);


        //Topic2
        //Query the top 5 most expensive states (with overall expense)given a year, type, length
        long year2 = (long) Out.get(1).get("year");
        String type2 = (String) Out.get(1).get("type");
        String length2 = (String) Out.get(1).get("length");
        EduCostStatQueryTwoDao eduCostStatQueryTwoDao = new EduCostStatQueryTwoDao(year2, type2, length2, url);
        String s2 = eduCostStatQueryTwoDao.queryTwo();
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_2, s2);


        //Topic3
        //Query the top 5 most economic states (with overall expense) given a year, type, length
        long year3 = (long) Out.get(2).get("year");
        String type3 = (String) Out.get(2).get("type");
        String length3 = (String) Out.get(2).get("length");
        EduCostStatQueryThreeDao eduCostStatQueryThreeDao = new EduCostStatQueryThreeDao(year3, type3, length3, url);
        String s3 = eduCostStatQueryThreeDao.queryThree();
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_3, s3);


        //Topic4
        //Query the top 5 states of the highest growth rate of overall expense given a range of past years
        long year_range = Long.parseLong((String) Out.get(3).get("year_range"));
        String type4 = (String) Out.get(3).get("type");
        String length4 = (String) Out.get(3).get("length");
        EduCostStatQueryFourDao eduCostStatQueryFourDao = new EduCostStatQueryFourDao(year_range, type4, length4, url);
        String s4 = eduCostStatQueryFourDao.queryFour();
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_4, s4);


        //Topic5
        //Aggregate region‘s average overall expense for a given year,type and length
        long year5 = (long) Out.get(4).get("year");
        String type5 = (String) Out.get(4).get("type");
        String length5 = (String) Out.get(4).get("length");
        EduCostStatQueryFiveDao eduCostStatQueryFiveDao = new EduCostStatQueryFiveDao(year5, type5, length5, url);
        String s5 = eduCostStatQueryFiveDao.queryFive();
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_5, s5);


        return "Messages for all tasks Published";

    }


}



