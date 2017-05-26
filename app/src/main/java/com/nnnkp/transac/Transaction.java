package com.nnnkp.transac;

import java.util.Date;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class Transaction {

    private long id;
    private String clientName;
    private String amount;
    private String type;
    private String remarks;
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", amount='" + amount + '\'' +
                ", type='" + type + '\'' +
                ", remarks='" + remarks + '\'' +
                ", date=" + date +
                '}';
    }
}
