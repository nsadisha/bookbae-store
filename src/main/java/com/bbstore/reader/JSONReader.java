package com.bbstore.reader;

import com.bbstore.alert.AlertBox;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
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
            AlertBox.showAlert("Error",filename+" not found!", JOptionPane.ERROR_MESSAGE);
            System.out.println();
        }catch (IOException e){
            AlertBox.showAlert("Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }catch (ParseException e){
            AlertBox.showAlert("Error", "Syntax error in "+filename, JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            AlertBox.showAlert("Error", "Error reading "+filename, JOptionPane.ERROR_MESSAGE);
        }
    }

    public String get(String key){
        return (String) this.obj.get(key);
    }
}
