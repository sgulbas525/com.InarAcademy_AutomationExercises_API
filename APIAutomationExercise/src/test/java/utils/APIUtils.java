package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.StringReader;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtils {
    private static RequestSpecification requestSpecification;
    private static Response response;

    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }


    public static Response sendPutRequest(String endpoint, String payload, Map<String, String> header, String contentType) {
        setBaseURI(endpoint);
        ContentType type = contentType.equalsIgnoreCase("JSON") ? ContentType.JSON : ContentType.XML;
        return given().contentType(type).body(payload).headers(header).put();
    }


    public static Response sendGetRequest(String url) {
        return given().expect().defaultParser(Parser.JSON).when().get(url);
    }


    public static String prettyPrintJson(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader jsonReader = new JsonReader(new StringReader(jsonString));
        jsonReader.setLenient(true);
        JsonElement jsonElement = gson.fromJson(jsonReader, JsonElement.class);
        return gson.toJson(jsonElement);
    }

    public static RequestSpecification getRequestSpecification() {
        requestSpecification = given().contentType(ContentType.JSON);
        return requestSpecification;
    }

    public static Response sendPostRequest(String url, String payload) {
        return given().spec(requestSpecification).contentType(ContentType.JSON).body(payload).post(url);
    }

    public static Response sendPostRequest(String url) {
        return RestAssured.given().contentType(ContentType.JSON).post(url);
    }
    public static Response sendPostRequest(Map<String, String> params){
        return RestAssured.given().queryParams(params).contentType(ContentType.JSON).post();
    }


    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }




}

