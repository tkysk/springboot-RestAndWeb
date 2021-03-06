package com.example.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by saeki on 2016/07/13.
 */
@Controller
public class RestController {

    String[] names = {"taro", "ichiro", "hanako", "sachiko", "goro"};
    String[] mails = {"taro@example.com", "ichi@ex", "hana@mail", "sa@mail", "goro@goro.com"};

    @RequestMapping("/rest/{id}")
    @ResponseBody
    public DataObject index(@PathVariable int id) {

        return new DataObject(id, names[id], mails[id]);
    }

    @RequestMapping("/web/{id}")
    public String showData(@PathVariable int id, Model model) {
        try {
            model.addAttribute("object", new DataObject(id, names[id], mails[id]));
        } catch (NullPointerException e) {
        }

        model.addAttribute("msg", "No." + id + " Data");

        return "showdata";
    }

    @RequestMapping("/web/num/{num}")
    public String shoWholeData(@PathVariable int num, Model model) {
        ArrayList<DataObject> data = new ArrayList<DataObject>();

        for (int i = 0; (i < names.length) && (i < mails.length); i++)
            data.add(new DataObject(i, names[i], mails[i]));

        model.addAttribute("data", data);
        model.addAttribute("genMsg", "'No.'+ ${num} +' Data'");
        model.addAttribute("num", num);

        return "showdata2";
    }
}


class DataObject {
    private int id;
    private String name;
    private String value;

    public DataObject(int id, String name, String value) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

