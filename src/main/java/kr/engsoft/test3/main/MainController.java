package kr.engsoft.test3.main;

import kr.engsoft.test3.aisc.AiscService;
import kr.engsoft.test3.shelter.Shelter;
import kr.engsoft.test3.shelter.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    final private AiscService aiscService;
    final private ShelterService shelterService;

    @ResponseBody
    @GetMapping("/api/shelterManage")
    public List<Shelter> test() {
        return shelterService.selectManage();
    }

    @GetMapping("/api/main")
    public String main(Model model) {

        model.addAttribute("memberList", shelterService.selectMember());
        model.addAttribute("aiscList", aiscService.selectGates());
        model.addAttribute("shelterList", shelterService.selectManage());
        return "main";
    }
}
