package com.a1.nextlocation.data;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO<T> {


    public Class<T> readFileData(Context context, String fileName) {
        Gson gson = new Gson();
        AssetManager am = context.getAssets();
        Class<T> res = null;
        try {
            InputStream is = am.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            res = gson.fromJson(inputStreamReader,new TypeToken<T>(){}.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void writeFileData(T objectToWrite) {
        //TODO make
    }
}
