package cl.duoc.ms_cart_bs.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartItemRequest {

    @JsonProperty(value = "productCode")
    private String productCode;

    @JsonProperty(value = "quantity")
    private Integer quantity;

    @JsonProperty(value = "size")
    private String size;

    @JsonProperty(value = "personalizationMessage")
    private String personalizationMessage;
}