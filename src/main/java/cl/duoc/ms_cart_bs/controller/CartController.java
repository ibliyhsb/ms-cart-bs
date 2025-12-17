package cl.duoc.ms_cart_bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.ms_cart_bs.model.dto.AddCartItemRequest;
import cl.duoc.ms_cart_bs.service.CartService;

@RestController
@RequestMapping("/api/Cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/getCartById/{idCart}")
    public ResponseEntity<?> getCartById(@PathVariable("idCart") Long idCart) {
        return ResponseEntity.ok(cartService.getCartById(idCart));
    }

    @GetMapping("/getByCustomerId/{customerId}")
    public ResponseEntity<?> getByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(cartService.getByCustomerId(customerId));
    }

    @PostMapping("/insertCart/{idCustomer}")
    public ResponseEntity<String> insertCart(@PathVariable("idCustomer") Long idCustomer) {
        return ResponseEntity.ok(cartService.insertCart(idCustomer));
    }

    @PostMapping("/addItem/{idCart}")
    public ResponseEntity<?> addItem(
            @PathVariable("idCart") Long idCart,
            @RequestBody AddCartItemRequest request) {
        return ResponseEntity.ok(cartService.addItem(idCart, request));
    }

    @PutMapping("/updateQuantity/{idCart}/{itemId}/{quantity}")
    public ResponseEntity<?> updateQuantity(
            @PathVariable("idCart") Long idCart,
            @PathVariable("itemId") String itemId,
            @PathVariable("quantity") Integer quantity) {
        return ResponseEntity.ok(cartService.updateQuantity(idCart, itemId, quantity));
    }

    @DeleteMapping("/removeItem/{idCart}/{itemId}")
    public ResponseEntity<String> removeItem(
            @PathVariable("idCart") Long idCart,
            @PathVariable("itemId") String itemId) {
        return ResponseEntity.ok(cartService.removeItem(idCart, itemId));
    }

    @DeleteMapping("/clear/{idCart}")
    public ResponseEntity<String> clearCart(@PathVariable("idCart") Long idCart) {
        return ResponseEntity.ok(cartService.clearCart(idCart));
    }

    @PostMapping("/applyCoupon/{idCart}/{couponCode}")
    public ResponseEntity<?> applyCoupon(
            @PathVariable("idCart") Long idCart,
            @PathVariable("couponCode") String couponCode) {
        return ResponseEntity.ok(cartService.applyCoupon(idCart, couponCode));
    }
}