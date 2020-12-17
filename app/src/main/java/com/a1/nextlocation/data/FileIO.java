package com.a1.nextlocation.data;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO<T> {

    private Class<T> tClass;


    public Class<T> readFileData(Context context, String fileName) {
        Gson gson = new Gson();
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            T res = gson.fromJson(inputStreamReader,);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO make
        return null;
    }

    public void writeFileData(T objectToWrite) {
        //TODO make
    }
}
