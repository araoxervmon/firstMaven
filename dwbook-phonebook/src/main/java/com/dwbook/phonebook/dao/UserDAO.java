package com.dwbook.phonebook.dao;
  import org.skife.jdbi.v2.sqlobject.*;
  public interface UserDAO {
	    @SqlQuery("select count(*) from user where (username = :username or email= :username) and password = :password")
    int getUser(@Bind("username") String username, @Bind("password") String password);
  }