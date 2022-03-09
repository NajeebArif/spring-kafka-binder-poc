package narif.poc.kafkastreambinders;

import narif.poc.kafkastreambinders.model.InputMessage;
import narif.poc.kafkastreambinders.model.OutputMessage;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class KafkaStreamBindersApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamBindersApplication.class, args);
    }

    @Bean
    public Consumer<KStream<String, InputMessage>> processInput() {
        return input -> input.foreach((key, value) -> System.out.println("MSG RCVD: " + key + " : " + value));
    }

    @Bean
    public Function<KStream<String, InputMessage>, KStream<String, OutputMessage>> process() {
        return input-> input
                .peek(this::consumeInputStream)
                .map((key, value) -> new KeyValue<>(key, mapToOutput(value)));
    }

    private void consumeInputStream(String key, InputMessage inputMessage) {
        System.out.println("*************************************************************************************");
        System.out.println("=====> Transforming the input to output for key: " + key+" and message: "+inputMessage);
        System.out.println("*************************************************************************************");
    }

    private OutputMessage mapToOutput(InputMessage inputMessage){
        final OutputMessage outputMessage = new OutputMessage();
        outputMessage.setId(inputMessage.getId());
        outputMessage.setMsg(inputMessage.getMsg()+" :--> OUTPUT_MSG");
        return outputMessage;
    }

}
