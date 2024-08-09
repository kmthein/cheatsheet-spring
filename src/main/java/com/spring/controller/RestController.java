package com.spring.controller;

import com.spring.model.Subsection;
import com.spring.repository.SubsectionRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private SubsectionRepository subsectionRepository;

    @GetMapping(value = "/getSubsections", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> getSubsectionBySectionId(@RequestParam("sectionId") int sectionId) {
        List<Subsection> subs = subsectionRepository.getSubsectionBySectionId(sectionId);

        JSONArray subsections = new JSONArray();
        for (Subsection sub : subs) {
            JSONObject subsection = new JSONObject();
            subsection.put("id", sub.getId());
            subsection.put("name", sub.getName());
            subsections.put(subsection);
        }

        return ResponseEntity.ok(subsections.toString());
    }
}
