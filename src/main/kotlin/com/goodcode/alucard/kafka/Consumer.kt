package com.goodcode.alucard.kafka

//import org.apache.kafka.clients.consumer.Consumer
//import org.apache.kafka.clients.consumer.KafkaConsumer
//import org.apache.kafka.common.serialization.StringDeserializer
//import java.util.*
//
//private fun createConsumer(): Consumer<String, String> {
//    println("\n\n\n\t\t KAFKA CONSUMER createConsumer")
//
//    val props = Properties()
//    props["bootstrap.servers"] = "localhost:9092"
//    props["group.id"] = "hello-world"
//    props["key.deserializer"] = StringDeserializer::class.java
//    props["value.deserializer"] = StringDeserializer::class.java
//
//    return KafkaConsumer(props)
//}