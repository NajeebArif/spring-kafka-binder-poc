package narif.poc.kafkastreambinders;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import narif.poc.kafkastreambinders.model.InputMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${test.inputTopic}")
    private String inputTopic;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void senMessage(String topic, String key, String message){
        log.debug("Message {} sent on topic {} with key {}", message, topic, key);
        kafkaTemplate.send(topic, key, message);
    }

    public void publishFewMessage(){
        for (int i = 0; i < 50; i++) {
            final InputMessage inputMessage = new InputMessage();
            inputMessage.setId((long) i);
            inputMessage.setMsg("INPUT_MSG:--> "+i+" message");
            senMessage(inputTopic, "key-"+i, getMessage(inputMessage));
            sleep();
        }
    }

    @SneakyThrows
    private void sleep() {
        Thread.sleep(1000);
    }

    @SneakyThrows
    private String getMessage(InputMessage inputMessage) {
        return new ObjectMapper().writeValueAsString(inputMessage);
    }
}
