package com.bbstore.reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    JSONParser jsonParser;
    FileReader fileReader;
    JSONObject obj;

    public JSONReader(String filename) {
        this.jsonParser = new JSONParser();

        try {
            this.fileReader = new FileReader(filename);
            this.obj = (JSONObject) jsonParser.parse(fileReader);
        }catch (FileNotFoundException e){
            System.out.println(filename+" not found!");
        }catch (IOException e){
            System.out.println("Something went wrong.");
        }catch (ParseException e){
            System.out.println("Error parsing json data.");
        }
    }

    public String get(String key){
        return (String) this.obj.get(key);
    }
}
