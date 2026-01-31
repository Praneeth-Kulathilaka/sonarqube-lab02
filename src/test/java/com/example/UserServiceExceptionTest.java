package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceExceptionTest {
  @Test void messageCtor() { assertEquals("msg", new UserServiceException("msg").getMessage()); }
  @Test void messageAndCauseCtor() {
    Throwable cause = new RuntimeException("c");
    UserServiceException ex = new UserServiceException("msg", cause);
    assertSame(cause, ex.getCause());
  }
}