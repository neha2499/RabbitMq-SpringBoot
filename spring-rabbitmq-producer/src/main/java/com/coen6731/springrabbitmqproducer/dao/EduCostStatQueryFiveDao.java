package com.coen6731.springrabbitmqproducer.dao;


import com.coen6731.springrabbitmqproducer.config.CheckStatus;
import com.coen6731.springrabbitmqproducer.config.MongoConfig;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Filters.*;

public class EduCostStatQueryFiveDao {


    long year ;
    String type ;
    String length ;
    String url ;

    public EduCostStatQueryFiveDao(long year, String type, String length, String url) {
        this.year = year;
        this.type = type;
        this.length = length;
        this.url = url;
    }

    public String queryFive() {
        String s;
        String query_name = "Five";

        MongoConfig mongoConfig = new MongoConfig(url, query_name);
        ArrayList<MongoCollection<Document>> arrayList = mongoConfig.config();
        MongoCollection<Document> coll = arrayList.get(0);
        MongoCollection<Document> collection = arrayList.get(1);




        AggregateIterable<Document> result = coll.aggregate(Arrays.asList(

                Aggregates.match(and(eq("Year", year), eq("Type", type), eq("Length", length))),
                Aggregates.group(
                        new Document().append("State", "$State").append("Year", "$Year").append("Type", "$Type").append("Length", "$Length"),
                        sum("OverallExpense", "$Value")),

//                  )
                Aggregates.facet(new Facet("Northeast",
                        new Document("$match", in("_id.State", "Connecticut", "Maine", "Massachusetts", "New Hampshire", "New Jersey", "New York", "Pennsylvania", "Rhode Island", "Vermont")),
                        new Document("$group", new Document("_id", null).append("Average", new Document("$avg", "$OverallExpense"))),
                        new Document("$project", new Document().append("Average", 1).append("_id", 0))
                ), new Facet("Southeast",
                        new Document("$match", in("_id.State", "Alabama", "Arkansas", "Delaware", "Florida", "Georgia", "Kentucky", "Louisiana", "Maryland", "Mississippi", "North Carolina", "South Carolina", "Tennessee", "Virginia", "West Virginia")),
                        new Document("$group", new Document("_id", null).append("Average", new Document("$avg", "$OverallExpense"))),
                        new Document("$project", new Document().append("Average", 1).append("_id", 0))
                ), new Facet("Midwest",
                        new Document("$match", in("_id.State", "Illinois", "Indiana", "Iowa", "Kansas", "Michigan", "Minnesota", "Missouri", "Ohio", "North Dakota", "Nebraska", "South Dakota", "Wisconsin")),
                        new Document("$group", new Document("_id", null).append("Average", new Document("$avg", "$OverallExpense"))),
                        new Document("$project", new Document().append("Average", 1).append("_id", 0)
                        )
                ), new Facet("Southwest",
                        new Document("$match", in("_id.State", "Arizona", "New Mexico", "Oklahoma", "Texas")),
                        new Document("$group", new Document("_id", null).append("Average", new Document("$avg", "$OverallExpense"))),
                        new Document("$project", new Document().append("Average", 1).append("_id", 0))
                ), new Facet("West",
                        new Document("$match", in("_id.State", "Alaska", "California", "Colorado", "Hawaii", "Idaho", "Nevada", "Montana", "Oregon", "Utah", "Washington", "Wyoming")),
                        new Document("$group", new Document("_id", null).append("Average", new Document("$avg", "$OverallExpense"))),
                        new Document("$project", new Document().append("Average", 1).append("_id", 0)
                        )


                ))));

        CheckStatus checkStatus = new CheckStatus(collection, result);
        s = checkStatus.status();

        return s;


    }

  }

