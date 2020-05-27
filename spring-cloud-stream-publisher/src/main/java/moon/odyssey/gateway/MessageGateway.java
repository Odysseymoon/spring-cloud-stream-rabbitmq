package moon.odyssey.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import moon.odyssey.channel.ProducerChannel;
import moon.odyssey.message.MyMessage;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = ProducerChannel.DIRECT)
    void directMessage(MyMessage message);

    @Gateway(requestChannel = ProducerChannel.BROADCAST)
    void broadcastMessage(MyMessage message);
}
