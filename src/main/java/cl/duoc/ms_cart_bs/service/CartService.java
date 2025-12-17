package cl.duoc.ms_cart_bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.duoc.ms_cart_bs.clients.CartDbFeignClient;
import cl.duoc.ms_cart_bs.model.dto.*;

import java.util.UUID;

@Service
public class CartService {

    @Autowired
    CartDbFeignClient cartDbFeignClient;

    @Autowired
    RestTemplate restTemplate;

    private static final String PRODUCTS_API = "http://localhost:8282/api/products";

    public CartDTO getCartById(Long idCart) {
        return cartDbFeignClient.getCartById(idCart).getBody();
    }

    public CartDTO getByCustomerId(Long customerId) {
        CartDTO cart = cartDbFeignClient.getByCustomerId(customerId).getBody();
        if (cart == null) {
            insertCart(customerId);
            cart = cartDbFeignClient.getByCustomerId(customerId).getBody();
        }
        return cart;
    }

    public String insertCart(Long idCustomer) {
        return cartDbFeignClient.insertCart(idCustomer).getBody();
    }

    public CartDTO addItem(Long idCart, AddCartItemRequest request) {
        // Obtener información del producto
        ProductDTO product = getProductByCode(request.getProductCode());
        
        if (product == null) {
            throw new RuntimeException("Producto no encontrado: " + request.getProductCode());
        }

        // Validar que el precio no sea null
        if (product.getPrecioCLP() == null) {
            throw new RuntimeException("El producto " + request.getProductCode() + " no tiene precio definido. Producto: " + product.toString());
        }

        // Crear item del carrito
        CartItemDTO item = new CartItemDTO();
        item.setId(UUID.randomUUID().toString());
        item.setProductCode(request.getProductCode());
        item.setProductName(product.getNombre());
        item.setPrice(product.getPrecioCLP());
        item.setQuantity(request.getQuantity());
        item.setSize(request.getSize());
        item.setPersonalizationMessage(request.getPersonalizationMessage());
        item.setImageUrl(product.getImagen());
        item.setSubtotal(product.getPrecioCLP() * request.getQuantity());

        // Guardar en DB
        cartDbFeignClient.addItem(idCart, item);

        return getCartById(idCart);
    }

    public CartDTO updateQuantity(Long idCart, String itemId, Integer quantity) {
        cartDbFeignClient.updateQuantity(idCart, itemId, quantity);
        return getCartById(idCart);
    }

    public String removeItem(Long idCart, String itemId) {
        return cartDbFeignClient.removeItem(idCart, itemId).getBody();
    }

    public String clearCart(Long idCart) {
        return cartDbFeignClient.clearCart(idCart).getBody();
    }

    public CartDTO applyCoupon(Long idCart, String couponCode) {
        CartDTO cart = getCartById(idCart);
        
        Double discount = 0.0;
        
        if ("FELICESS0".equalsIgnoreCase(couponCode)) {
            discount = cart.getSubtotal() * 0.10;
            cartDbFeignClient.applyCoupon(idCart, couponCode, discount);
        } else {
            throw new RuntimeException("Cupón inválido");
        }

        return getCartById(idCart);
    }

    private ProductDTO getProductByCode(String productCode) {
        try {
            return restTemplate.getForObject(
                PRODUCTS_API + "/GetProductByCode/" + productCode, 
                ProductDTO.class
            );
        } catch (Exception e) {
            return null;
        }
    }
}