package de.logopak.nameclient;

import de.logopak.nameprovider.NameProvider;
import de.logopak.nameprovider.internal.DefaultNameProvider;

public class NameClient {

  private NameProvider nameProvider = new DefaultNameProvider();

  public void displayWelcomeMessage(Outputter outputter) {
    String message = String
        .format("“That there’s some good in this world, Mr. %s… and it’s worth fighting for.”", nameProvider.provideName());
    outputter.output(message);
  }

}
