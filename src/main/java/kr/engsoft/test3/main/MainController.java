package kr.engsoft.test3.main;

//import kr.engsoft.test3.aisc.AiscService;
//import kr.engsoft.test3.shelter.ShelterService;
import kr.engsoft.test3.aisc.Aisc;
import kr.engsoft.test3.aisc.AiscService;
import kr.engsoft.test3.shelter.Shelter;
import kr.engsoft.test3.shelter.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    final private AiscService aiscService;
    final private ShelterService shelterService;

    @GetMapping("/main")
    public String main() {

        List<Aisc> aisc = aiscService.selectGates();
        List<Shelter> shelter = shelterService.selectManage();
        return "main";
    }
}
