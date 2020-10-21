package uniquewordintext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

class UniqueWordInTextTest {

    @Test
    @DisplayName("Word occurrence time cost:")
    void wordOccurrence_TimeCost() throws IOException {
        Instant start = Instant.now();
        UniqueWordInText.wordOccurrence();
        Instant end = Instant.now();
        System.out.println("time cost: " + (Duration.between(start, end).toMillis()));
    }

}