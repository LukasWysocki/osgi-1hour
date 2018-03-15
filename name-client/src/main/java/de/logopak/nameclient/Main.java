package de.logopak.nameclient;

public class Main {

  public static void main(String[] args) {
    new NameClient().displayWelcomeMessage(message -> System.out.println(message));
  }
}
