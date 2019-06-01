package ch.shaped.kafka.tfl.model;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

public class TfLSchemas {

    public static final String SCHEMA_CARPARK = "ch.shaped.kafka.tfl.CarParkValue";

    public static final String NAME = "name";
    public static final String BAY_COUNT = "bayCount";
    public static final String FREE = "free";
    public static final String OCCUPIED = "occupied";

    // Value Schema
    public static final Schema CARPARK_SCHEMA = SchemaBuilder.struct().name(SCHEMA_CARPARK)
            .version(1)
            .field(NAME, Schema.STRING_SCHEMA)
            .field(BAY_COUNT, Schema.INT32_SCHEMA)
            .field(FREE, Schema.INT32_SCHEMA)
            .field(OCCUPIED, Schema.INT32_SCHEMA)
            .build();
}
