package com.tasks.kivork.api;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Class represents JSON helper
 */
public class JsonHelper {

    private JsonHelper() {
    }

    /**
     * To json string.
     *
     * @param filePath json file path
     * @param columnName column to read
     * @return the string
     */
    public static String getDataFromJson(String filePath, String columnName) throws IOException, ParseException {
        // parsing file
        Object obj = new JSONParser().parse(new FileReader(filePath));
        // typecasting
        JSONObject jo = (JSONObject) obj;
        return (String) jo.get(columnName);
    }
}
