package de.logopak.nameprovider.internal;

import de.logopak.nameprovider.NameProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class NameProviderActivator implements BundleActivator {

  private ServiceRegistration<NameProvider> serviceRegistration;

  @Override
  public void start(BundleContext context) {
    serviceRegistration = context.registerService(NameProvider.class, new DefaultNameProvider(), null);
  }

  @Override
  public void stop(BundleContext context) {
    serviceRegistration.unregister();
  }
}
