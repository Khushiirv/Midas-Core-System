
package com.jpmc.midascore.payload;

import com.jpmc.midascore.foundation.Transaction;

public class TransactionResponse {
    private Transaction transaction;
    private Boolean error;

    public TransactionResponse() {
    }

    public TransactionResponse(Transaction transaction, Boolean error) {
        this.transaction = transaction;
        this.error = error;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "transaction=" + transaction +
                ", error=" + error +
                '}';
    }
}
