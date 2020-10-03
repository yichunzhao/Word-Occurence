/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquewordintext;

import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import static uniquewordintext.UniqueWordInText.wordOccurrence;

/**
 *
 * @author YNZ
 */
public class UniqueWordinTextTest {

    Map<String, Long> top10;

    public UniqueWordinTextTest() throws IOException {
        top10 = wordOccurrence();
    }

    @Test
    public void testWordOccrence() throws Exception {

        assertEquals(Long.valueOf(514), top10.get("and"));
        assertEquals(Long.valueOf(513), top10.get("the"));
        assertEquals(Long.valueOf(446), top10.get("i"));
        
        assertEquals(Long.valueOf(310), top10.get("a"));
        assertEquals(Long.valueOf(295), top10.get("of"));
        assertEquals(Long.valueOf(288), top10.get("my"));
        assertEquals(Long.valueOf(211), top10.get("you"));
        assertEquals(Long.valueOf(188), top10.get("that"));
        assertEquals(Long.valueOf(185), top10.get("this"));
        //assertEquals(Long.valueOf(324), top10.get("to"));
    }

}
