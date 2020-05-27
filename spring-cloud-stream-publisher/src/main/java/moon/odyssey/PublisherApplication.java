package moon.odyssey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import moon.odyssey.channel.ProducerChannel;

@SpringBootApplication
@EnableBinding(ProducerChannel.class)
public class PublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }


}
