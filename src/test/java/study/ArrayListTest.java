package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListTest {

    private List<String> testList;

    @BeforeEach
    void setup(){
        testList =new ArrayList<>();
        testList.add("hi");
        testList.add("welcome");
        testList.add("glad");
    }

    @Test
    void toArray_size_같은_배열길이(){
        int arrLen = testList.size();
        String[] convetedArr = testList.toArray(new String[arrLen]);
        assertThat(convetedArr.length).isEqualTo(arrLen);
        for(int i=0; i<arrLen; i++){
            assertThat(testList.get(i)).isEqualTo(convetedArr[i]);
        }
    }

    @Test
    void toArray_size_보다_큰_배열길이(){
        int arrLen = testList.size();
        String[] convetedArr = testList.toArray(new String[arrLen+1]);
        assertThat(convetedArr.length).isEqualTo(arrLen+1);
        assertThat(convetedArr[arrLen]).isNull();
    }

    @Test
    void toArray_size_보다_큰_배열길이_값이들어있는경우(){
        int arrLen = testList.size();
        String[] existArr= {"a","b","c","d","e","f"};
        String[] convetedArr = testList.toArray(existArr);
        assertThat(convetedArr.length).isEqualTo(existArr.length);
        assertThat(convetedArr[arrLen]).isNull();   // size 위치 index에만 null 할당
        for(int i=arrLen+1; i<existArr.length; i++){
            assertThat(convetedArr[i]).isEqualTo(existArr[i]);
        }
    }

    @Test
    void toArray_size_보다_작은_배열길이(){
        int arrLen = testList.size();
        String[] convetedArr = testList.toArray(new String[0]);
        assertThat(convetedArr.length).isEqualTo(arrLen);
    }
}
