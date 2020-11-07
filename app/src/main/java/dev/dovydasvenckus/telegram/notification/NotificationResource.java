package dev.dovydasvenckus.telegram.notification;

import dev.dovydasvenckus.telegram.telegram.TelegramSender;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/notifications")
public class NotificationResource {

  private TelegramSender telegramSender;

  public NotificationResource(TelegramSender telegramSender) {
    this.telegramSender = telegramSender;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response notify(Notification notification) {
    try {
      telegramSender.sendMessage(notification.getMessage());
      return Response.ok().build();
    } catch (Exception ex) {
      return Response.serverError().build();
    }
  }
}
