package kr.engsoft.test3.shelter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ShelterRepository {
    private final EntityManager em;

    @Autowired
    public ShelterRepository(@Qualifier("ShelterEntityManagerFactory") EntityManager manager) {
        this.em = manager;
    }

    public List<Shelter> selectManage() {
        String sql = "";

        sql += "SELECT provider_id, map_id ";
        sql += "FROM manage ";

        Query executeQuery = em.createNativeQuery(sql, Shelter.class);

        return executeQuery.getResultList();
    }

    public List<ShelterMember> selectMember() {
        String sql = "";

        sql += "SELECT id, username ";
        sql += "FROM member ";

        Query executeQuery = em.createNativeQuery(sql, ShelterMember.class);

        return executeQuery.getResultList();
    }
}
