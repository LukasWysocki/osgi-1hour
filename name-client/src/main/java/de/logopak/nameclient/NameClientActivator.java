package de.logopak.nameclient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class NameClientActivator implements BundleActivator {

  ScheduledExecutorService scheduler;

  @Override
  public void start(BundleContext context) {
    System.out.println("Starting bundle " + context.getBundle().getSymbolicName());
    scheduler = Executors.newSingleThreadScheduledExecutor();

    NameClient nameClient = new NameClient();
    Runnable doWelcome = () -> {
      nameClient.displayWelcomeMessage(
          message -> System.out.println(message)
      );
    };
    scheduler.scheduleAtFixedRate(doWelcome, 2, 7, TimeUnit.SECONDS);
  }

  @Override
  public void stop(BundleContext context) {
    scheduler.shutdown();
    System.out.println("Stopped bundle " + context.getBundle().getSymbolicName());
  }
}
