package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({
        "statusCode",
        "products",
        "message"
})
@JsonIgnoreProperties
public class ProductPage {
    @JsonProperty("responseCode")
    private int statusCode;
    @JsonProperty("products")
    private List<Product> products;

    @JsonProperty("message")
    private String message;

    @JsonProperty("brands")
    private List<Brands> brands;



}
