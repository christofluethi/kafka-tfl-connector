# kafka-tfl-connector
Kafka Connector for Transport for London Unified API - CarPark API

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
```

## Install

```
cat << EOF > TfLSourceConnector.json
{
  "name": "TfLSourceConnector",
  "config": {
    "connector.class": "ch.shaped.kafka.tfl.TfLSourceConnector",
    "tasks.max": "1",
    "carParkUrl": "https://api.tfl.gov.uk",
    "carpark": "tfl-carpark",
    "appId": "<ID>",
    "appKey": "<KEY>",
    "interval": "5000"
  }
}
EOF
curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d @TfLSourceConnector.json /api/kafka-connect-1/connectors
```


## Data
Pushed CarPark Name with BayCount, Free and Occupied state to kafka topic

```
Key: Buckhurst Hill Stn (LUL)
Value: 44 29 15
```
