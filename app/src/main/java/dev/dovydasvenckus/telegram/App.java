package dev.dovydasvenckus.telegram;

import dev.dovydasvenckus.telegram.notification.NotificationResource;
import dev.dovydasvenckus.telegram.telegram.TelegramConfiguration;
import dev.dovydasvenckus.telegram.telegram.TelegramSender;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public String getName() {
    return "telegram-notification-service";
  }

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
  }

  @Override
  public void run(AppConfiguration appConfiguration,
      Environment environment) {
    TelegramConfiguration telegramConfiguration = appConfiguration.getTelegramConfiguration();
    TelegramSender telegramSender = new TelegramSender(
        telegramConfiguration.getApiConfiguration().getUrl(),
        telegramConfiguration.getApiConfiguration().getToken(),
        telegramConfiguration.getChatId()
    );
    environment.jersey().register(new NotificationResource(telegramSender));
  }

}
