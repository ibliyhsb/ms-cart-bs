package cl.duoc.ms_cart_bs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.ms_cart_bs.model.dto.ProductDTO;

@FeignClient(name = "ms-products-db", url = "http://localhost:8280")
public interface ProductDbFeignClient {

    @GetMapping("/api/products/GetProductById/{idProduct}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "idProduct") Long idProduct);
}
