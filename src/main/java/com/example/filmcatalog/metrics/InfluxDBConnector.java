package com.example.filmcatalog.metrics;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.write.Point;


public class InfluxDBConnector {
    private final InfluxDBClient influxDBClient;
    public InfluxDBConnector(String url, String token, String org, String bucket){

        influxDBClient = InfluxDBClientFactory.create(url,token.toCharArray(), org, bucket);
    }
    public void publicMetrics(String field, Long data){
        WriteApiBlocking api = influxDBClient.getWriteApiBlocking();
        Point point = Point.measurement("example")
                .addTag("controller","all_controllers")
                .addField(field,data);
        api.writePoint(point);
    }
}
