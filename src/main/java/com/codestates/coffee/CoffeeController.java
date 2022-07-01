package com.codestates.coffee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/coffees",produces = MediaType.APPLICATION_JSON_VALUE)
public class CoffeeController {
    @PostMapping
    public String postCoffee(@RequestParam("korName") String korName,
                             @RequestParam("engName") String engName,
                             @RequestParam("price") int price) {
        System.out.println("# email: " + korName);
        System.out.println("# email: " + engName);
        System.out.println("# email: " + price);

        String response =
                "{\"" +
                        "email\":\"" + korName + "\"," +
                        "\"name\":\"" + engName + "\",\"" +
                        "phone\":\"" + price +
                        "\"}";
        return response;

    }


    @GetMapping("/{coffee-id}")
    public String getCoffee(@PathVariable("coffee-id") long coffeeId){
        System.out.println("# orderId: " + coffeeId);


        return null;

    }

    public String getCoffees(){
        System.out.println("# get coffees");

        return null;
    }


}