package com.example.filmcatalog.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleMetrics {
    public final AtomicLong counter;
    public final InfluxDBConnector influxDBConnector;

    public SimpleMetrics(InfluxDBConnector influxDBConnector) {
        this.influxDBConnector = influxDBConnector;
        this.counter = new AtomicLong(0);
    }
    public void incrCounter(){
        influxDBConnector.publicMetrics("counter",counter.incrementAndGet());
    }
}
