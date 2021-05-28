package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.models.CDOffering;
import com.bankapp.BankApp.services.CDOfferingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cdofferings")
public class CDOfferingsController {

    @Autowired
    CDOfferingsService cdOfferingsService;

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin')")
    public CDOffering addCDOffering(@RequestBody CDOffering cdOffering) {
        return cdOfferingsService.addCDOffering(cdOffering);
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<CDOffering> getCDOfferings() {
        return cdOfferingsService.getCDOfferings();
    }
}
