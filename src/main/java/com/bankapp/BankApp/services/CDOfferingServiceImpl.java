package com.bankapp.BankApp.services;

import com.bankapp.BankApp.models.CDOffering;
import com.bankapp.BankApp.repository.CDOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CDOfferingServiceImpl implements CDOfferingsService {

    @Autowired
    CDOfferingRepository cdOfferingRepository;

    @Override
    public CDOffering addCDOffering(CDOffering cdOffering) {
        //TODO add cases for invalid rate/term entries
        return cdOfferingRepository.save(cdOffering);
    }

    @Override
    public List<CDOffering> getCDOfferings() {
        return cdOfferingRepository.findAll();
    }
}
