package com.coen6731.topic;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class JsonConfigMaker {


    public ArrayList<JSONObject> setConfigFile(){


            ArrayList<JSONObject> output= new ArrayList<>();
            try {
                FileReader reader = new FileReader("/home/neha/Desktop/new/topic/src/main/java/com/coen6731/topic/topicConfig.json");
                JSONParser parser = new JSONParser();
                JSONObject configObj = (JSONObject) parser.parse(reader);


                //Reading JSONObject for key EduCostStatQueryOne
                JSONObject queryOne = (JSONObject) configObj.get("EduCostStatQueryOne");
                System.out.println(queryOne);
                Integer year1 = Integer.parseInt(queryOne.get("year").toString());
                String state1 = (String) queryOne.get("state");
                String type1 = (String) queryOne.get("type");
                String length1 = (String) queryOne.get("length");
                String expense1 = (String) queryOne.get("expense");
                String output1 = "Cost-"+ String.valueOf(year1)+"-"+state1+"-"+type1 +"-"+length1;
                queryOne.put("Query Topic",output1);
                queryOne.put("Routing Name","EduCostStatQueryOne");
                System.out.println(queryOne);
                output.add(queryOne);


                //Top5-Expensive-[Year]-[Type]-[Length]
                //Reading JSONObject for key EduCostStatQueryTwo
                JSONObject queryTwo = (JSONObject) configObj.get("EduCostStatQueryTwo");
                System.out.println(queryTwo);
                Integer year2 = Integer.parseInt(queryTwo.get("year").toString());
                String type2 = (String) queryTwo.get("type");
                String length2 = (String) queryTwo.get("length");
                String output2 = "Top5-Expensive-"+ String.valueOf(year2)+"-"+type2 +"-"+length2;
                queryTwo.put("Query Topic",output2);
                queryTwo.put("Routing Name","EduCostStatQueryTwo");
                System.out.println(queryTwo);
                output.add(queryTwo);


                //Top5-Economic-[Year]-[Type]-[Length]
                //Reading JSONObject for key EduCostStatQueryThree
                JSONObject queryThree = (JSONObject) configObj.get("EduCostStatQueryThree");
                System.out.println(queryThree);
                Integer year3 = Integer.parseInt(queryThree.get("year").toString());
                String type3 = (String) queryThree.get("type");
                String length3 = (String) queryThree.get("length");
                String output3 = "Top5-Economic-"+ String.valueOf(year3)+"-"+type3 +"-"+length3;
                queryThree.put("Query Topic",output3);
                queryThree.put("Routing Name","EduCostStatQueryThree");
                System.out.println(queryThree);
                output.add(queryThree);


                //Top5-HighestGrow-[Years]
                //Reading JSONObject for key EduCostStatQueryFour
                JSONObject queryFour = (JSONObject) configObj.get("EduCostStatQueryFour");
                System.out.println(queryFour);
                Integer year_range = Integer.parseInt(queryFour.get("year_range").toString());
                String type4 = (String) queryFour.get("type");
                String length4 = (String) queryFour.get("length");
                String output4 = "Top5-HighestGrow-"+ String.valueOf(year_range);
                queryFour.put("Query Topic",output4);
                queryFour.put("Routing Name","EduCostStatQueryFour");
                System.out.println(queryFour);
                output.add(queryFour);




                //AverageExpense-[Year]-[Type]-[Length]
                //Reading JSONObject for key EduCostStatQueryFive
                JSONObject queryFive = (JSONObject) configObj.get("EduCostStatQueryFive");
                System.out.println(queryFive);
                Integer year5 = Integer.parseInt(queryFive.get("year").toString());
                String type5 = (String) queryFive.get("type");
                String length5 = (String) queryFive.get("length");
                String output5 = "Top5-HighestGrow-"+ String.valueOf(year5);
                queryFive.put("Query Topic",output5);
                queryFive.put("Routing Name","EduCostStatQueryFive");
                System.out.println(queryFive);
                output.add(queryFive);

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }





            return output;




    }
}
