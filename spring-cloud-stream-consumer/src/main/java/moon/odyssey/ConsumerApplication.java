package moon.odyssey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import lombok.extern.slf4j.Slf4j;
import moon.odyssey.channel.ConsumerChannel;
import moon.odyssey.message.MyMessage;

@SpringBootApplication
@EnableBinding(ConsumerChannel.class)
@Slf4j
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @StreamListener(ConsumerChannel.DIRECT)
    public void handleDirect(MyMessage message) {
        log.info("##### direct message : {}", message.getMessage());
    }

    @StreamListener(ConsumerChannel.BROADCAST)
    public void handleBroadcast(MyMessage message) {
        log.info("##### broadcast message : {}", message.getMessage());
    }
}
