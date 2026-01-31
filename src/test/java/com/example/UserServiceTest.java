package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

  @Test
  void findUser_withoutPassword_throwsHelpfulMessage() {
    System.clearProperty("db.password");
    UserServiceException ex = assertThrows(UserServiceException.class,
        () -> new UserService().findUser("admin"));
    assertTrue(ex.getMessage().contains("Database password is not configured"));
  }

  @Test
  void findUser_withPassword_wrapsSqlExceptionWhenNoDriver() {
    System.setProperty("db.password", "dummy");
    UserServiceException ex = assertThrows(UserServiceException.class,
        () -> new UserService().findUser("admin"));
    assertNotNull(ex.getCause());
  }

  @Test
  void deleteUser_withPassword_wrapsSqlExceptionWhenNoDriver() {
    System.setProperty("db.password", "dummy");
    UserServiceException ex = assertThrows(UserServiceException.class,
        () -> new UserService().deleteUser("admin"));
    assertNotNull(ex.getCause());
  }
}