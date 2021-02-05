package com.hust.dces.Controller;

import com.github.pagehelper.PageInfo;
import com.hust.dces.Entity.SensitiveWord;
import com.hust.dces.Entity.SensitiveWordsType;
import com.hust.dces.Service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("sens_word")
public class SensitiveWordController {
    @Autowired
    private SensitiveWordService sensitiveWordService;

    @RequestMapping("/guanggao")
    public String toguanggao(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize,
                             @RequestParam(value = "wordtypeid",defaultValue = "0") Integer wordtypeid,
                             @RequestParam(value = "sensitiveword",defaultValue = "") String sensitiveword,
                             Model model){
        PageInfo<SensitiveWord>listInfo=null;
        if(wordtypeid==0&&sensitiveword.isEmpty())
            listInfo=sensitiveWordService.findAllSensitiveWords(pageIndex,pageSize);
        else if(wordtypeid==0&&!sensitiveword.isEmpty())
            listInfo=sensitiveWordService.findWordByWord(pageIndex,pageSize,sensitiveword);
        else listInfo=sensitiveWordService.findWordByTypeIDAndWord(pageIndex,pageSize,wordtypeid,sensitiveword);
        List<SensitiveWordsType> typeInfo = sensitiveWordService.findAllType();

        model.addAttribute("sensitiveWords",listInfo);
        model.addAttribute("sensitiveWord",sensitiveword);
        model.addAttribute("wordTypeId",wordtypeid);
        model.addAttribute("typeInfo",typeInfo);
        return "guanggao";
    }

    @GetMapping("/delete/{itemid}")
    public String deleteWord(
            @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize,
            @PathVariable("itemid") Integer itemid,Model model){
        sensitiveWordService.deleteGuanggao(itemid);
        return "redirect:/sens_word/guanggao";

    }

    @PostMapping("/add")
    public String addWord(@RequestParam(value = "word") String word,
                          @RequestParam(value = "wordtypeid") Integer wordtypeid, Model model) {
        if (word != null)
            sensitiveWordService.addWord(word, wordtypeid);
        return "redirect:/sens_word/guanggao";
    }

}
