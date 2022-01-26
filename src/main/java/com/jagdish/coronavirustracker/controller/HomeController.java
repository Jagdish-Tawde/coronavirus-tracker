package com.jagdish.coronavirustracker.controller;

import com.jagdish.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping
    public String home(Model model) {
        long totalCaseCount = coronaVirusDataService
                .getAllStat()
                .stream()
                .mapToLong(stat -> stat.getLatestTotalCases()).sum();
        long totalNewCase = coronaVirusDataService
                .getAllStat()
                .stream()
                .mapToLong(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("totalCaseCount", totalCaseCount);
        model.addAttribute("locationStats", coronaVirusDataService.getAllStat());
        model.addAttribute("totalNewCase", totalNewCase);
        return "home";
    }
}
