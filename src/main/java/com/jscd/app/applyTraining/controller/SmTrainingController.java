package com.jscd.app.applyTraining.controller;

import com.jscd.app.applyTraining.dto.SmApplicationDto;
import com.jscd.app.applyTraining.service.SmApplicationService;
import com.jscd.app.lecture.lstRegist.dto.LstRegistDto;
import com.jscd.app.lecture.lstRegist.service.LstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/smTraining")
public class SmTrainingController {
    @Autowired
    SmApplicationService smApplicationService;
    @Autowired
    LstService lstService;

    // 부트캠프 신청서 제출시
    @PostMapping("smApplication")
    public String smApplicationWrite(SmApplicationDto smApplicationDto, Model m, RedirectAttributes rattr){

        try {
            int cnt = smApplicationService.write(smApplicationDto);
            smApplicationService.lectureApplyInsert(smApplicationDto);

            if(cnt != 1)
                throw new Exception("write err");

            rattr.addFlashAttribute("msg", "write ok");

            // 신청서 제출 후 결제 페이지로 이동해야 함.
            return null;
        } catch (Exception e) {
            // 신청서 제출 실패시
            // 작성했던 내용, 에러 메세지와 함께 신청서 페이지로 이동
            e.printStackTrace();
            m.addAttribute(smApplicationDto);
            m.addAttribute("msg", "write err");
            return "/applyTraining/smApplication";
        }
    }

    // 세미나 신청서 이동
    @GetMapping("smApplication")
    public String btTrainingApplication(LstRegistDto lstRegistDto, Model m){
        // 1. 로그인 확인
        // 2. 이미 신청한 회원인지 확인

        // 3. 어떤 회원의 신청서인지 session id 얻어서 전달

        // 4. registCode, title, lastPrice 얻어서 전달
        m.addAttribute("lstRegistDto" + lstRegistDto);
        return "/applyTraining/smApplication";
    }

    // 세미나 리스트 이동
    @GetMapping("/list")
    public String smTrainingList(Model m) throws Exception {
        try {
            List<LstRegistDto> list = lstService.seminarList();
            m.addAttribute("list", list);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "/applyTraining/seminarList";
    }

    // 세미나 세부페이지 이동
    @GetMapping("/read")
    public String seminarRead(Integer registCode, Model m) throws Exception {
        try {
            LstRegistDto lstRegistDto = lstService.seminarRead(registCode);
            m.addAttribute(lstRegistDto);
        } catch(Exception e) {
            e.printStackTrace();
            return "redirect:/smTraining/list";
        }
        return "/applyTraining/seminar";
    }
}