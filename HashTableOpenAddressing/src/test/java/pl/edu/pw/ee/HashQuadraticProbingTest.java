package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import org.junit.*;

import pl.edu.pw.ee.services.HashTable;
import pl.edu.pw.ee.services.WordsFromFileArray;

public class HashQuadraticProbingTest {
    HashTable<String> emptyHashOfSizeTen;
    HashTable<String> hashOfSizeTenWithSevenElems;
    HashQuadraticProbing<String> hashOfInitalSizeTenWithFiveHundredElems;
    ArrayList<String> words = new WordsFromFileArray("words.txt");

    @Before
    public void setUp() {
        emptyHashOfSizeTen = new HashQuadraticProbing<>(10, 2, 3);
        hashOfSizeTenWithSevenElems = new HashQuadraticProbing<>(10, 4, 1);

        for (int i = 0; i < 7; i++) {
            hashOfSizeTenWithSevenElems.put(words.get(i));
        }
    }

    @Test
    public void should_NotThrowException_WhenCoefficientsAreNotEqualToZero() {
        // given
        Random r = new Random();
        int a = r.nextInt(10) + 1;
        int b = r.nextInt(10) + 1;
        int size = r.nextInt(100) + 1;

        // when
        HashTable<Double> someHashTable = new HashQuadraticProbing<>(size, a, b);

        // then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenCoefficientAIsEqualToZero() {
        // given
        Random r = new Random();
        int a = 0;
        int b = r.nextInt(10) + 1;
        int size = r.nextInt(100) + 1;

        // when
        HashTable<Double> someHashTable = new HashQuadraticProbing<>(size, a, b);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenCoefficientBIsEqualToZero() {
        // given
        Random r = new Random();
        int a = r.nextInt(10) + 1;
        int b = 0;
        int size = r.nextInt(100) + 1;

        // when
        HashTable<Double> someHashTable = new HashQuadraticProbing<>(size, a, b);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenBothCoefficientaAreEqualToZero() {
        // given
        int a = 0;
        int b = 0;
        Random r = new Random();
        int size = r.nextInt(100);

        // when
        HashTable<Double> someHashTable = new HashQuadraticProbing<>(size, a, b);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> hashSizeZero = new HashQuadraticProbing<>(initialSize, 5, 7);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenPutNullElem() {
        // given
        String nullElem = null;

        // when
        emptyHashOfSizeTen.put(nullElem);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenGetNullElem() {
        // given
        String nullElem = null;

        // when
        emptyHashOfSizeTen.get(nullElem);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenDeleteNullElem() {
        // given
        String nullElem = null;

        // when
        emptyHashOfSizeTen.delete(nullElem);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        String newElem = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHashOfSizeTen);
        emptyHashOfSizeTen.put(newElem);
        int nOfElemsAfterPut = getNumOfElems(emptyHashOfSizeTen);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_AddOnlyOneElem_WhenAddingTwoSameElems() {
        // given
        String elem = "nothing special";
        String sameElem = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHashOfSizeTen);
        emptyHashOfSizeTen.put(elem);
        emptyHashOfSizeTen.put(sameElem);
        int nOfElemsAfterPut = getNumOfElems(emptyHashOfSizeTen);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_AddBothElems_WhenAddingTwoDifferentElems() {
        // given
        String elem = "nothing special";
        String differentElem = "something special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHashOfSizeTen);
        emptyHashOfSizeTen.put(elem);
        emptyHashOfSizeTen.put(differentElem);
        int nOfElemsAfterPut = getNumOfElems(emptyHashOfSizeTen);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(2, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenLoadFactorDoesntExceedMaxVal() {
        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHashOfSizeTen);

        for (int i = 0; i < 7; i++) {
            emptyHashOfSizeTen.put(words.get(i));
        }

        int nOfElemsAfterPut = getNumOfElems(emptyHashOfSizeTen);
        int finalHashSize = getHashSize(emptyHashOfSizeTen);
        double loadFactor = (double) nOfElemsAfterPut / (double) finalHashSize;

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(7, nOfElemsAfterPut);
        assertEquals(10, finalHashSize);
        assertEquals(0.7, loadFactor, 0.1);
    }

    @Test
    public void should_AddElemsAndDoubleHashSize_WhenLoadFactorExceedsMaxVal() {
        // when
        int initialHashSize = getHashSize(emptyHashOfSizeTen);
        int nOfElemsBeforePut = getNumOfElems(emptyHashOfSizeTen);

        for (int i = 0; i < 9; i++) {
            emptyHashOfSizeTen.put(words.get(i));
        }

        int nOfElemsAfterPut = getNumOfElems(emptyHashOfSizeTen);
        int finalHashSize = getHashSize(emptyHashOfSizeTen);
        double loadFactor = (double) nOfElemsAfterPut / (double) finalHashSize;

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(9, nOfElemsAfterPut);
        assertEquals(10, initialHashSize);
        assertEquals(20, finalHashSize);
        assertEquals(0.45, loadFactor, 0.01);
    }

    @Test
    public void should_GetNull_WhenGettingElemThatIsNotInHash() {
        // given
        String nonexistentElem = "i'm a hallucination";

        // when
        int nOfElemsBeforeGet = getNumOfElems(hashOfSizeTenWithSevenElems);
        String gottenElem = hashOfSizeTenWithSevenElems.get(nonexistentElem);
        int nOfElemsAfterGet = getNumOfElems(hashOfSizeTenWithSevenElems);

        // then
        assertEquals(7, nOfElemsBeforeGet);
        assertEquals(7, nOfElemsAfterGet);
        assertEquals(null, gottenElem);
    }

    @Test
    public void should_CorrectlyGetElem_WhenGettingElemThatIsInHash() {
        // given
        String existingElem = words.get(0);

        // when
        int nOfElemsBeforeGet = getNumOfElems(hashOfSizeTenWithSevenElems);
        String gottenElem = hashOfSizeTenWithSevenElems.get(existingElem);
        int nOfElemsAfterGet = getNumOfElems(hashOfSizeTenWithSevenElems);

        // then
        assertEquals(7, nOfElemsBeforeGet);
        assertEquals(7, nOfElemsAfterGet);
        assertEquals(existingElem, gottenElem);
    }

    @Test
    public void should_NotChangeHashSize_WhenDeletingElemThatIsNotInHash() {
        // given
        String nonexistentElem = "im a hallucination";
        String getNonexistentElem = hashOfSizeTenWithSevenElems.get(nonexistentElem);

        // when
        int nOfElemsBeforeDeletion = getNumOfElems(hashOfSizeTenWithSevenElems);
        hashOfSizeTenWithSevenElems.delete(nonexistentElem);
        int nOfElemsAfterDeletion = getNumOfElems(hashOfSizeTenWithSevenElems);

        // then
        assertEquals(7, nOfElemsBeforeDeletion);
        assertEquals(7, nOfElemsAfterDeletion);
        assertEquals(null, getNonexistentElem);
    }

    @Test
    public void should_CorrectlyDeleteElem_WhenDeletingElemThatIsInHash() {
        // given
        String existingElem = words.get(0);

        // when
        int nOfElemsBeforeDeletion = getNumOfElems(hashOfSizeTenWithSevenElems);
        hashOfSizeTenWithSevenElems.delete(existingElem);
        int nOfElemsAfterDeletion = getNumOfElems(hashOfSizeTenWithSevenElems);

        // then
        assertEquals(7, nOfElemsBeforeDeletion);
        assertEquals(6, nOfElemsAfterDeletion);
        assertEquals(null, hashOfSizeTenWithSevenElems.get(existingElem));
    }

    @Test
    public void should_CorrectlyDeleteElemAndBeAbleToGetRemainingElems_WhenDeletingElemThatIsInHash() {
        // This test checks (for each element in hash) if we can get remaining elements
        // when we delete one.

        int numOfElemsInHash = 500;

        for (int index = 0; index < numOfElemsInHash; index++) {
            // given
            String elemToDelete = words.get(index);
            hashOfInitalSizeTenWithFiveHundredElems = new HashQuadraticProbing<>(10, 5, 56);

            for (int i = 0; i < numOfElemsInHash; i++) {
                hashOfInitalSizeTenWithFiveHundredElems.put(words.get(i));
            }

            // when
            int nOfElemsBeforeDeletion = getNumOfElems(hashOfInitalSizeTenWithFiveHundredElems);
            hashOfInitalSizeTenWithFiveHundredElems.delete(elemToDelete);
            int nOfElemsAfterDeletion = getNumOfElems(hashOfInitalSizeTenWithFiveHundredElems);

            // then
            assertEquals(500, nOfElemsBeforeDeletion);
            assertEquals(499, nOfElemsAfterDeletion);
            assertEquals(null, hashOfInitalSizeTenWithFiveHundredElems.get(elemToDelete));

            for (int j = 0; j < nOfElemsBeforeDeletion; j++) {
                if (j != index) {
                    assertEquals(words.get(j), hashOfInitalSizeTenWithFiveHundredElems.get(words.get(j)));
                } else {
                    assertEquals(null, hashOfInitalSizeTenWithFiveHundredElems.get(words.get(j)));
                }
            }
        }

    }

    private int getNumOfElems(HashTable<?> emptyHashOfSizeTen) {
        String fieldSize = "nElems";
        try {
            System.out.println(emptyHashOfSizeTen.getClass().getSuperclass().getName());
            Field field = emptyHashOfSizeTen.getClass().getSuperclass().getDeclaredField(fieldSize);
            field.setAccessible(true);

            int hashSize = field.getInt(emptyHashOfSizeTen);

            return hashSize;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private int getHashSize(HashTable<?> emptyHashOfSizeTen) {
        String fieldSize = "size";
        try {
            System.out.println(emptyHashOfSizeTen.getClass().getSuperclass().getName());
            Field field = emptyHashOfSizeTen.getClass().getSuperclass().getDeclaredField(fieldSize);
            field.setAccessible(true);

            int hashSize = field.getInt(emptyHashOfSizeTen);

            return hashSize;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }





}
