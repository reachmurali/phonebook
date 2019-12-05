package com.phonebook.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phonebook.bean.User;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppConfig implements WebMvcConfigurer {

    public AppConfig() {
    }

    
    @Bean
    public User getUser() {
        return new User();
    }

    @Bean
    public Gson getGson() {
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.serializeNulls().create();
        return gson;
    }

}
