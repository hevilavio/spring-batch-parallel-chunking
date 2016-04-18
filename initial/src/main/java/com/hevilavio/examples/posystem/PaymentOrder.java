package com.hevilavio.examples.posystem;

/**
 * Created by hevilavio on 4/18/16.
 */
public class PaymentOrder {
    private Integer clientIdFrom;
    private Integer clientIdTo;
    private String ammount;

    public PaymentOrder(Integer clientIdFrom, Integer clientIdTo, String ammount) {
        this.clientIdFrom = clientIdFrom;
        this.clientIdTo = clientIdTo;
        this.ammount = ammount;
    }

    public Integer getClientIdFrom() {
        return clientIdFrom;
    }

    public Integer getClientIdTo() {
        return clientIdTo;
    }

    public String getAmmount() {
        return ammount;
    }

    public String getContentForFileExchange() {
        StringBuilder sb = new StringBuilder();

        sb.append("T");
        sb.append(clientIdFrom);
        sb.append(",");
        sb.append(clientIdTo);
        sb.append(",");
        sb.append(ammount);

        return sb.toString();
    }

    public String getSupposedFilename() {
        int random = (int)(Math.random() * 1000);
        return "OP_" + random + ".in";
    }
}
