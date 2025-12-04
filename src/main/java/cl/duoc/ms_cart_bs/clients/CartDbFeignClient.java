package cl.duoc.ms_cart_bs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.duoc.ms_cart_bs.model.dto.CartDTO;
import cl.duoc.ms_cart_bs.model.dto.CartItemDTO;

@FeignClient(name = "ms-cart-db", url = "http://localhost:8180")
public interface CartDbFeignClient {

    
    @GetMapping("/api/Cart/getCartById/{idCart}")
    public CartDTO getCartById(@PathVariable("idCart") Long idCart);
    
    @PostMapping("/api/Cart/insertCart")
    public CartDTO insertCart (@RequestBody CartDTO cartDTO);
    
    @PostMapping("/api/Cart/insertProduct/{idCart}")
    public CartDTO insertProduct (@PathVariable("idCart") Long idCart, @RequestBody CartItemDTO item);

    @PutMapping("/api/Cart/updateQuantity/{idCart}/{productId}")
    public CartDTO updateQuantity(@PathVariable("idCart") Long idCart, @PathVariable("productId") Long productId, @RequestBody int quantity);

    @DeleteMapping("/api/Cart/deleteProduct/{idCart}/{productId}")
    public CartDTO deleteProduct(@PathVariable("idCart") Long idCart, @PathVariable("productId") Long productId);

}
