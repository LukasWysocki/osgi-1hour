package de.logopak.nameprovider.internal;

import de.logopak.nameprovider.NameProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class NameProviderActivator implements BundleActivator {

  private ServiceRegistration<NameProvider> serviceRegistration;
  private DefaultNameProvider service;

  @Override
  public void start(BundleContext context) {
    service = new DefaultNameProvider();
    serviceRegistration = context.registerService(NameProvider.class, service, null);
  }

  @Override
  public void stop(BundleContext context) {
    serviceRegistration.unregister();
    service.stop();
  }
}
