package com.irfaan.api.inventory.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "transaction")
@Entity
public class Transaction extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_transaction", nullable = false)
    private LocalDateTime dateTransaction;

    @OneToMany(targetEntity = DetailTransaction.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private List<DetailTransaction> detailTransactionList;

    @Column(name = "total_pay", nullable = false)
    private Double totalPay;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public List<DetailTransaction> getDetailTransactionList() {
        return detailTransactionList;
    }

    public void setDetailTransactionList(List<DetailTransaction> detailTransactionList) {
        this.detailTransactionList = detailTransactionList;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", dateTransaction=" + dateTransaction +
                ", detailTransactionList=" + detailTransactionList +
                ", totalPay=" + totalPay +
                '}';
    }
}
