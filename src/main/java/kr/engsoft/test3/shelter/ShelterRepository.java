package kr.engsoft.test3.shelter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShelterRepository {
    private final EntityManager em;

    public List<Shelter> selectManage() {
        String sql = "";

        sql += "SELECT provider_id, map_id ";
        sql += "FROM manage ";

        Query executeQuery = em.createNativeQuery(sql, Shelter.class);

        return executeQuery.getResultList();
    }
}
