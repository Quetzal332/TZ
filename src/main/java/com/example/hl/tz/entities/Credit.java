package com.example.hl.tz.entities;

import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private  UUID id;
    @Column(name = "credit_limit")
    private  Long creditLimit;
    @Column(name = "name")
    private String name;
    @Column(name = "interest_rate")
    private Double interestRate;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "credit")
    private List<CreditOffer> creditOffers;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }




    public List<CreditOffer> getCreditOffers() {
        return creditOffers;
    }


    public void setCreditOffers(List<CreditOffer> creditOffers) {
        this.creditOffers = creditOffers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Credit() {
    }

    public Credit(@Valid Long creditLimit,@Valid String name,@Valid Double interestRate,@Valid Bank bank, @Valid List<CreditOffer> creditOffers) {
        this.creditLimit = creditLimit;
        this.name = name;
        this.interestRate = interestRate;
        this.bank = bank;
        this.creditOffers = creditOffers;
    }


}
