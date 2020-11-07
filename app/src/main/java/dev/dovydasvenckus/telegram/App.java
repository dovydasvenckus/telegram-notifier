package dev.dovydasvenckus.telegram;

import dev.dovydasvenckus.telegram.notification.NotificationResource;
import dev.dovydasvenckus.telegram.telegram.TelegramSender;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<TelegramConfiguration> {

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public String getName() {
    return "telegram-notification-service";
  }

  @Override
  public void initialize(Bootstrap<TelegramConfiguration> bootstrap) {
  }

  @Override
  public void run(TelegramConfiguration configuration,
      Environment environment) {
    TelegramSender telegramSender = new TelegramSender(
        configuration.getApiUrl(),
        configuration.getApiToken(),
        configuration.getChatId()
    );
    environment.jersey().register(new NotificationResource(telegramSender));
  }

}
