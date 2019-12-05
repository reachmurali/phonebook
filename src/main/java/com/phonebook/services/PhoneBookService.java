package com.phonebook.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.phonebook.bean.User;
import com.phonebook.dao.PhoneBookDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PhoneBookService {

    @Autowired
    private Gson gson;

    @Autowired
    private PhoneBookDAO dao;
    private static final Logger LOGGER = LogManager.getLogger(PhoneBookService.class);

    public JsonObject saveUser(String data) {
        JsonObject jsonObject = null;

        try {
            jsonObject = new JsonObject();
            User user = gson.fromJson(data, User.class);
            User u = dao.save(user);
            Sort sort = new Sort(Sort.Direction.ASC, "name");
            Iterable<User> users = dao.findAll(sort);
            JsonElement element = gson.toJsonTree(users);
            jsonObject.add("USERS", element);
            jsonObject.addProperty("MESSAGE", "user details saved successfully");
            jsonObject.addProperty("Status", "success");
        } catch (Exception e) {
            LOGGER.error(e);
            jsonObject.addProperty("MESSAGE", "user details saving failed");
            jsonObject.addProperty("Status", "fail");
        }
        return jsonObject;
    }

    public JsonObject fetchUsers() {
        JsonObject jsonObject = null;

        try {
            jsonObject = new JsonObject();
            Sort sort = new Sort(Sort.Direction.ASC, "name");
            Iterable<User> users = dao.findAll(sort);
            JsonElement element = gson.toJsonTree(users);
            jsonObject.add("DATA", element);
            jsonObject.addProperty("MESSAGE", "user details fetched successfully");
            jsonObject.addProperty("Status", "success");
        } catch (Exception e) {
            LOGGER.error(e);
            jsonObject.addProperty("MESSAGE", "user details fatching failed");
            jsonObject.addProperty("Status", "fail");
        }
        return jsonObject;
    }

    public JsonObject deleteUser(String data) {
        JsonObject jsonObject = null;

        try {
            jsonObject = new JsonObject();
            User user = gson.fromJson(data, User.class);
            System.out.println(user);
            dao.delete(user);
            jsonObject.addProperty("MESSAGE", "user deleted successfully");
            jsonObject.addProperty("STATUS", "success");
        } catch (Exception e) {
            LOGGER.error(e);
            jsonObject.addProperty("MESSAGE", "user not deleted");
            jsonObject.addProperty("STATUS", "fail");
        }
        return jsonObject;
    }
}
