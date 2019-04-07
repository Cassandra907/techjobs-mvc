package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
    return "search";}

    // TODO #1 - Create handler to process search request and display results



    @RequestMapping(value = "results") //results handler
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();
        int numberOfResults;

        if(searchType.equals("all")){
            for(HashMap<String,String> job : JobData.findByValue(searchTerm)) {
                jobs.add(job);
            }
        } else {
            for(HashMap<String,String> job : JobData.findByColumnAndValue(searchType,searchTerm)) {
                jobs.add(job);
            }
        }

        numberOfResults = jobs.size();

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("numberOfResults", numberOfResults);


        return "search";

    }
}
