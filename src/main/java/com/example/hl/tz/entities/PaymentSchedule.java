package com.example.hl.tz.entities;

import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "payment_schedule")
public class PaymentSchedule {
    @Id
    @GeneratedValue(generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private UUID scheduleId;

    @Column(name = "sum_payment")
    private BigDecimal sumPayment;

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    @Column(name = "date_payment")
    private Date datePayment;
    @Column(name = "sum_body")
    private BigDecimal sumBody;
    @Column(name = "sum_percent")
    private BigDecimal sumPercent;


    @OneToOne(mappedBy = "paymentSchedule")
    private CreditOffer creditOffer;
    public UUID getScheduleId() {
        return scheduleId;
    }

    public CreditOffer getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    public BigDecimal getSumPayment() {
        return sumPayment;
    }

    public void setSumPayment(BigDecimal sumPayment) {
        this.sumPayment = sumPayment;
    }

    public BigDecimal getSumBody() {
        return sumBody;
    }

    public void setSumBody(BigDecimal sumBody) {
        this.sumBody = sumBody;
    }

    public BigDecimal getSumPercent() {
        return sumPercent;
    }

    public void setSumPercent(BigDecimal sumPercent) {
        this.sumPercent = sumPercent;
    }
}
