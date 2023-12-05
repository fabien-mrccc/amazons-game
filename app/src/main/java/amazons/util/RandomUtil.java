package amazons.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomUtil{
    /**
     * Return a random element in the specified  array.
     * @param random: A random generator
     * @param array: the array from which the random element is selected
     * @return a random element from the specified array
     */
    public static <T> T getRandomElement (Random random, T[] array){
        return array[random.nextInt(array.length)];
    }

    /**
     * Return a random element in the specified list.
     * @param random: A random generator
     * @param list: the list from which the random element is selected
     * @return a random element from the specified list. The specified list is unchanged
     */
    public static <T> T getRandomElement (Random random, List<T> list){
        return list.get(random.nextInt(list.size()));
    }

    /**
     * Select random elements for the specified array. The selected elements are chosen at random and
     * have distinct indexes in the array. The specified array is unmodified.
     * @param random: a random generator
     * @param array: the array in which  elements are selected
     * @param numberOfElements: the number of elements to be selected. Must be not larger than the length of the array
     * @return a list of numberOfElements of elements from the specified array
     */
    public static <T>  List<T> getRandomElements(Random random, T[] array, int numberOfElements){
        assert array.length >= numberOfElements;
        return random.ints(0,array.length)
                .distinct()
                .limit(numberOfElements)
                .mapToObj(i -> array[i])
                .collect(Collectors.toList());
    }

    /**
     * Select random elements for the specified list. The selected elements are chosen at random and
     * have distinct indexes in the list. The specified list is unmodified.
     * @param random: a random generator
     * @param list: the list in which  elements are selected
     * @param numberOfElements: the number of elements to be selected. Must be not larger than the length of the list
     * @return a list of numberOfElements of elements from the specified list
     */
    public static <T> List<T> getRandomElements(Random random, List<T> list, int numberOfElements){
        assert list.size() >=  numberOfElements;
        return random.ints(0, list.size())
                .distinct()
                .limit(numberOfElements)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }
}
