package dev.dovydasvenckus.telegram.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

public class TelegramApiConfiguration {

  @NotEmpty
  private String url;

  @NotEmpty
  private String token;

  @JsonProperty
  public String getUrl() {
    return url;
  }

  @JsonProperty
  public void setUrl(String url) {
    this.url = url;
  }

  @JsonProperty
  public String getToken() {
    return token;
  }

  @JsonProperty
  public void setToken(String token) {
    this.token = token;
  }
}
