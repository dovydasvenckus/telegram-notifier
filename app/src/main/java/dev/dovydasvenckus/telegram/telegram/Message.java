package dev.dovydasvenckus.telegram.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

  @JsonProperty("chat_id")
  private String chatId;
  private String text;

  public Message(String chatId, String text) {
    this.chatId = chatId;
    this.text = text;
  }

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
