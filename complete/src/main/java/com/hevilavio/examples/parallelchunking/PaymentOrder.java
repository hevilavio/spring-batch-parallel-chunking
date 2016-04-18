package com.hevilavio.examples.parallelchunking;

/**
 * Created by hevilavio on 4/18/16.
 */
public class PaymentOrder {
    private Integer orderId;
    private Integer clientIdFrom;
    private Integer clientIdTo;
    private String ammount;

    public PaymentOrder(Integer orderId, Integer clientIdFrom, Integer clientIdTo, String ammount) {
        this.orderId = orderId;
        this.clientIdFrom = clientIdFrom;
        this.clientIdTo = clientIdTo;
        this.ammount = ammount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getClientIdFrom() {
        return clientIdFrom;
    }

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
