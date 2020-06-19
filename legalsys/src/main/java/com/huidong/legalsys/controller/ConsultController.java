package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.service.ConsultService;
import com.huidong.legalsys.service.PenallawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class ConsultController {

    @Autowired
    private ConsultService consultService;
    @Autowired
    private PenallawService penallawService;

    @GetMapping("/consult/detail")
    public String consultDetail(@RequestParam("id") Integer id,
                                Map<String, Object> map) {
        Consult consult = consultService.getConsult(id);
        map.put("consult", consult);
        try {
            String result = consult.getResult();
            JSONObject jsonOutput = new JSONObject(result);

            JSONArray accusationId = jsonOutput.getJSONArray("accusation");
            JSONArray articleId = jsonOutput.getJSONArray("articles");
            Integer imprisonment = jsonOutput.getInt("imprisonment");
            ArrayList<Stature> article = new ArrayList<>();
            ArrayList<String> accusation = new ArrayList<>();
            try {
                for (int i = 0; i < accusationId.length(); i++) {
                    accusation.add(consultService.getAccu(articleId.getInt(i)));
                }
                for (int i = 0; i < accusationId.length(); i++) {
                    article.add(penallawService.getStature(accusationId.getInt(i)));
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

            map.put("accusations", accusation);
            map.put("articles", article);
            map.put("imprisonment", imprisonment);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return "consult/detail";
    }

    @GetMapping("/consult")
    public String consult() {
        return "consult/index";
    }

    @GetMapping("/consult/result")
    public String consultResult(Map<String, Object> map,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        String query = (String) session.getAttribute("query");
        JSONArray accusationId = (JSONArray) session.getAttribute("accusations");
        JSONArray articleId = (JSONArray) session.getAttribute("articles");
        Integer imprisonment = (Integer) session.getAttribute("imprisonment");
        ArrayList<Stature> article = new ArrayList<>();
        ArrayList<String> accusation = new ArrayList<>();
        try {
            for (int i = 0; i < accusationId.length(); i++) {
                accusation.add(consultService.getAccu(articleId.getInt(i)));
            }
            for (int i = 0; i < accusationId.length(); i++) {
                article.add(penallawService.getStature(accusationId.getInt(i)));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        map.put("query", query);
        map.put("accusations", accusation);
        map.put("articles", article);
        map.put("imprisonment", imprisonment);
        session.removeAttribute("query");
        session.removeAttribute("accusations");
        session.removeAttribute("articles");
        session.removeAttribute("imprisonment");
        return "consult/result";
    }


    @PostMapping("/consult/upload")
    public String consultUpload(@RequestParam("title") String title,
                                @RequestParam("query") String query,
                                HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        String result = consultService.newconsult(phone, title, query);
        try {
            JSONObject jsonOutput = new JSONObject(result);
            session.setAttribute("accusations", jsonOutput.getJSONArray("accusation"));
            session.setAttribute("articles", jsonOutput.getJSONArray("articles"));
            session.setAttribute("imprisonment", jsonOutput.getInt("imprisonment"));
            session.setAttribute("query", query);
        } catch (JSONException e){
            e.printStackTrace();
        }
        return "redirect:/consult/result";
    }
}
