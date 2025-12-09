package cl.duoc.ms_cart_bs.model.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    @JsonProperty(value = "id_cart")
    private Long idCart;

    @JsonProperty(value = "id_customer")
    private Long idCustomer;

    @JsonProperty(value = "items")
    private List<CartItemDTO> items;

    @JsonProperty(value = "subtotal")
    private Double subtotal;

    @JsonProperty(value = "discount")
    private Double discount;

    @JsonProperty(value = "total")
    private Double total;

    @JsonProperty(value = "appliedCoupon")
    private String appliedCoupon;
}