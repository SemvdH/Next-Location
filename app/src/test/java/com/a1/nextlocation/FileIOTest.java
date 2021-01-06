package com.a1.nextlocation;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.InstrumentationInfo;
import android.widget.ArrayAdapter;

import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileIOTest {

//    @Test
//    public void testReadFileIO() {
//        //System.out.println(Arrays.toString(mMockContext.fileList()));
//        Context mMockContext = mock(MainActivity.class);
//        List<Route> expected = new ArrayList<>();
//        Route testRoute = new Route("rondje stad");
//        testRoute.addLocation(new Location("kees kroket", "2.4654645,6.2342323", "lekkere patatjes", null));
//        testRoute.setTotalDistance(2.3434f);
//        testRoute.setTotalTime(342342);
//        expected.add(testRoute);
//
//        /*
//        FileIO<ArrayList<Route>> fileIO = new FileIO<>();
//        ArrayList<Route> res = fileIO.readFileData(context, "routes.json",new TypeToken<ArrayList<Route>>(){}.getType());
//         */
//
//        if (mMockContext.getAssets() == null)
//            System.out.println("daar ga je");
//
//        FileIO<ArrayList<Route>> fileIO = new FileIO<>();
//        ArrayList<Route> res = fileIO.readFileData(mMockContext, "routes.json", new TypeToken<ArrayList<Route>>(){}.getType());
//
//        assertEquals(expected, res);
//
//    }

}
