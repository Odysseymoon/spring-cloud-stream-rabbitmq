package moon.odyssey.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import moon.odyssey.message.MyMessage;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = "directMessage")
    void directMessage(MyMessage message);

    @Gateway(requestChannel = "broadcastMessage")
    void broadcastMessage(MyMessage message);
}
