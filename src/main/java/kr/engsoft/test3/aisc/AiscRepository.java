package kr.engsoft.test3.aisc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AiscRepository {
    private final EntityManager em;

    public List<Aisc> selectGates() {
        String sql = "";

        sql += "SELECT gate_id, latitude, longitude ";
        sql += "FROM gates ";

        Query executeQuery = em.createNativeQuery(sql, Aisc.class);

        return executeQuery.getResultList();
    }
}
