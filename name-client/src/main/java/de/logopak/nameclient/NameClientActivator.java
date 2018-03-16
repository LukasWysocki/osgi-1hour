package de.logopak.nameclient;

import de.logopak.nameprovider.NameProvider;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class NameClientActivator implements BundleActivator {

  private ScheduledExecutorService scheduler;
  private NameProvider nameProvider;
  private ServiceReference nameproviderReference;

  @Override
  public void start(BundleContext context) {
    System.out.println("Starting bundle " + context.getBundle().getSymbolicName());
    scheduler = Executors.newSingleThreadScheduledExecutor();

    nameproviderReference = context.getServiceReference(NameProvider.class);
    nameProvider = (NameProvider) context.getService(nameproviderReference);

    NameClient nameClient = new NameClient(nameProvider);
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
    try {
      scheduler.awaitTermination(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.err.println("InterruptedException, while stopping scheduler in: " + context.getBundle().getSymbolicName());
      // ignore, and continue
    }
    context.ungetService(nameproviderReference);
    System.out.println("Stopped bundle " + context.getBundle().getSymbolicName());
  }
}
