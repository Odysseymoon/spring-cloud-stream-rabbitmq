package moon.odyssey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;
import moon.odyssey.message.MyMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Slf4j
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public Function<Flux<MyMessage>, Mono<Void>> direct() {
        return myMessageFlux -> myMessageFlux.doOnNext(myMessage -> log.info("##### message for direct : {}", myMessage.getMessage()))
                                       .then();
    }

    @Bean
    public Function<Flux<MyMessage>, Mono<Void>> broadcast() {
        return myMessageFlux -> myMessageFlux.doOnNext(myMessage -> log.info("##### message for broadcast : {}", myMessage.getMessage()))
                                             .then();
    }
}
