package pl.edu.pw.ee;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HashListChainingTest {
    HashListChaining<String> hashListChainingSizeOne;
    HashListChaining<String> hashListChainingSizeRandom;
    String[] elements = { "cos", "inny", "el_3", "Maryla" };

    @Before 
    public void setUp() {
        Random r = new Random();
        int randomSize = r.nextInt(10 - 2) + 2;

        hashListChainingSizeOne = new HashListChaining<>(1);
        hashListChainingSizeRandom = new HashListChaining<>(randomSize);
    }

    @Rule
    public ExpectedException e = ExpectedException.none();

    @Test
    public void add_OneElem_When_HashListSize_IsLessThanZero() {
        e.expect(IllegalArgumentException.class);
        e.expectMessage("Size of hash list cannot be 0 or less!");
        
        HashListChaining<String> hashListChainingSizeMinusOne = new HashListChaining<>(-1);

        hashListChainingSizeMinusOne.add(elements[0]);

        assertNull(hashListChainingSizeMinusOne.get(elements[0]));
        assertTrue(hashListChainingSizeMinusOne.getNumOfElemsInHash() == 0);
    }

    @Test
    public void add_OneElem_When_HashListSize_EqualsZero() {
        e.expect(IllegalArgumentException.class);
        e.expectMessage("Size of hash list cannot be 0 or less!");
        
        HashListChaining<String> hashListChainingSizeZero = new HashListChaining<>(0);

        hashListChainingSizeZero.add(elements[0]);

        assertNull(hashListChainingSizeZero.get(elements[0]));
        assertTrue(hashListChainingSizeZero.getNumOfElemsInHash() == 0);
    }

    @Test
    public void add_OneElem_When_ElemIsNull() {
        e.expect(IllegalArgumentException.class);
        e.expectMessage("Cannot add a null elem!");

        hashListChainingSizeOne.add(null);

        assertNull(hashListChainingSizeOne.get(null));
        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 0);
    }

    @Test
    public void add_OneElem_When_HashListSize_EqualsOne() {
        hashListChainingSizeOne.add(elements[0]);

        assertTrue(hashListChainingSizeOne.get(elements[0]).equals(elements[0]));
        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 1);
    }

    @Test
    public void add_TwoDifferentElems_When_HashListSize_EqualsOne() {
        hashListChainingSizeOne.add(elements[0]);
        hashListChainingSizeOne.add(elements[1]);

        for(int i = 0; i < 2; i++) {
            assertTrue(hashListChainingSizeOne.get(elements[i]).equals(elements[i]));
        }

        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 2);
    }

    @Test
    public void add_TwoSameElems_When_HashListSize_EqualsOne() {
        hashListChainingSizeOne.add(elements[0]);
        hashListChainingSizeOne.add(elements[0]);

        assertTrue(hashListChainingSizeOne.get(elements[0]).equals(elements[0]));
        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 1);
    }

    @Test
    public void add_ThreeElems_When_HashListSize_EqualsTwo() {
        for (int i = 0; i < 3; i++) {
            hashListChainingSizeRandom.add(elements[i]);
        }

        for(int j = 0; j < 3; j++) {
            assertTrue(hashListChainingSizeRandom.get(elements[j]).equals(elements[j]));
        }

        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 3);
    }

    @Test
    public void get_Elem_When_HashListIsEmpty() {
        assertNull(hashListChainingSizeRandom.get("inny"));
        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 0);
    }

    @Test
    public void get_Elem_When_GivenElem_IsNull() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeRandom.add(elements[i]);
        }

        e.expect(IllegalArgumentException.class);
        e.expectMessage("Cannot get a null elem!");

        assertNull(hashListChainingSizeRandom.get(null));
        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);
    }

    @Test
    public void get_Elem_When_GivenElem_IsNotInHashList() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeRandom.add(elements[i]);
        }

        assertNull(hashListChainingSizeRandom.get("ImAHallucination"));
        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);
    }

    @Test
    public void get_Elem_When_GivenElem_IsInHashList() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeRandom.add(elements[i]);
        }

        assertTrue(hashListChainingSizeRandom.get("inny") == "inny");
        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);
    }

    @Test
    public void delete_Elem_When_HashListIsEmpty() {
        assertNull(hashListChainingSizeRandom.get(elements[0]));
        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 0);

        hashListChainingSizeRandom.delete(elements[0]);

        assertNull(hashListChainingSizeRandom.get(elements[0]));
        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 0);
    }

    @Test
    public void delete_Elem_When_GivenElem_IsNull() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeRandom.add(elements[i]);
        }

        e.expect(IllegalArgumentException.class);
        e.expectMessage("Cannot delete a null elem!");

        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);

        hashListChainingSizeRandom.delete(null);

        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);
    }

    @Test
    public void delete_Elem_When_GivenElem_IsNotInHashList() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeRandom.add(elements[i]);
        }

        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);
        assertNull(hashListChainingSizeRandom.get("imNotEvenHere"));

        hashListChainingSizeRandom.delete("imNotEvenHere");

        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);
        assertNull(hashListChainingSizeRandom.get("imNotEvenHere"));
    }

    @Test
    public void delete_Elem_When_GivenElem_IsInHashList() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeRandom.add(elements[i]);
        }

        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 4);
        assertTrue(hashListChainingSizeRandom.get("inny").equals("inny"));

        hashListChainingSizeRandom.delete("inny");

        assertTrue(hashListChainingSizeRandom.getNumOfElemsInHash() == 3);
        assertNull(hashListChainingSizeRandom.get("inny"));
    }

    @Test
    public void delete_Elem_When_GivenElem_IsAtTheBeginningOfChain() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeOne.add(elements[i]);
        }

        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 4);
        assertTrue(hashListChainingSizeOne.getNextElem("Maryla").equals("el_3"));
        assertNull(hashListChainingSizeOne.getPreviousElem("Maryla"));

        hashListChainingSizeOne.delete("Maryla");

        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 3);
        assertNull(hashListChainingSizeOne.get("Maryla"));
    }

    @Test
    public void delete_Elem_When_GivenElem_IsInTheMiddleOfChain() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeOne.add(elements[i]);
        }

        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 4);
        assertTrue(hashListChainingSizeOne.getNextElem("inny").equals("cos"));
        assertTrue(hashListChainingSizeOne.getPreviousElem("inny").equals("el_3"));

        hashListChainingSizeOne.delete("inny");

        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 3);
        assertNull(hashListChainingSizeOne.get("inny"));
    }

    @Test
    public void delete_Elem_When_GivenElem_IsAtTheEndOfChain() {
        for (int i = 0; i < elements.length; i++) {
            hashListChainingSizeOne.add(elements[i]);
        }

        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 4);
        assertNull(hashListChainingSizeOne.getNextElem("cos"));
        assertTrue(hashListChainingSizeOne.getPreviousElem("cos").equals("inny"));

        hashListChainingSizeOne.delete("cos");

        assertTrue(hashListChainingSizeOne.getNumOfElemsInHash() == 3);
        assertNull(hashListChainingSizeOne.get("cos"));
    }

}