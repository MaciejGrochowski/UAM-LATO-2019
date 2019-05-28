import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class ConnectionTest {

    @Test
    void connectionTest(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();

        em.close();
        emf.close();
    }
}
