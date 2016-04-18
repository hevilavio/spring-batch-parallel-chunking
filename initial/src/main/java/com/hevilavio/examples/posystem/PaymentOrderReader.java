package com.hevilavio.examples.posystem;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * Created by hevilavio on 4/18/16.
 */
public class PaymentOrderReader implements ItemReader<PaymentOrder> {

    @Override
    public PaymentOrder read()
        throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return new PaymentOrder(1, 1, "00");
    }
}
