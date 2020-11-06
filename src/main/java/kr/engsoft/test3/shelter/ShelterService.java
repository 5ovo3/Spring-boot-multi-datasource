package kr.engsoft.test3.shelter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;

    @Transactional
    public List<Shelter> selectManage() {
        return shelterRepository.selectManage();
    }
}
