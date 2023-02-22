package StepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.LoginPage;
import pojo.ProductPage;
import utils.APIUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TestCase_stepDefs {

    ProductPage productPageRes;
    String body;
    String res;
    LoginPage loginPage;

    @Given("the user retrieves products list from {string}")
    public void theUserRetrievesProductsListFrom(String url) {
        productPageRes = APIUtils.sendGetRequest(url).as(ProductPage.class);
    }

    @Then("verify status code {string}")
    public void verifyStatusCode(String code) {
        System.out.println("Response code: " + productPageRes.getStatusCode());
    }

    @Then("store the products in a {string} txt file")
    public void storeTheProductsInAFile(String fileName) {
        File f = new File("/Users/Serhat/Desktop/" + fileName);
        try {
            PrintWriter output = new PrintWriter(f);
            for (int i = 0; i < productPageRes.getProducts().size(); i++) {
                output.print("Id: " + productPageRes.getProducts().get(i).getId() + "--");
                output.print("Brand: " + productPageRes.getProducts().get(i).getBrand() + "--");
                output.print("Name: " + productPageRes.getProducts().get(i).getName() + "--");
                output.print("Price: " + productPageRes.getProducts().get(i).getPrice() + "--");
                output.print("UserType: " + productPageRes.getProducts().get(i).getCategory().getUsertype() + "--");
                output.println("Category: " + productPageRes.getProducts().get(i).getCategory().getCategory());

            }

            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        }
    }

    @Then("store the brands in a {string} txt file")
    public void storeTheBrandsInAFile(String fileName) {
        File f = new File("/Users/Serhat/Desktop/" + fileName);
        try {
            PrintWriter output = new PrintWriter(f);
            for (int i = 0; i < productPageRes.getBrands().size(); i++) {
                output.print("Id: " + productPageRes.getBrands().get(i).getId() + ", ");
                output.println("Brand: " + productPageRes.getBrands().get(i).getBrand());
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        }
    }

    @Given("the user build the body to post")
    public void theUserBuildTheBodyToPost() {
        body = "{\"id\": 4114, \"name\": \"Asofman\", \"price\": \"00.1\", \"brand\": \"SG\", \"category\": {\"usertype\": {\"usertype\": \"Men\"}, \"category\": \"suit\"}";
    }

    @When("the user post the product to {string}")
    public void theUserPostTheProductTo(String url) {
        res = APIUtils.sendPostRequest(url, body).asString();
    }

    @Then("convert response into java")
    public void convertResponseIntoJava() {
        ObjectMapper objectMapper = APIUtils.getObjectMapper();
        try {
            productPageRes = objectMapper.readValue(res, ProductPage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @And("verify the response message")
    public void verifyTheResponseMessage() {
        System.out.println(productPageRes.getMessage());
    }

    @Given("the user build the body to put")
    public void theUserBuildTheBodyToPut() {
        body = "{\"id\": 4114, \"brand\": \"SG\"}";
    }

    @When("the user put the product to {string}")
    public void theUserPutTheProductTo(String url) {
        res = APIUtils.sendPutRequest(url, body, new HashMap<>(), "json").asString();
    }

    @Given("the user post the request to {string}")
    public void theUserPostTheRequestTo(String url) {
        res = APIUtils.sendPostRequest(url).asString();
        ObjectMapper objectMapper = APIUtils.getObjectMapper();
        try {
            productPageRes = objectMapper.readValue(res, ProductPage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("the user post the request to {string} with invalid details {string} {string}")
    public void theUserPostTheRequestToWithInvalidDetails(String url,String email, String password) throws JsonProcessingException {

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        APIUtils.setBaseURI(url);
        res = APIUtils.sendPostRequest(params).asString();

        ObjectMapper objectMapper = APIUtils.getObjectMapper();
        loginPage = objectMapper.readValue(res, LoginPage.class);

        System.out.println(loginPage.getStatusCode());
        System.out.println(loginPage.getMessage());

    }
}