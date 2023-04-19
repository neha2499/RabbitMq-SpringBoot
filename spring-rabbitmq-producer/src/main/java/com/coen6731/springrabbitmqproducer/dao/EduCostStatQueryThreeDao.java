package com.coen6731.springrabbitmqproducer.dao;


import com.coen6731.springrabbitmqproducer.config.CheckStatus;
import com.coen6731.springrabbitmqproducer.config.MongoConfig;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class EduCostStatQueryThreeDao {




    long year ;
    String type ;
    String length ;
    String url ;


    public EduCostStatQueryThreeDao(long year, String type, String length, String url) {
        this.year = year;
        this.type = type;
        this.length = length;
        this.url = url;
    }

    public String queryThree(){


//        Query the top 5 most economic states (with overall expense) given a year, type, length; and
//        save the query as a document in a collection named EduCostStatQueryThree.



//        int year = 2013;
//
//        String type = "Private";
//        String length = "4-year";
//
//        String url = "mongodb+srv://neha2499:group86731@cluster.ue98hvc.mongodb.net/test?tls=true";


        String s;
        String query_name= "Three";

        MongoConfig mongoConfig= new MongoConfig(url,query_name);
        ArrayList<MongoCollection<Document>> arrayList= mongoConfig.config();
        MongoCollection<Document> coll = arrayList.get(0);
        MongoCollection<Document> collection = arrayList.get(1);



            AggregateIterable<Document> result = coll.aggregate(Arrays.asList(
                    Aggregates.match(and(eq("Year",year),eq("Type",type),eq("Length",length))),
                    Aggregates.group(
                            new Document().append("State", "$State").append("Year", "$Year"),
                            Accumulators.sum("OverallExpense", "$Value")
                    ),
                    Aggregates.sort(Sorts.ascending("OverallExpense")),
                    Aggregates.limit(5)
            ));

        CheckStatus checkStatus=new CheckStatus(collection,result);
        s= checkStatus.status();

        return s;
        }

    }

