package com.innky.majobroom.utills;

import com.google.gson.Gson;
import com.innky.majobroom.jsonbean.GeomtryBean;
import com.innky.majobroom.jsonbean.ModelBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ModelJsonReader {
    public static GeomtryBean readJson(String path) {
        try (var in = ModelJsonReader.class.getResourceAsStream("/assets/majobroom/" + path + ".json");) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            var builder = new StringBuilder();
            String temp;

            while ((temp = bufferedReader.readLine()) != null) {
                builder.append(temp);
            }
            String presonsString = builder.toString();
            Gson gson = new Gson();
            ModelBean fromJson = gson.fromJson(presonsString,
                    ModelBean.class);

            return fromJson.getModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
