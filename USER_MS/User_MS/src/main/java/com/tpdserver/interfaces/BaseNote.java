package com.tpdserver.interfaces;

import javax.ejb.Remote;

@Remote
public interface BaseNote {
    int getId();
    void setId(int id);
    String getTitle();
    void setTitle(String title);
    String getContent();
    void setContent(String content);
    int getUserId();
    void setUserId(int userId);
}
