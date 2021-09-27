package com.vibhor.cloudstream;

import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@org.springframework.context.annotation.Configuration
public class Configuration {

    Random random = new Random();


    @Bean
    public Supplier<Flux<Integer>> randomProducer() {
        return ()->Flux.interval(Duration.ofSeconds(5)).map(value->random.nextInt(500 - 1) + 1).log();
    }

    @Bean
    public Function<Flux<Integer>,Flux<Integer>> evenProcessor() {
        return integerFlux -> integerFlux.map(this::getEvenNumber).log();
    }

    @Bean
    public Consumer<Flux<Integer>> numberConsumer() {
        return System.out::println;
    }

    private int getEvenNumber(int i){

        if(i%2 == 0)
            return i;

        else
            return 0;

    }




}
