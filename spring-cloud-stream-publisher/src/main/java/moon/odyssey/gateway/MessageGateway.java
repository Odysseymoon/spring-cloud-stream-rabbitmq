package moon.odyssey.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = "directMessage")
    void directMessage(String message);

    @Gateway(requestChannel = "broadcastMessage")
    void broadcastMessage(String message);
}
