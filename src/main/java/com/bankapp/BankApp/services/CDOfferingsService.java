package com.bankapp.BankApp.services;

import com.bankapp.BankApp.models.CDOffering;
import com.bankapp.BankApp.repository.CDOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CDOfferingsService { ;

    public CDOffering addCDOffering(CDOffering cdOffering);

    public List<CDOffering> getCDOfferings();
}
