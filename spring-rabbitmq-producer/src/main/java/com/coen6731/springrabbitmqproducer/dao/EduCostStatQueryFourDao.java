package com.coen6731.springrabbitmqproducer.dao;


import com.coen6731.springrabbitmqproducer.config.CheckStatus;
import com.coen6731.springrabbitmqproducer.config.MongoConfig;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;


public class EduCostStatQueryFourDao {

    long year_range ;
    String type ;
    String length ;
    String url ;

    public EduCostStatQueryFourDao(long year_range, String type, String length, String url) {
        this.year_range = year_range;
        this.type = type;
        this.length = length;
        this.url = url;
    }

    public String queryFour() {

        String s;
        String query_name= "Four";

        MongoConfig mongoConfig= new MongoConfig(url,query_name);
        ArrayList<MongoCollection<Document>> arrayList= mongoConfig.config();
        MongoCollection<Document> coll = arrayList.get(0);
        MongoCollection<Document> collection = arrayList.get(1);

        long year_base=2021;
        long year_then= year_base-year_range;



            AggregateIterable<Document> result= coll.aggregate(Arrays.asList(
                   Aggregates.match(and(eq("Type",type),eq("Length",length),gt("Year",year_then),lte("Year", year_base))),
                    Aggregates.group(
                            new Document().append("State", "$State").append("Year", "$Year"),
                            Accumulators.sum("OverallExpense", "$Value")
                    ),
                    Aggregates.sort(Sorts.ascending("_id.State", "_id.Year")),
                   Aggregates.group("$_id.State",
                           Accumulators.first("Start_year", "$_id.Year"),
                           Accumulators.last("End_year","$_id.Year"),
                           Accumulators.push("Array","$OverallExpense")),
                   Aggregates.project(new Document()
                           .append("_id",0)
                           .append("State","$_id")
                           .append("Start_year",1)
                           .append("End_year",1)
                           //.append("Array",1)
                           .append("Growth_rate",
                                   new Document("$divide",Arrays.asList(
                                           new Document("$subtract",
                                                   Arrays.asList(new Document("$arrayElemAt",Arrays.asList("$Array",-1)),
                                                           new Document("$arrayElemAt",Arrays.asList("$Array",0)))),
                                           new Document("$arrayElemAt",Arrays.asList("$Array",0)))))),
                Aggregates.sort(Sorts.descending("Growth_rate")),
                Aggregates.limit(5)






            ));

        CheckStatus checkStatus=new CheckStatus(collection,result);
        s= checkStatus.status();

        return s;







    }
}

