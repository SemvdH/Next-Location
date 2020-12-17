package com.a1.nextlocation.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO<T> {
    private final String TAG = FileIO.class.getCanonicalName();


    public T readFileData(Context context, String fileName) {
        Gson gson = new Gson();
        AssetManager am = context.getAssets();
        T res = null;
        try {
            InputStream is = am.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            res = gson.fromJson(inputStreamReader,new TypeToken<T>(){}.getType());
            Log.d(TAG, "readFileData: got object: " + res);

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
