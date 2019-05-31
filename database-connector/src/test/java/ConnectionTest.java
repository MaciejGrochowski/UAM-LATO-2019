import entities.CreatureEntity;
import entities.FractionEntity;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

class ConnectionTest {

    @Test
    void testConnection(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
    }

    @Test
    void persistTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();

        CreatureEntity creature1 = PrepareCreature("Level1", 1);
        CreatureEntity creature2 = PrepareCreature("Level2", 2);
        CreatureEntity creature3 = PrepareCreature("Level3", 3);
        CreatureEntity creature4 = PrepareCreature("Level4", 4);
        CreatureEntity creature5 = PrepareCreature("Level5", 5);
        CreatureEntity creature6 = PrepareCreature("Level_1", 1);

        FractionEntity f1 = new FractionEntity("F1");
        FractionEntity f2 = new FractionEntity("F2");

        f1.addCreatureToFraction(creature1);
        f1.addCreatureToFraction(creature2);
        f1.addCreatureToFraction(creature3);
        f1.addCreatureToFraction(creature4);
        f1.addCreatureToFraction(creature5);
        f2.addCreatureToFraction(creature1);
        f2.addCreatureToFraction(creature6);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(f1);
        em.persist(f2);
        tx.commit();

        em.close();
        emf.close();
    }

    @Test
    void findTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();
        CreatureEntity fromDb = em.find(CreatureEntity.class, 2L);
        System.out.println(fromDb);
    }

    @Test
    void findTest2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();
        FractionEntity fromDb = em.find(FractionEntity.class, 1L);
        System.out.println(fromDb);
    }

    @Test
    void selectAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();
        TypedQuery<CreatureEntity> query = em.createQuery("SELECT c FROM CreatureEntity c",
                CreatureEntity.class);
        List<CreatureEntity> fromDb = query.getResultList();
        System.out.println(fromDb);
    }

    @Test
    void findTestByJpql() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();
        TypedQuery<CreatureEntity> query = em.createQuery("SELECT c FROM CreatureEntity c where c.id=:id ",
                CreatureEntity.class);
        query.setParameter("id", 2l);
        CreatureEntity fromDb = query.getSingleResult();
        System.out.println(fromDb);
    }

    @Test
    void findTestByJpqlWithFetching() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();
        TypedQuery<CreatureEntity> query = em.createQuery("SELECT c FROM CreatureEntity c join fetch c.fractions where c.id=:id ",
                CreatureEntity.class);
        query.setParameter("id", 2l);
        CreatureEntity fromDb = query.getSingleResult();
        System.out.println(fromDb);
    }

    @Test
    void findTestByJpqlWithShortData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroes-demo");
        EntityManager em = emf.createEntityManager();
        TypedQuery<CreatureEntity> query = em.createQuery("SELECT new CreatureEntity(c.name, c.level) FROM CreatureEntity c where c.id=:id ",
                CreatureEntity.class);
        query.setParameter("id", 2l);
        CreatureEntity fromDb = query.getSingleResult();
        System.out.println(fromDb);
    }

    private CreatureEntity PrepareCreature(String aName, int aLevel) {
        CreatureEntity creature = new CreatureEntity();
        creature.setName(aName);
        creature.setMaxHp(aLevel);
        creature.setDefence(aLevel);
        creature.setDealDamageStrategy(CreatureEntity.DamageStrategy.HIGHEST_DAMAGE);
        creature.setGoldCost(aLevel);
        creature.setMinAttack(aLevel);
        creature.setMaxAttack(10);
        creature.setSpeed(aLevel);
        return creature;
    }
}
