package com.hevilavio.examples.parallelchunking;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by hevilavio on 4/18/16.
 */
@Slf4j
public class PaymentOrderReader {

    public static FlatFileItemReader getReader(){

        FlatFileItemReader<PaymentOrder> reader = new FlatFileItemReader<PaymentOrder>();

        reader.setResource(new FileSystemResource(
            "/home/hevilavio/workspace/github/spring-batch-parallel-chunking/complete/example-data-small.txt"));


        reader.setLineMapper(new LineMapper<PaymentOrder>() {
            @Override
            public PaymentOrder mapLine(String line, int lineNumber) throws Exception {
                String[] split = line.split(",");
                PaymentOrder paymentOrder = new PaymentOrder(lineNumber, Integer.valueOf(split[1]),
                    Integer.valueOf(split[2]), split[3]);

                log.info("mapping line {}, orderId is {}", lineNumber, paymentOrder.getOrderId());
                return paymentOrder;
            }
        });

        return reader;
    }


}
