# kafka-tfl-connector
Simpley Dummy Kafka Connector for Transport for London Unified API - CarPark API

## Config
```
connector.class=ch.shaped.kafka.tfl.TfLSourceConnector
tasks.max=1
carpark=tfl-carpark
appId=<ID>
name=TfLSourceConnector
carParkUrl=https://api.tfl.gov.uk
appKey=<KEY>
interval=500
key.converter=org.apache.kafka.connect.storage.StringConverter
value.converter=org.apache.kafka.connect.json.JsonConverter
value.converter.schemas.enable=false
```

## Data
Pushes CarPark Name with BayCount, Free and Occupied state to kafka topic. Connector tries to push only changes - however, this cannot be guaranteed.

```
Key: Buckhurst Hill Stn (LUL)
Value: { "name" : "Buckhurst Hill Stn (LUL)", "bayCount" : 44, "free" : 29, "occupied" : 15 }
```

## Web UI
See https://github.com/christofluethi/kafka-tfl-webnotification
