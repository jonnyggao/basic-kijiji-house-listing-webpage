package application.controller;

import application.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;

@Controller
public class A3Controller {

  @Autowired
  private ListingService listingService;

  @RequestMapping(value = "/cscc01a3", method = RequestMethod.GET)
  public String cscc01a3(HttpServletRequest request, Model model) {

    model.addAttribute("allListings", listingService.getAllListings());
    return "cscc01a3";
  }
}
