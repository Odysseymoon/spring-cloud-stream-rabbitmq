package moon.odyssey.rest;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.odyssey.channel.ProducerChannel;
import moon.odyssey.message.MyMessage;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final StreamBridge streamBridge;

    @GetMapping(value = "/direct/{message}")
    public Mono<Void> directMessage(@PathVariable String message) {

        return Mono.just(message)
                   .doOnNext(s -> streamBridge.send(ProducerChannel.DIRECT, MyMessage.builder().message(message).build()))
                   .then();
    }

    @GetMapping(value = "/broadcast/{message}")
    public Mono<Void> broadcastMessage(@PathVariable String message) {

        return Mono.just(message)
                   .doOnNext(s -> streamBridge.send(ProducerChannel.BROADCAST, MyMessage.builder().message(message).build()))
                   .then();
    }
}
