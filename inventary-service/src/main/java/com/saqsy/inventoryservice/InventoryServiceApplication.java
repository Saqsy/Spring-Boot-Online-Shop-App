package com.saqsy.inventoryservice;

import com.saqsy.inventoryservice.model.Inventory;
import com.saqsy.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//        return args -> {
//
//            for (int i = 0 ; i < 1 ; i ++){
//                Inventory inventory = new Inventory();
//                inventory.setSkuCode("product_"+i);
//                inventory.setQuantity(100+i);
//
//                inventoryRepository.save(inventory);
//            }
//        };
//    }

}
