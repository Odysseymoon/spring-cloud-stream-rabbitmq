package moon.odyssey.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.odyssey.gateway.MessageGateway;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final MessageGateway messageGateway;

    @GetMapping(value = "/direct/{message}")
    public Mono<Void> direct(@PathVariable String message) {

        return Mono.just(message)
                   .doOnNext(s -> messageGateway.directMessage(message))
                   .then();
    }

    @GetMapping(value = "/broadcast/{message}")
    public Mono<Void> broadcast(@PathVariable String message) {

        return Mono.just(message)
                   .doOnNext(s -> messageGateway.broadcastMessage(message))
                   .then();
    }
}
