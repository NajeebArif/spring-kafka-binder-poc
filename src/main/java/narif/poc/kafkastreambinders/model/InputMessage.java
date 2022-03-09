package narif.poc.kafkastreambinders.model;

import lombok.Data;

@Data
public class InputMessage {
    private Long id;
    private String msg;
}
