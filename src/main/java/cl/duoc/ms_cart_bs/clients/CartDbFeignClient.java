package cl.duoc.ms_cart_bs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.duoc.ms_cart_bs.model.dto.CartDTO;

@FeignClient(name = "ms-cart-db", url = "http://localhost:8180")
public interface CartDbFeignClient {

    
    @GetMapping("/api/Cart/getCartById/{idCart}")
    public CartDTO getCartById(@PathVariable("idCart") Long idCart);
    
    @PostMapping("/api/Cart/insertCart")
    public ResponseEntity<String> insertCart (@RequestBody CartDTO cartDTO);
    
    @PostMapping("/api/Cart/insertProduct/{price}/{productName}/{idCart}")
    public ResponseEntity<String> insertProduct (@PathVariable("price") int price, @PathVariable("productName") String productName, @PathVariable("idCart") Long idCart);

    @DeleteMapping("/api/Cart/deleteProduct/{productName}/{idCart}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productName") String productName, @PathVariable("idCart") Long idCart);

}
