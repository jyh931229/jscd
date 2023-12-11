package com.jscd.app.applyTraining.controller;

import com.jscd.app.applyTraining.dto.BtApplicationDto;
import com.jscd.app.applyTraining.dto.ApplicationHandler;
import com.jscd.app.applyTraining.service.BtApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/onlyAdmin/adminBtTraining")
@RequestMapping("/adminBtTraining")
public class AdminBtTrainingController {
    @Autowired
    BtApplicationService btApplicationService;

    // 승인 여부 처리시
    @PostMapping("modify")
    public String btApplicationModify(BtApplicationDto btApplicationDto, Integer page, Integer pageSize, Model m, RedirectAttributes rattr){

        System.out.println("btApplicationDto = " + btApplicationDto);

        try {
            int cnt = btApplicationService.modify(btApplicationDto);

            if(cnt != 1)
                throw new Exception("modify failed");

            rattr.addFlashAttribute("page", page);
            rattr.addFlashAttribute("pageSize", pageSize);
            return "redirect:/adminBtTraining/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("btApplicationDto", btApplicationDto);
            m.addAttribute("page", page);
            m.addAttribute("page", pageSize);
            return "/applyTraining/adminBtApplication";
        }
    }

    @GetMapping("read")
    public String btApplicationRead(Integer stfmNo, Integer page, Integer pageSize, Model m){

        try {
            BtApplicationDto btApplicationDto =  btApplicationService.read(stfmNo);

            m.addAttribute("btApplicationDto", btApplicationDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/applyTraining/adminBtApplication";
    }

    @GetMapping("list")
    public String btApplicationList(Integer page, Integer pageSize, Model m){

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;

        try {
            int totalCnt = btApplicationService.getCount();

            ApplicationHandler ah = new ApplicationHandler(totalCnt, page, pageSize);
            Map map = new HashMap();
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);

            List<BtApplicationDto> list = btApplicationService.getPage(map);

            m.addAttribute("list", list);
            m.addAttribute("ah", ah);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/applyTraining/adminBtApplicationList";
    }
}
