package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.models.CDOffering;
import com.bankapp.BankApp.services.CDOfferingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CDOfferingsController {

    @Autowired
    CDOfferingsService cdOfferingsService;

    /**
     *
     * @param cdOffering to add
     * @return cd offering
     */
    @PostMapping(value = "/cdofferings")
    @ResponseStatus(HttpStatus.CREATED)
    public CDOffering addCDOffering(@RequestBody CDOffering cdOffering) {
        return cdOfferingsService.addCDOffering(cdOffering);
    }

    /**
     *
     * @return list of cd offerings
     */
    @GetMapping(value = "/cdofferings")
    @ResponseStatus(HttpStatus.OK)
    public List<CDOffering> getCDOfferings() {
        return cdOfferingsService.getCDOfferings();
    }
}
