package dev.dovydasvenckus.telegram.telegram;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelegramSender {

  private static final Logger LOG = LoggerFactory.getLogger(TelegramSender.class);
  private static final String SEND_ACTION_URL = "sendMessage";

  private String apiUrl;
  private String apiToken;
  private String chatId;

  private ObjectMapper objectMapper = new ObjectMapper();

  public TelegramSender(String apiUrl, String apiToken, String chatId) {
    this.apiUrl = apiUrl;
    this.apiToken = apiToken;
    this.chatId = chatId;
  }

  public void sendMessage(String message) throws Exception {
    LOG.info(String.format("Sending message to %s", chatId));
    String requestBody = objectMapper.writeValueAsString(new Message(chatId, message));

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(String.format(apiUrl, apiToken, SEND_ACTION_URL)))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() != HttpStatus.OK_200) {
      LOG.error("Could not send message to chat: {}. Reason: {}", chatId, response.body());
      throw new TelegramException(response.body());
    }
  }
}
