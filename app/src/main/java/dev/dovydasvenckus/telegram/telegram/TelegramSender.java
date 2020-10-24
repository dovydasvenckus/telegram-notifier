package dev.dovydasvenckus.telegram.telegram;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TelegramSender {
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
	
	public void sendMessage(String message) throws IOException, InterruptedException {
		String requestBody = objectMapper.writeValueAsString(new Message(chatId, message));
    
		HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.format(apiUrl, apiToken, SEND_ACTION_URL)))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();
           
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());;
	}
}
