package pl.orange.letsmeet.util;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import pl.orange.letsmeet.exception.InvalidTimeFormatException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class StringToLocalTimeConverterTest {
    
    private StringToLocalTimeConverter converter = new StringToLocalTimeConverter();

    @Test
    void should_convertProperly_forCorrectArgument() {
        
        LocalTime time = assertDoesNotThrow(() -> (converter.convert("13:01")));
        assert time != null;
        assertEquals(13, time.getHour());
        assertEquals(1, time.getMinute());
    }
    
    @Test
    void should_throwException_forInvalidArgument() {
        assertThatThrownBy(() -> converter.convert("13:01:15"))
                .isInstanceOf(InvalidTimeFormatException.class)
                .hasMessage(InvalidTimeFormatException.INVALID_TIME_FORMAT);
    }
}