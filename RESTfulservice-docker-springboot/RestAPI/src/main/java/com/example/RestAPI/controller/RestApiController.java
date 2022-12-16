package com.example.RestAPI.controller;

import com.example.RestAPI.domain.Details;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;

@RestController
public class RestApiController {

  public static final ObjectMapper MAPPER = new ObjectMapper();
  public static final String CUSTOMERS_FILE_PATH = "/etc/neerav/customers.json";

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/customers")
  public String getCustomers() {

    try {
      Details details = MAPPER.readValue(Paths.get(CUSTOMERS_FILE_PATH).toFile(), Details.class);

      // these 2 lines also work and get a list of customers
      // Map<String, List<Customer>> root = mapper.readValue(Paths.get(filePath).toFile(), new TypeReference<Map<String, List<Customer>>>(){});
      // List<Customer> customers = root.get("Customers");

      return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(details);
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Sorry, we are unable to serve your request";
    }
  }

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/customers/{custId}", method = RequestMethod.GET)
  public String getParticularCustomers(@PathVariable("custId") String custId) {

    try {
      Details details = MAPPER.readValue(Paths.get(CUSTOMERS_FILE_PATH).toFile(), Details.class);

      return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(details.getCustomers().stream()
              .filter(t -> custId.equals(t.getUserId())).findFirst().orElse(null));
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Sorry, we are unable to serve your request";
    }
  }

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/customers/{custId}/orders", method = RequestMethod.GET)
  public String getCustomerOrder(@PathVariable("custId") String custId) {

    try {
      Details details = MAPPER.readValue(Paths.get(CUSTOMERS_FILE_PATH).toFile(), Details.class);

      return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(details.getCustomers().stream()
              .filter(t -> custId.equals(t.getUserId())).findFirst().orElse(null).getOrders());
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Sorry, we are unable to serve your request";
    }
  }

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/customers/{custId}/orders/{orderId}", method = RequestMethod.GET)
  public String getCustomerParticularOrder(@PathVariable("custId") String custId,@PathVariable("orderId") String orderId) {

    try {
      Details details = MAPPER.readValue(Paths.get(CUSTOMERS_FILE_PATH).toFile(), Details.class);

      return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(details.getCustomers().stream()
              .filter(t -> custId.equals(t.getUserId())).findFirst().orElse(null).getOrders()
              .stream().filter(t ->orderId.equals(t.getOrderId())).findFirst().orElse(null));
    } catch (Exception ex) {
      ex.printStackTrace();
      return "Sorry, we are unable to serve your request";
    }
  }


}
