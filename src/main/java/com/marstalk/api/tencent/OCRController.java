package com.marstalk.api.tencent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ocr")
public class OCRController {

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("ocr_index");
    }
}
