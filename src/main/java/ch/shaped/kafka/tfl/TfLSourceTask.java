package ch.shaped.kafka.tfl;

import ch.shaped.kafka.tfl.model.CarPark;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.util.*;

public class TfLSourceTask extends SourceTask {

    private static final Logger LOG = LoggerFactory.getLogger(TfLSourceTask.class);
    private String[] topics;
    private TfLOccupancy occupancy;
    private Integer interval;

    public String version() {
        return TfLConstants.VERSION;
    }

    public void start(Map<String, String> properties) {
        topics = properties.get(TfLConstants.CONFIG_KAFKA_TOPIC).split(",");
        interval = Integer.parseInt(properties.get(TfLConstants.CONFIG_POLL_INTERVAL));

        String carParkUrl = properties.get(TfLConstants.CONFIG_CARPARK_URL);
        ResteasyClient client = new ResteasyClientBuilder().register(ResteasyJacksonProvider.class).build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(carParkUrl));
        occupancy = target.proxy(TfLOccupancy.class);
    }

    public List<SourceRecord> poll() throws InterruptedException {
        // TODO: keep track of request rate
        Thread.sleep(interval);

        List<SourceRecord> records = new ArrayList<>();
        Date d = new Date();

        List<CarPark> carParks = occupancy.carPark();

        for (CarPark carPark : carParks) {
            for (String topic : topics) {
                records.add(generateSourceRecord(carPark, d, topic));
            }
        }

        return records;
    }

    private SourceRecord generateSourceRecord(CarPark park, Date updated, String topic) {
        return new SourceRecord(
                null,
                null,
                topic,
                null, // partition will be inferred by the framework
                Schema.STRING_SCHEMA,
                park.getName(),
                Schema.STRING_SCHEMA,
                park.getState(),
                updated.getTime());
    }

    public void stop() {

    }
}
