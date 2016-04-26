package com.json.parse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJSON {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject)parser.parse(new FileReader("C:\\Users\\Nagendra\\AQD\\Test_Instance.json"));
		    
			JSONArray entity = (JSONArray) obj.get("entities");
			for(int i=0;i<entity.size();i++){
				JSONObject jsonnew=(JSONObject) entity.get(i);
				JSONArray jsonarrayROW = (JSONArray) jsonnew.get("Fields");
				

				   for(int j=0;j<jsonarrayROW.size();j++){
					   JSONObject jsonInner=(JSONObject) jsonarrayROW.get(j);
					   //System.out.println(jsonInner.toString());
                       if(jsonInner.get("Name").equals("test-id")){
                    	   JSONArray jsonarrayInner = (JSONArray) jsonInner.get("values");
                    	   System.out.println(jsonarrayInner.size());
                    	   for(int z= 0 ;z<jsonarrayInner.size();z++){
                    		   JSONObject jsonValue=(JSONObject) jsonarrayInner.get(z);
                    		   System.out.println(jsonValue.get("value"));
                    	   }
                    	  System.out.println("test"); 
                       }
				   }
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
