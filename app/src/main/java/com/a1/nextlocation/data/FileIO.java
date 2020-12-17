package com.a1.nextlocation.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
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

    public void writeFileData(T objectToWrite) {
        //TODO make
    }
}
