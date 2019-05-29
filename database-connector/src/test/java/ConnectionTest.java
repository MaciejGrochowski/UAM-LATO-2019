import entities.CreatureEntity;
import entities.FractionEntity;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class ConnectionTest {

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
