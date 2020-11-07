package dev.dovydasvenckus.telegram.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TelegramConfiguration {

  @Valid
  @NotNull
  private TelegramApiConfiguration apiConfiguration;

  @NotEmpty
  private String chatId;

  @JsonProperty
  public String getChatId() {
    return chatId;
  }

  @JsonProperty
  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  @JsonProperty("api")
  public TelegramApiConfiguration getApiConfiguration() {
    return apiConfiguration;
  }

  @JsonProperty("api")
  public void setApiConfiguration(
      TelegramApiConfiguration apiConfiguration) {
    this.apiConfiguration = apiConfiguration;
  }
}
