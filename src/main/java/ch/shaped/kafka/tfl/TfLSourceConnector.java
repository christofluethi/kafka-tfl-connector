package ch.shaped.kafka.tfl;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TfLSourceConnector extends SourceConnector {

    private String topics;
    private String carParkUrl;
    private String appId;
    private String appKey;
    private Integer interval;

    public void start(Map<String, String> properties) {
        carParkUrl = properties.get(TfLConstants.CONFIG_CARPARK_URL);
        topics = properties.get(TfLConstants.CONFIG_KAFKA_TOPIC);
        appId = properties.get(TfLConstants.CONFIG_TFL_APPID);
        appKey = properties.get(TfLConstants.CONFIG_TFL_KEY);
        interval = Integer.parseInt(properties.get(TfLConstants.CONFIG_POLL_INTERVAL));
    }

    public Class<? extends Task> taskClass() {
        return TfLSourceTask.class;
    }

    public List<Map<String, String>> taskConfigs(int maxTasks) {
        final List<Map<String, String>> configs = new LinkedList<>();

        for (int i = 0; i < maxTasks; i++) {
            final Map<String, String> config = new HashMap<>();
            config.put(TfLConstants.CONFIG_CARPARK_URL, carParkUrl);
            config.put(TfLConstants.CONFIG_KAFKA_TOPIC, topics);
            config.put(TfLConstants.CONFIG_TFL_APPID, appId);
            config.put(TfLConstants.CONFIG_TFL_KEY, appKey);
            config.put(TfLConstants.CONFIG_POLL_INTERVAL, String.valueOf(interval));
            configs.add(config);
        }

        return configs;
    }

    public void stop() {

    }

    public ConfigDef config() {
        final ConfigDef configDef = new ConfigDef();
        configDef.define(TfLConstants.CONFIG_CARPARK_URL, ConfigDef.Type.STRING, "https://api.tfl.gov.uk", ConfigDef.Importance.HIGH, "Full TfL CarPark address (see https://api.tfl.gov.uk)");
        configDef.define(TfLConstants.CONFIG_KAFKA_TOPIC, ConfigDef.Type.STRING, "tfl-carpark", ConfigDef.Importance.HIGH, "Topic name(s) the message should be sent to");
        configDef.define(TfLConstants.CONFIG_TFL_APPID, ConfigDef.Type.STRING, null, ConfigDef.Importance.HIGH, "Transport for London App ID");
        configDef.define(TfLConstants.CONFIG_TFL_KEY, ConfigDef.Type.STRING, null, ConfigDef.Importance.HIGH, "Transport for London Key");
        configDef.define(TfLConstants.CONFIG_POLL_INTERVAL, ConfigDef.Type.INT, null, ConfigDef.Importance.HIGH, "Polling interval in ms");
        return configDef;
    }

    public String version() {
        return TfLConstants.VERSION;
    }
}
