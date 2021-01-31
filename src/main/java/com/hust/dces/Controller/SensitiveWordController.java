package com.hust.dces.Controller;

import com.hust.dces.Entity.SensitiveWord;
import com.hust.dces.Service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("sens_word")
public class SensitiveWordController {
    @Autowired
    private SensitiveWordService sensitiveWordService;

    @GetMapping("/guanggao")
    public String toguanggao(Model model){
        //查询广告敏感词
        List<SensitiveWord>sensitiveWords = sensitiveWordService.getGuanggaoWords();
        model.addAttribute("sensitiveWords",sensitiveWords);
        return "guanggao";
    }
    @GetMapping("/guanggao/{itemid}")
    public String deleteWord(@PathVariable("itemid") Integer itemid,Model model){
        sensitiveWordService.deleteGuanggao(itemid);
        //查询广告敏感词
        List<SensitiveWord>sensitiveWords = sensitiveWordService.getGuanggaoWords();
        model.addAttribute("sensitiveWords",sensitiveWords);
        return "guanggao";

    }
}
