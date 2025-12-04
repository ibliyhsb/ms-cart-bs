package cl.duoc.ms_cart_bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_cart_bs.model.dto.CartDTO;
import cl.duoc.ms_cart_bs.model.dto.CartItemDTO;
import cl.duoc.ms_cart_bs.service.CartService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
public class CartController {

    @Autowired
    CartService cartService;

    // Obtener carrito por ID
    @GetMapping("/getCartById/{idCart}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long idCart) {
        CartDTO cart = cartService.getCartById(idCart);
        return ResponseEntity.ok(cart);
    }

    // Crear carrito nuevo - Retorna CartDTO
    @PostMapping("/insertCart/{idCustomer}")
    public ResponseEntity<CartDTO> insertCart(@PathVariable String idCustomer) {
        CartDTO cart = cartService.createCart(idCustomer);
        return ResponseEntity.ok(cart);
    }

    // Agregar producto - Recibe CartItemDTO en body
    @PostMapping("/insertProduct/{idCart}")
    public ResponseEntity<CartDTO> insertProduct(
        @PathVariable Long idCart,
        @RequestBody CartItemDTO item
    ) {
        CartDTO cart = cartService.addProduct(idCart, item);
        return ResponseEntity.ok(cart);
    }

    // Actualizar cantidad
    @PutMapping("/updateQuantity/{idCart}/{productId}")
    public ResponseEntity<CartDTO> updateQuantity(
        @PathVariable Long idCart,
        @PathVariable Long productId,
        @RequestBody Map<String, Integer> body
    ) {
        int quantity = body.get("quantity");
        CartDTO cart = cartService.updateQuantity(idCart, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    // Eliminar producto - Usa productId
    @DeleteMapping("/deleteProduct/{idCart}/{productId}")
    public ResponseEntity<CartDTO> deleteProduct(
        @PathVariable Long idCart,
        @PathVariable Long productId
    ) {
        CartDTO cart = cartService.deleteProduct(idCart, productId);
        return ResponseEntity.ok(cart);
    }
    
}
