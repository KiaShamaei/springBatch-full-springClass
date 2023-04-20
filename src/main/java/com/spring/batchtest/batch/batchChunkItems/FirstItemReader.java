package com.spring.batchtest.batch.batchChunkItems;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FirstItemReader implements ItemReader<Integer> {
    List<Integer> items= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
    int i = 0;
    @Override
    public Integer read() throws Exception,
            UnexpectedInputException,
            ParseException,
            NonTransientResourceException {
        System.out.println("item reader call...");
        Integer item ;
        if(i < items.size()){
            item = items.get(i++);
            return item ;
        }
        i=0;
        return null;
    }
}
