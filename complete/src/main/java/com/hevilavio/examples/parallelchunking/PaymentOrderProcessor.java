package com.hevilavio.examples.parallelchunking;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created by hevilavio on 4/18/16.
 */
@Slf4j
public class PaymentOrderProcessor implements ItemProcessor<PaymentOrder, PaymentOrder> {

    private final String exchangeFolder = "/home/hevilavio/workspace/github/spring-batch-parallel-chunking/out/";
    private final String uniqueExchangeFolder;

    public PaymentOrderProcessor() {
        this.uniqueExchangeFolder = Paths.get(exchangeFolder, UUID.randomUUID().toString()).toString();

        new File(uniqueExchangeFolder).mkdirs();
    }

    @Override
    public PaymentOrder process(PaymentOrder paymentOrder) throws Exception {
        log.info("processing orderId {}", paymentOrder.getOrderId());
        String contentForFileExchange = paymentOrder.getContentForFileExchange();
        String filename = paymentOrder.getSupposedFilename();

        writeFile(contentForFileExchange, filename);

//        simulateDelay();

        return paymentOrder;
    }

    private void simulateDelay() throws InterruptedException {

        Thread.sleep(5 * 1000);
    }

    private void writeFile(String contentForFileExchange, String filename) throws IOException {

        Path path = Paths.get(uniqueExchangeFolder, filename);

        BufferedOutputStream outputStream = new BufferedOutputStream(
            Files.newOutputStream(path, StandardOpenOption.CREATE_NEW));

        outputStream.write(contentForFileExchange.getBytes(), 0, contentForFileExchange.length());
        outputStream.close();
    }
}
