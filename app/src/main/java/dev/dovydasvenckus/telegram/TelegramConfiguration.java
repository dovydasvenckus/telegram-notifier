package dev.dovydasvenckus.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.constraints.NotEmpty;

public class TelegramConfiguration extends Configuration {

  @NotEmpty
  private String apiUrl = "https://api.telegram.org/bot%s/%s";

  @NotEmpty
  private String apiToken;

  @NotEmpty
  private String chatId;


  @JsonProperty
  public String getApiUrl() {
    return apiUrl;
  }

  @JsonProperty
  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  @JsonProperty
  public String getApiToken() {
    return apiToken;
  }

  @JsonProperty
  public void setApiToken(String apiToken) {
    this.apiToken = apiToken;
  }

  @JsonProperty
  public String getChatId() {
    return chatId;
  }

  @JsonProperty
  public void setChatId(String chatId) {
    this.chatId = chatId;
  }
}
