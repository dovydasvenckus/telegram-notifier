package dev.dovydasvenckus.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.dovydasvenckus.telegram.telegram.TelegramConfiguration;
import io.dropwizard.Configuration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AppConfiguration extends Configuration {

  @Valid
  @NotNull
  private TelegramConfiguration telegramConfiguration;

  @JsonProperty("telegram")
  public TelegramConfiguration getTelegramConfiguration() {
    return telegramConfiguration;
  }

  @JsonProperty("telegram")
  public void setTelegramConfiguration(
      TelegramConfiguration telegramConfiguration) {
    this.telegramConfiguration = telegramConfiguration;
  }
}
