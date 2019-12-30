package learn.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * 生产者 api
 */
public class CustomProducer {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "gh102:9092,gh103:9092,gh104:9092");
        properties.put("acks", "1");
        properties.put("retries", 1);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer(properties);

        ProducerRecord<String, String> producerRecord;
        for (int i = 1; i <= 10; i++) {
            producerRecord = new ProducerRecord<>("t03", "key" + i, "b" + i * i);
            Future<RecordMetadata> t01 = producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    System.out.println("partition = " + metadata.partition() + " , offset = " + metadata.offset());

                }
            });
        }

        // 必须要调用 close 方法才能真正触发消息发送
        producer.close();

    }
}
