package com.phonebook.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.phonebook.services.PhoneBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PhoneAppController {

    private static final Logger LOGGER = LogManager.getLogger(PhoneAppController.class);
    @Autowired
    private Gson gson;
    @Autowired
    private PhoneBookService bookService;

    @RequestMapping(value = "/fetchPhoneBook",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String fetchUsers() {
        JsonObject response = null;
        try {
            response = new JsonObject();
            LOGGER.info("Start of fetchUsers");
            JsonObject jo = bookService.fetchUsers();
            response.add("DATA", jo);
            response.addProperty("MESSAGE", "Data fetched successfully");
            response.addProperty("STATUS", "SUCCESS");
        } catch (Exception e) {
            LOGGER.error("Exception in fetching users ", e);
            response.addProperty("MESSAGE", "Data fetching failed");
            response.addProperty("STATUS", "FAIL");
        }
        LOGGER.info("End of fetchUsers");
        return gson.toJson(response);
    }

    @RequestMapping(value = "/saveUser",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveUser(@RequestBody String data) {
        JsonObject response = null;
        try {
            response = new JsonObject();
            LOGGER.info("Start of saveUser");
            LOGGER.info("req data "+data);
            JsonObject jo = bookService.saveUser(data);
            response.add("DATA", jo);
            response.addProperty("STATUS", "SUCCESS");
        } catch (Exception e) {
            LOGGER.error("Exception in fetching users ", e);
            response.addProperty("STATUS", "FAIL");

        }
        LOGGER.info("End of saveUser");
        return gson.toJson(response);
    }
    
      @RequestMapping(value = "/deleteUser",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@RequestBody String data) {
        JsonObject response = null;
        try {
            response = new JsonObject();
            LOGGER.info("Start of deleteUser");
            LOGGER.info("req data "+data);
            JsonObject jo = bookService.deleteUser(data);
            response.add("DATA", jo);
            response.addProperty("STATUS", "SUCCESS");
        } catch (Exception e) {
            LOGGER.error("Exception in fetching users ", e);
            response.addProperty("STATUS", "FAIL");

        }
        LOGGER.info("End of deleteUser");
        return gson.toJson(response);
    }
}
