package com.a1.nextlocation.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO<T> {
    private final String TAG = FileIO.class.getCanonicalName();


    public T readFileData(Context context, String fileName, Type typeOf) {
        Gson gson = new Gson();
        AssetManager am = context.getAssets();
        T res = null;
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = am.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine())!= null) {
                sb.append(line);
            }


            Log.d(TAG, "readFileData: got string: " + sb.toString());
            res = gson.fromJson(sb.toString(),typeOf);
            Log.d(TAG, "readFileData: got object: " + res);

            reader.close();
            inputStreamReader.close();
            is.close();

        } catch (IOException e) {
            Log.d(TAG, "readFileData:  exception! " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return res;
    }

//    public void writeFileData(T objectToWrite, Context context) {
//        //TODO make
//        //object naar jsonobject
//        //jsonarray toevoegen/maken
//        //filewriter naar file
//
//        String filename = "";
//        if (objectToWrite instanceof Coupon){
//            filename = "coupons.json";
//        }
//
//        if (objectToWrite instanceof Route){
//            filename = "routes.json";
//        }
//
//        if (objectToWrite instanceof Location){
//            filename = "locations.json";
//        }
//
//        try (FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)){
//            String json = new Gson().toJson(objectToWrite);
//
//            fileOutputStream.write(json.getBytes(StandardCharsets.UTF_8));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        /*try (FileWriter fileWriter = new FileWriter(filename)){
//
//            String json = new Gson().toJson(objectToWrite);
//
//            fileWriter.append(json);
//            fileWriter.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
//    }
}
