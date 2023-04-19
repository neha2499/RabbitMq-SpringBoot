package com.coen6731.springrabbitmqproducer.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CreateCollection {

    public CreateCollection() {
    }

    public void collection() {

        String connectionString = "mongodb+srv://neha2499:group86731@cluster.ue98hvc.mongodb.net/test?tls=true";
        String collectionName = "EduCostStat";
        String csvFilePath = "nces330_20.csv";

        // Create a MongoDB client
        MongoClient mongoClient = MongoClients.create(connectionString);

        // Get a handle to the database
        MongoDatabase database = mongoClient.getDatabase("Neha_assignment2");

        // Get a handle to the collection
        MongoCollection<Document> collection = database.getCollection(collectionName);

        // Read the CSV file into a string
        String csvData = "";
        try {
            csvData = new String(Files.readAllBytes(Paths.get(csvFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a Document object for each row in the CSV file and insert them into the collection
        String[] rows = csvData.split("\n");
        for (int i = 1; i < rows.length; i++) { // skip header row
            String[] columns = rows[i].split(",");
            Document document = new Document("Year", Integer.parseInt(columns[0]))
                    .append("State", columns[1])
                    .append("Type", columns[2])
                    .append("Length", columns[3])
                    .append("Expense", columns[4])
                    .append("Value", Integer.parseInt(columns[5]));
            // add more columns as needed
            collection.insertOne(document);
        }

        // Close the MongoDB client
        mongoClient.close();
    }


}


