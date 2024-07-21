package pl.mikbac;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.Setter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

/**
 * Created by MikBac on 21.07.2024
 */

@Setter
public class KafkaAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private String kafkaBrokers;
    private String kafkaTopic;
    private String kafkaClientId;

    private KafkaProducer kafkaProducer;

    @Override
    public void start() {
        super.start();
        Properties config = new Properties();
        config.put("client.id", kafkaClientId);
        config.put(BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        config.put(ACKS_CONFIG, "all");
        config.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        config.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<String, String>(config);
    }

    @Override
    protected void append(final ILoggingEvent iLoggingEvent) {
        kafkaProducer.send(new ProducerRecord<>(kafkaTopic,
                UUID.randomUUID().toString(),
                iLoggingEvent.getMessage()));
    }

}
