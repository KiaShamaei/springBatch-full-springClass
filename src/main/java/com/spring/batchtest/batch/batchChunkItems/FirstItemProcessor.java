package com.spring.batchtest.batch.batchChunkItems;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer , Long> {
    @Override
    public Long process(Integer item) throws Exception {
        System.out.println("item processor is call");
        return Long.valueOf(item);
    }
}
