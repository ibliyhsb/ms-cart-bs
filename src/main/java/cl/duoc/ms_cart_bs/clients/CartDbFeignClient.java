package cl.duoc.ms_cart_bs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.ms_cart_bs.model.dto.CartDTO;
import cl.duoc.ms_cart_bs.model.dto.CartItemDTO;

@FeignClient(name = "ms-cart-db", url = "http://localhost:8180")
public interface CartDbFeignClient {

    @GetMapping("/api/Cart/getCartById/{idCart}")
    ResponseEntity<CartDTO> getCartById(@PathVariable("idCart") Long idCart);

    @GetMapping("/api/Cart/getByCustomerId/{customerId}")
    ResponseEntity<CartDTO> getByCustomerId(@PathVariable("customerId") Long customerId);

    @PostMapping("/api/Cart/insertCart/{idCustomer}")
    ResponseEntity<String> insertCart(@PathVariable("idCustomer") Long idCustomer);

    @PostMapping("/api/Cart/addItem/{idCart}")
    ResponseEntity<String> addItem(@PathVariable("idCart") Long idCart, @RequestBody CartItemDTO item);

    @PutMapping("/api/Cart/updateQuantity/{idCart}/{itemId}/{quantity}")
    ResponseEntity<String> updateQuantity(
            @PathVariable("idCart") Long idCart,
            @PathVariable("itemId") String itemId,
            @PathVariable("quantity") Integer quantity);

    @DeleteMapping("/api/Cart/removeItem/{idCart}/{itemId}")
    ResponseEntity<String> removeItem(
            @PathVariable("idCart") Long idCart,
            @PathVariable("itemId") String itemId);

    @DeleteMapping("/api/Cart/clear/{idCart}")
    ResponseEntity<String> clearCart(@PathVariable("idCart") Long idCart);

    @PostMapping("/api/Cart/applyCoupon/{idCart}/{couponCode}/{discount}")
    ResponseEntity<String> applyCoupon(
            @PathVariable("idCart") Long idCart,
            @PathVariable("couponCode") String couponCode,
            @PathVariable("discount") Double discount);
}