package com.retrofitreactive.endpoint.deserializer;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class JsonDeserializer implements com.google.gson.JsonDeserializer<Response>{
    @Override
    public FacturaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Response facturaResponse = gson.fromJson(json, Response.class);
        JsonObject objResponse = json.getAsJsonObject();
        evaluateJson(objResponse, facturaResponse);
        return facturaResponse;
    }

    private void evaluateJson(JsonObject jsonObject, FacturaResponse facturaResponse){
        //Setea aqui los valores que obtengas del json
    }
}
