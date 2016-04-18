package com.hevilavio.examples.posystem;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * Created by hevilavio on 4/18/16.
 */
public class PaymentOrderWritter implements ItemWriter<PaymentOrder> {


    @Override
    public void write(List<? extends PaymentOrder> items) throws Exception {
        for (PaymentOrder item : items) {
            System.out.println("writting item:" + item.getSupposedFilename());
        }
    }
}
