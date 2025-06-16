package cl.duoc.ms_cart_bs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsCartBsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCartBsApplication.class, args);
	}

}
