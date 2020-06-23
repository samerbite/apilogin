package cl.titicoctel.apilogin.cucumber.util;

import cl.titicoctel.apilogin.Dto.ErroresNegocioDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;

public class Utilitarios {
    public static String createJsonFromObject(Object object, Class<?> clase){
        Gson sgon = new Gson();
        return sgon.toJson(clase.cast(object));
    }

    public static String obtenerJson(Object objeto){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(objeto);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = new JsonParser().parse(jsonInString);
            return gson.toJson(jsonElement);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return "ERROR";
        }
    }

    public static ErroresNegocioDto obtenerErrorDesdeJson(String jsonString){
        try {
            Gson g = new Gson();
            ErroresNegocioDto error = g.fromJson(jsonString, ErroresNegocioDto.class);
            return error;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ErroresNegocioDto.builder().build();
        }
    }
}
