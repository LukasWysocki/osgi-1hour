package de.logopak.nameclient;

import de.logopak.nameprovider.NameProvider;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NameClientOrchestrator {

  private volatile ScheduledExecutorService scheduler;

  public void setNameProvider(NameProvider nameProvider) {
    System.out.println("Got NameProvider, schedule Welcome..");

    scheduler = Executors.newSingleThreadScheduledExecutor();

    NameClient nameClient = new NameClient(nameProvider);
    Runnable doWelcome = () -> {
      nameClient.displayWelcomeMessage(
          message -> System.out.println(message)
      );
    };
    scheduler.scheduleAtFixedRate(doWelcome, 2, 7, TimeUnit.SECONDS);
  }

  public void unsetNameProvider(NameProvider nameProvider) {
    System.out.println("Lost NameProvider, cancel Welcome..");
    scheduler.shutdown();
    try {
      scheduler.awaitTermination(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.err.println("InterruptedException, while stopping scheduler");
      // ignore, and continue
    }
  }
}
