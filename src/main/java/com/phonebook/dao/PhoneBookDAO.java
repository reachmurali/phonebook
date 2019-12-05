package com.phonebook.dao;

import com.phonebook.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneBookDAO extends JpaRepository<User, Integer> {
    
}
