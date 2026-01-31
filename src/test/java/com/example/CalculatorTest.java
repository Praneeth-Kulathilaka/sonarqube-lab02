package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
  @Test void add() { assertEquals(15, new Calculator().calculate(10, 5, "add")); }
  @Test void addAgain() { assertEquals(15, new Calculator().calculate(10, 5, "add-again")); }
  @Test void sub() { assertEquals(5, new Calculator().calculate(10, 5, "sub")); }
  @Test void subAgain() { assertEquals(5, new Calculator().calculate(10, 5, "sub-again")); }
  @Test void mul() { assertEquals(50, new Calculator().calculate(10, 5, "mul")); }
  @Test void divByZero() { assertEquals(0, new Calculator().calculate(10, 0, "div")); }
  @Test void div() { assertEquals(2, new Calculator().calculate(10, 5, "div")); }
  @Test void mod() { assertEquals(1, new Calculator().calculate(10, 3, "mod")); }
  @Test void pow() { assertEquals(1000, new Calculator().calculate(10, 3, "pow")); }
  @Test void unknownOp() { assertEquals(0, new Calculator().calculate(10, 5, "???")); }
  @Test void addAgainMethod() { assertEquals(3, new Calculator().addAgain(1, 2)); }
}