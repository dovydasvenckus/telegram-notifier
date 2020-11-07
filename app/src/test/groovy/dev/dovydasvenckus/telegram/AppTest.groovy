package dev.dovydasvenckus.telegram

import dev.dovydasvenckus.telegram.notification.Notification
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.junit.Rule
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response

class AppTest extends Specification {
    @Rule
    DropwizardAppRule<AppConfiguration> RULE = new DropwizardAppRule<>(
            App.class,
            ResourceHelpers.resourceFilePath("test-config.yml")
    )

    def "application has a greeting"() {
        given:
            Client client = RULE.client()

        when:
            Response response = client.target(
                    String.format("http://localhost:%d/api/notifications", RULE.getLocalPort()))
                    .request()
                    .post(Entity.json(new Notification(message: "Test message")))

        then:
            response.getStatus() == 204
    }
}
