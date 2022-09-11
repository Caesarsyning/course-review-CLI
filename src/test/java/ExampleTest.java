import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ExampleTest {

	@Test
	void test() {
		double input = 2.0;
		double expected = 2.0;
		double actual = Math.sqrt(input * input);
		double margin_of_error = 1e-9;
		assertEquals(expected, actual, margin_of_error, "Example test failed message");
	}

}
