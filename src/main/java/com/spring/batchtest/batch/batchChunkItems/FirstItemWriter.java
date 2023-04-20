package com.spring.batchtest.batch.batchChunkItems;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Long> {
    @Override
    public void write(List<? extends Long> items) throws Exception {
        System.out.println("item writer is call");
        items.stream().forEach(System.out::println);

    }
}
