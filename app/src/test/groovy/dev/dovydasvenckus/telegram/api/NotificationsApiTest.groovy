package dev.dovydasvenckus.telegram.api

import dev.dovydasvenckus.telegram.App
import dev.dovydasvenckus.telegram.AppConfiguration
import dev.dovydasvenckus.telegram.api.server.MockServer
import dev.dovydasvenckus.telegram.notification.Notification
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.junit.Rule
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response

class NotificationsApiTest extends Specification {
    @Rule
    DropwizardAppRule<AppConfiguration> RULE = new DropwizardAppRule<>(
            App.class,
            ResourceHelpers.resourceFilePath("test-config.yml")
    )

    def "should return 500 when telegram API is down"() {
        given:
            Client client = RULE.client()

        when:
            Response response = client.target(
                    String.format("http://localhost:%d/api/notifications", RULE.getLocalPort()))
                    .request()
                    .post(Entity.json(new Notification(message: "Test message")))

        then:
            response.getStatus() == 500
    }

    def "should return 500 when telegram API return not 200 response"() {
        given:
            Client client = RULE.client()
            MockServer mockServer = new MockServer(port: 8090, uri: "/botSomeToken/sendMessage", statusCode: 400)
            mockServer.start()

        when:
            Response response = client.target(
                    String.format("http://localhost:%d/api/notifications", RULE.getLocalPort()))
                    .request()
                    .post(Entity.json(new Notification(message: "Test message")))

        then:
            response.getStatus() == 500
        cleanup:
            mockServer.stop()
    }

    def "should return 200 when telegram call returns success"() {
        given:
            Client client = RULE.client()
            MockServer mockServer = new MockServer(port: 8090, uri: "/botSomeToken/sendMessage", statusCode: 200)
            mockServer.start()

        when:
            Response response = client.target(
                    String.format("http://localhost:%d/api/notifications", RULE.getLocalPort()))
                    .request()
                    .post(Entity.json(new Notification(message: "Test message")))

        then:
            response.getStatus() == 200
        cleanup:
            mockServer.stop()
    }
}
