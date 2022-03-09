package narif.poc.kafkastreambinders;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class KafkaConsumer {

    private CountDownLatch latch = new CountDownLatch(50);
    private String payload = null;

//    @KafkaListener(topics = "${test.topic}")
//    public void receive(ConsumerRecord<?, ?> consumerRecord) {
//        log.info("received payload='{}'", consumerRecord.toString());
//        setPayload(consumerRecord.toString());
//        latch.countDown();
//    }

    @KafkaListener(topics = "${test.outputTopic}")
    public void receiveOutput(ConsumerRecord<?, ?> consumerRecord) {
        log.info("received payload='{}'", consumerRecord.toString());
        setPayload(consumerRecord.toString());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
