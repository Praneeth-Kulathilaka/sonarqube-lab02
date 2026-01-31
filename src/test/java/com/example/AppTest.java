package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {
  @Test
  void main_executesAndFailsWithoutDbDriver() {
    System.setProperty("db.password", "dummy");
    assertThrows(Exception.class, () -> App.main(new String[0]));
  }
}