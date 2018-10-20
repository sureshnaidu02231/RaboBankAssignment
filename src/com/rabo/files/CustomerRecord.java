package com.rabo.files;

import java.math.BigDecimal;

public class CustomerRecord {
    
    private String referenceNumber;
    private String accountNumber;
    private String description;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;

    private CustomerRecord(CustomerRecordBuilder customerRecordBuilder) {
        this.referenceNumber = customerRecordBuilder.referenceNumber;
        this.accountNumber = customerRecordBuilder.accountNumber;
        this.description = customerRecordBuilder.description;
        this.startBalance = customerRecordBuilder.startBalance;
        this.mutation = customerRecordBuilder.mutation;
        this.endBalance = customerRecordBuilder.endBalance;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public static class CustomerRecordBuilder {

        private String referenceNumber;
        private String accountNumber;
        private String description;
        private BigDecimal startBalance;
        private BigDecimal mutation;
        private BigDecimal endBalance;

        public CustomerRecordBuilder referenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
            return this;
        }

        public CustomerRecordBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public CustomerRecordBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CustomerRecordBuilder startBalance(String startBalance) {
            this.startBalance = new BigDecimal(startBalance);
            return this;
        }

        public CustomerRecordBuilder mutation(String mutation) {
            this.mutation = new BigDecimal(mutation);
            return this;
        }

        public CustomerRecordBuilder endBalance(String endBalance) {
            this.endBalance = new BigDecimal(endBalance);
            return this;
        }

        public CustomerRecord build() {
            return new CustomerRecord(this);
        }

    }
}