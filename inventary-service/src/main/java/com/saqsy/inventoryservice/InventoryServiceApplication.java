package com.saqsy.inventoryservice;

import com.saqsy.inventoryservice.model.Inventory;
import com.saqsy.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//        return args -> {
//            Inventory inventory = new Inventory();
//            inventory.setSkuCode("product_1");
//            inventory.setQuantity(100);
//
//            Inventory inventory2 = new Inventory();
//            inventory.setSkuCode("product_2");
//            inventory.setQuantity(129);
//            inventoryRepository.save(inventory);
//            inventoryRepository.save(inventory2);
//        };
//    }

}
