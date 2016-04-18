package com.hevilavio.examples.parallelchunking;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;

/**
 * Created by hevilavio on 4/18/16.
 */
@Slf4j
public class PaymentOrderWritter implements ItemWriter<PaymentOrder> {

    @Override
    public void write(List<? extends PaymentOrder> items) throws Exception {

        int i = 1;
        for (PaymentOrder item : items) {
            log.info("writing orderId {}, {}/{}", item.getClientIdFrom(), i++, items.size());
        }
    }
}
