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
import java.util.Arrays;

public class FileIO<T> {
    private final String TAG = FileIO.class.getCanonicalName();


    public T readFileData(Context context, String fileName, Type typeOf) {
        Gson gson = new Gson();
        AssetManager am = context.getAssets();
        T res = null;
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        try {
            if (Arrays.asList(context.getResources().getAssets().list("")).contains(fileName)) {
                is = am.open(fileName);
                Log.d(TAG, "Opening file: " + fileName);
            } else {
                is = am.open(fileName.substring(0, fileName.length() - 8) + ".json");
            }
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
}
