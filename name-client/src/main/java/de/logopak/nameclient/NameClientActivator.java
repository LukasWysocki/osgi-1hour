package de.logopak.nameclient;

import de.logopak.nameprovider.NameProvider;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class NameClientActivator implements BundleActivator {

  private ScheduledExecutorService scheduler;
  private ServiceTracker namedProviderTracker;

  @Override
  public void start(BundleContext context) {
    System.out.println("Starting bundle " + context.getBundle().getSymbolicName());
    scheduler = Executors.newSingleThreadScheduledExecutor();

    namedProviderTracker = new ServiceTracker<NameProvider, ScheduledFuture>(context, NameProvider.class, null) {
      @Override
      public ScheduledFuture addingService(ServiceReference<NameProvider> reference) {
        NameClient nameClient = new NameClient(context.getService(reference));
        Runnable doWelcome = () -> {
          nameClient.displayWelcomeMessage(
              message -> System.out.println(message)
          );
        };
        System.out.println("Got NameProvider, schedule Welcome..");
        return scheduler.scheduleAtFixedRate(doWelcome, 2, 7, TimeUnit.SECONDS);
      }

      @Override
      public void removedService(ServiceReference<NameProvider> reference, ScheduledFuture scheduledFuture) {
        System.out.println("Lost NameProvider, cancel Welcome..");
        scheduledFuture.cancel(false);
        context.ungetService(reference);
      }
    };

    namedProviderTracker.open();

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
    namedProviderTracker.close();
    System.out.println("Stopped bundle " + context.getBundle().getSymbolicName());
  }
}
