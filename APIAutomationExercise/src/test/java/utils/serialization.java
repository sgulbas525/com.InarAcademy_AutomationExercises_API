package utils;

import com.google.gson.Gson;
import pojo.LoginPage;

import java.io.*;

public class serialization {
    public static void main(String[] args) throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.setEmail("qweqwe");
        loginPage.setPassword("Qweqwe");
        loginPage.setMessage("QWeqweq");
        loginPage.setStatusCode("2123");



        Gson gson =new Gson();
        String json = gson.toJson(loginPage);
        System.out.println(json);

    }
}
