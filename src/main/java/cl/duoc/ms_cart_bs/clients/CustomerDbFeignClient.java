package cl.duoc.ms_cart_bs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.ms_cart_bs.model.dto.CustomerDto;

@FeignClient(name = "ms-customers-db", url = "http://localhost:8081")
public interface CustomerDbFeignClient {

    
    @GetMapping("/api/customers/GetCustomerById/{idCustomer}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("idCustomer") Long idCustomer);

}
