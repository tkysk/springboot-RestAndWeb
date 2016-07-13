package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by saeki on 2016/07/13.
 */
@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("msg", "名前を入力して送信してください。");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String send(@RequestParam("name") String str, Model model) {
        model.addAttribute("msg", "こんにちは、" + str + "さん!");
        model.addAttribute("value", str);
        return "index";

    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("msg", "フォームに入力して送信してください。");
        return "formsample";

    }

    @RequestMapping(value = "form", method = RequestMethod.POST)
    public String formsend(
            @RequestParam(value = "check1", required = false) boolean check1,
            @RequestParam(value = "radio1", required = false) String radio1,
            @RequestParam(value = "select1", required = false) String select1,
            @RequestParam(value = "select2", required = false) String[] select2,
            Model model
    ) {
        String res = "";
        try {
            res = "check:" + check1 +
                    " radio:" + radio1 +
                    "select:" + select1 +
                    "\nselect2:";
        } catch (NullPointerException e) {
        }
        try {
            res += select2[0];
            for (int i = 1; i < select2.length; i++)
                res += ", " + select2[i];
        } catch (NullPointerException e) {
            res += "null";
        }

        model.addAttribute("msg", res);
        return "formsample";
    }

    @RequestMapping("/other")
    public String other(){
        return "redirect:/";
    }

    @RequestMapping("/home")
    public String home(){
        return "forward:/";
    }



    @RequestMapping("/mav/{num}")
    public ModelAndView index2(@PathVariable int num, ModelAndView mav) {
        int res = 0;
        for (int i = 0; i <= num; i++)
            res += i;
        mav.addObject("msg", "total: " + res);
        mav.setViewName("index2");
        return mav;
    }
}
