package com.xindian.controller;

import com.xindian.service.TbMerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mer")
public class MerController {

    @Autowired
    private TbMerService service;
}
