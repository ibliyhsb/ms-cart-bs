package cl.duoc.ms_cart_bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_cart_bs.clients.CartDbFeignClient;
import cl.duoc.ms_cart_bs.model.dto.CartDTO;
import cl.duoc.ms_cart_bs.model.dto.CartItemDTO;

@Service
public class CartService {

    @Autowired
    CartDbFeignClient cartDbFeignClient;

    // Crear carrito nuevo
    public CartDTO createCart(String idCustomer) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setIdCustomer(Long.parseLong(idCustomer));
        cartDTO.setProducts(null);
        cartDTO.setTotal(0);
        
        return cartDbFeignClient.insertCart(cartDTO);
    }

    // Obtener carrito por ID
    public CartDTO getCartById(Long idCart) {
        return cartDbFeignClient.getCartById(idCart);
    }

    // Agregar producto al carrito
    public CartDTO addProduct(Long idCart, CartItemDTO item) {
        return cartDbFeignClient.insertProduct(idCart, item);
    }

    // Actualizar cantidad de un producto
    public CartDTO updateQuantity(Long idCart, Long productId, int quantity) {
        return cartDbFeignClient.updateQuantity(idCart, productId, quantity);
    }
   
    // Eliminar producto del carrito
    public CartDTO deleteProduct(Long idCart, Long productId) {
        return cartDbFeignClient.deleteProduct(idCart, productId);
    }

}