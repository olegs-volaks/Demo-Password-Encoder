import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncoderTest {

    private PasswordEncoder subject = new PasswordEncoder();

    @Test
    void success() {
        String password = "demo password";
        String encodedResult = subject.encode(password);
        assertTrue(subject.matches(encodedResult, password));
    }

    @Test
    void fail() {
        String password = "demo password";
        String encodedResult = subject.encode(password);
        assertFalse(subject.matches(encodedResult, "other password"));
    }
}