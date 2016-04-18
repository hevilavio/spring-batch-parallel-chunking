package com.hevilavio.examples.parallelchunking;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by hevilavio on 4/18/16.
 */
@Getter
@AllArgsConstructor
public class PaymentOrder {
    private Integer orderId;
    private Integer clientIdFrom;
    private Integer clientIdTo;
    private String ammount;

    public String getContentForFileExchange() {
        StringBuilder sb = new StringBuilder();

        sb.append("T");
        sb.append(",");
        sb.append(clientIdFrom);
        sb.append(",");
        sb.append(clientIdTo);
        sb.append(",");
        sb.append(ammount);

        return sb.toString();
    }

    public String getSupposedFilename() {
        return "OP_" + orderId + ".in";
    }
}
