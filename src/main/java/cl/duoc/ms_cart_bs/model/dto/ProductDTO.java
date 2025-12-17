package cl.duoc.ms_cart_bs.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("nombre")
    private String nombre;
    
    @JsonProperty("price")
    private Double precioCLP;
    
    @JsonProperty("imagen")
    private String imagen;
    
    @JsonProperty("descripcion")
    private String descripcion;
}