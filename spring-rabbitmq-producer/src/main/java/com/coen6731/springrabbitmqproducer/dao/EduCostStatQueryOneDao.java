package com.coen6731.springrabbitmqproducer.dao;



import com.coen6731.springrabbitmqproducer.config.CheckStatus;
import com.coen6731.springrabbitmqproducer.config.MongoConfig;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;


public class EduCostStatQueryOneDao {





        long year;
        String state;
        String type;
        String length;
        String expense;
        String url;

    public EduCostStatQueryOneDao(long year, String state, String type, String length, String expense, String url) {
        this.year = year;
        this.state = state;
        this.type = type;
        this.length = length;
        this.expense = expense;
        this.url = url;
    }



    public String queryOne() {


        String s;
         String query_name= "One";

          MongoConfig mongoConfig= new MongoConfig(url,query_name);
          ArrayList<MongoCollection<Document>> arrayList= mongoConfig.config();
           MongoCollection<Document> coll = arrayList.get(0);
           MongoCollection<Document> collection = arrayList.get(1);








/*
Query the cost given specific year, state, type, length, expense; and save the query as a
document in a collection named EduCostStatQueryOne.
*/
            AggregateIterable<Document> result = coll.aggregate(Arrays.asList(
                    Aggregates.match(and(eq("Year", year), eq("State", state), eq("Type", type), eq("Length", length), eq("Expense", expense))),
                    Aggregates.project(Projections.fields(Projections.excludeId(), Projections.include()))
            ));







        CheckStatus checkStatus=new CheckStatus(collection,result);
        s= checkStatus.status();

        return s;
    }
}
