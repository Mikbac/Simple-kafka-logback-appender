# Simple kafka logback appender

## Docker compose with Kafka

```shell
cd docker
docker compose up -d 
```

## Adding to logback

```xml

<appender name="KAFKA" class="pl.mikbac.KafkaAppender">
    <kafkaBrokers>localhost:9192</kafkaBrokers>
    <kafkaTopic>demo_logs2</kafkaTopic>
    <kafkaClientId>test_client</kafkaClientId>
</appender>
```

```xml

<root level="warn"> <!-- kafka appender can't use "info" level because it tries to log itself -->
    <appender-ref ref="KAFKA"/>
</root>
```

## Demo project

To check how it works run [Sample-app](Sample-app) via [Main.run.xml](.run/Main.run.xml).
