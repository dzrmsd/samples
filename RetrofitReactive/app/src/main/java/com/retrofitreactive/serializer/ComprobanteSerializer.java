package com.retrofitreactive.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.List;

public class ComprobanteSerializer implements JsonSerializer<Pojo> {
    @Override
    public JsonElement serialize(Comprobante src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        return json;
    }
}
