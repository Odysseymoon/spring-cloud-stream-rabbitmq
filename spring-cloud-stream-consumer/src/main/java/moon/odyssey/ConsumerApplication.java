package moon.odyssey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.SubscribableChannel;

import lombok.extern.slf4j.Slf4j;
import moon.odyssey.channel.ConsumerChannel;

@SpringBootApplication
@EnableBinding(ConsumerChannel.class)
@Slf4j
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    private IntegrationFlow incommingMessageFlow(SubscribableChannel channel, String prefix) {

        return IntegrationFlows.from(channel)
                               .transform(String.class, String::toUpperCase)
                               .handle(
                                   String.class
                                   , (payload, headers) -> {
                                       log.info("##### {} message : {}", prefix, payload);
                                       return null;
                                   }
                               )
                               .get();
    }

    @Bean
    IntegrationFlow direct(ConsumerChannel channel) {
        return incommingMessageFlow(channel.directMessage(), "direct");
    }

    @Bean
    IntegrationFlow broadcast(ConsumerChannel channel) {
        return incommingMessageFlow(channel.broadcastMessage(), "broadcast");
    }
}
