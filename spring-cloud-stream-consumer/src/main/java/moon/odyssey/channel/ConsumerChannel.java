package moon.odyssey.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {

    @Input
    SubscribableChannel directMessage();

    @Input
    SubscribableChannel broadcastMessage();
}
