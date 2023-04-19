package com.coen6731.springrabbitmqproducer.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class MongoConfig {

    String url;
    String query_name;
    public MongoConfig(String url,String query_name) {
        this.url = url;
        this.query_name=query_name;

    }



public ArrayList<MongoCollection<Document>> config(){

        MongoClient mongoClient = MongoClients.create(url);
        // database and collection code goes here
        MongoDatabase db = mongoClient.getDatabase("Neha_assignment2");
        MongoCollection<Document> coll = db.getCollection("EduCostStat");
        MongoCollection<Document> collection = db.getCollection("EduCostStatQuery"+query_name);
        ArrayList<MongoCollection<Document>> arrayList = new ArrayList<>();
        arrayList.add(coll);
        arrayList.add(collection);
        System.out.println("Database accessed");




       return arrayList;
    }
}
