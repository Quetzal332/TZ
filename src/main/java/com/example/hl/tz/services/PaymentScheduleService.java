package com.example.hl.tz.services;

import com.example.hl.tz.entities.CreditOffer;
import com.example.hl.tz.entities.PaymentSchedule;
import com.example.hl.tz.repositories.CreditOfferRepository;
import com.example.hl.tz.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentScheduleService {
    private CreditOfferService creditOfferService;
    private PaymentScheduleRepository paymentScheduleRepository;
    @Autowired
    public void setPaymentScheduleRepository(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }
    @Autowired
    public void setCreditOfferService(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    public List<PaymentSchedule> getPaymentSchedules(LocalDate date, Integer month,
                                                     double percent, BigDecimal sumCredit,
                                                     CreditOffer creditOffer) {

        List<PaymentSchedule> paymentSchedules = new ArrayList<>();
        BigDecimal body = sumCredit.divide(BigDecimal.valueOf(month), 2, BigDecimal.ROUND_HALF_UP);

        for (int i = 0; i < month; i++) {

            BigDecimal sumPercent = sumCredit
                    .multiply(BigDecimal.valueOf(percent))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

            sumCredit = sumCredit
                    .subtract(body)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

            BigDecimal payment = sumPercent
                    .add(body)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);

            PaymentSchedule paymentSchedule = new PaymentSchedule();
            paymentSchedule.setDatePayment(Date.valueOf(date.plusMonths(i)));
            paymentSchedule.setSumBody(body);
            paymentSchedule.setSumPayment(payment);
            paymentSchedule.setSumPercent(sumPercent);
            paymentSchedule.setCreditOffer(creditOffer);

            paymentSchedules.add(paymentSchedule);
        }

        return paymentSchedules;
    }

    public void saveAll(List<PaymentSchedule> paymentSchedules) {
        paymentScheduleRepository.saveAll(paymentSchedules);
    }
}
