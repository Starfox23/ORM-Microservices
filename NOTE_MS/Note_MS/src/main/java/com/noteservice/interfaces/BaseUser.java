package com.noteservice.interfaces;

import javax.ejb.Remote;

@Remote
public interface BaseUser {
    int getId();
    void setId(int id);
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
}
