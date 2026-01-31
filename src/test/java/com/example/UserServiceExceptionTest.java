package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

class UserServiceExceptionTest {
  @Test public void messageCtor() { assertEquals("msg", new UserServiceException("msg").getMessage()); }
  @Test public void messageAndCauseCtor() {
    Throwable cause = new RuntimeException("c");
    UserServiceException ex = new UserServiceException("msg", cause);
    assertSame(cause, ex.getCause());
  }
}