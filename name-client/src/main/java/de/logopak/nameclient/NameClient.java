package de.logopak.nameclient;

import de.logopak.nameprovider.NameProvider;

public class NameClient {

  private final NameProvider nameProvider;

  public NameClient(NameProvider nameProvider) {
    this.nameProvider = nameProvider;
  }

  public void displayWelcomeMessage(Outputter outputter) {
    String message = String
        .format("“That there’s some good in this world, Mr. %s… and it’s worth fighting for.”", nameProvider.provideName());
    outputter.output(message);
  }

}
