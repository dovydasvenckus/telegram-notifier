package dev.dovydasvenckus.telegram.notification;

import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
	
  @NotEmpty
  private String message;
  
  @JsonProperty
  public String getMessage() {
    return message;
  }
    
  @JsonProperty
  public void setMessage(String message) {
    this.message = message;
  }
}
