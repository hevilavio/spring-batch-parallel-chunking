package com.hevilavio.examples.posystem;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created by hevilavio on 4/18/16.
 */
public class PaymentOrderProcessor implements ItemProcessor<PaymentOrder, Void> {

    private final String exchangeFolder = "/home/hevilavio/workspace/github";

    @Override
    public Void process(PaymentOrder paymentOrder) throws Exception {


        String contentForFileExchange = paymentOrder.getContentForFileExchange();
        String filename = paymentOrder.getSupposedFilename();

        writeFile(contentForFileExchange, filename);

        simulateDelay();

        return null;
    }

    private void simulateDelay() throws InterruptedException {

        Thread.sleep(5 * 1000);
    }

    private void writeFile(String contentForFileExchange, String filename) throws IOException {

        Path path = Paths.get(exchangeFolder, filename);

        BufferedOutputStream outputStream = new BufferedOutputStream(
            Files.newOutputStream(path, StandardOpenOption.CREATE_NEW));

        outputStream.write(contentForFileExchange.getBytes(), 0, contentForFileExchange.length());
        outputStream.close();
    }
}
