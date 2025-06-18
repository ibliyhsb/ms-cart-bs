package cl.duoc.ms_cart_bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.duoc.ms_cart_bs.clients.CartDbFeignClient;
import cl.duoc.ms_cart_bs.clients.CustomerDbFeignClient;
import cl.duoc.ms_cart_bs.clients.ProductDbFeignClient;
import cl.duoc.ms_cart_bs.model.dto.CartDTO;
import cl.duoc.ms_cart_bs.model.dto.CustomerDto;
import cl.duoc.ms_cart_bs.model.dto.ProductDTO;

@Service
public class CartService {

    @Autowired
    CartDbFeignClient cartDbFeignClient;

    @Autowired
    CustomerDbFeignClient customerDbFeignClient;

    @Autowired
    ProductDbFeignClient productDbFeignClient;

    public ResponseEntity<?> insertCart (Long idCustomer){
        ResponseEntity<CustomerDto> response = customerDbFeignClient.getCustomerById(idCustomer);

        if(response.getBody() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer does not exist");
        }

        else{           
            CartDTO cartDTO = new CartDTO();
            cartDTO.setIdCart(idCustomer);
            cartDTO.setIdCustomer(idCustomer);
            cartDTO.setProducts(null);
            cartDTO.setTotal(0);

            return cartDbFeignClient.insertCart(cartDTO);
        }

    }

    public CartDTO getCartById(Long idCart){
        return cartDbFeignClient.getCartById(idCart);
    }

   public ResponseEntity<String> insertProduct (Long idProduct, Long idCart){

       CartDTO response = cartDbFeignClient.getCartById(idCart);
       ResponseEntity<ProductDTO> response2 = productDbFeignClient.getProductById(idProduct);

           if(response == null){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The cart does not exist");
           }

           else if(response2.getBody() == null){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product does not exist");
           }
           else {
               int price = response2.getBody().getPrice();
               String productName = response2.getBody().getProductName();
               return cartDbFeignClient.insertProduct(price, productName, idCart);
       }
   }
   
   public ResponseEntity<String> deleteProduct(String productName, Long idCart){
       return cartDbFeignClient.deleteProduct(productName, idCart);
   }

}