package cl.duoc.ms_cart_bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_cart_bs.service.CartService;
import feign.FeignException.FeignClientException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/Cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/getCartById/{idCart}")
    public ResponseEntity<?> getCartById(@PathVariable("idCart") Long idCart){
        try {
        return ResponseEntity.ok(cartService.getCartById(idCart));}
        catch(FeignClientException feignClientException){
            return ResponseEntity.status(feignClientException.status()).body(feignClientException.contentUTF8());
      }
    }

    @PostMapping("/insertCart/{idCustomer}")
    public ResponseEntity<?> insertCart (@PathVariable("idCustomer") Long idCustomer) {    
        try{   
            return cartService.insertCart(idCustomer);
        }
        catch(FeignClientException feignClientException){
            return ResponseEntity.status(feignClientException.status()).body(feignClientException.contentUTF8());
      }
        
    }

    @PostMapping("/insertProduct/{idProduct}/{idCart}")
    public ResponseEntity<String> insertProduct (@PathVariable("idProduct") Long idProduct, @PathVariable("idCart") Long idCart) {
        return cartService.insertProduct(idProduct, idCart);
    }

    @DeleteMapping("/deleteProduct/{productName}/{idCart}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productName") String productName, @PathVariable("idCart") Long idCart){
        try{
            return cartService.deleteProduct(productName, idCart);
        }
        catch(FeignClientException feignClientException){
            return ResponseEntity.status(feignClientException.status()).body(feignClientException.contentUTF8());
        }
    }
    
    
}
