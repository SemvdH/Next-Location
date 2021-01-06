package com.a1.nextlocation;

import com.a1.nextlocation.json.GeometryDecoder;
import com.google.gson.JsonArray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeometryDecoderTest {

    @Test
    public void geometryDecoderTest(){
        String encodedGeometryTest = "";

        JsonArray expected = new JsonArray();

        

        assertEquals(expected, GeometryDecoder.decodeGeometry(encodedGeometryTest, true));
    }
}
