package everisacademy.utils;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ValidatorEmail implements Predicate<String> {
	
	private static final Predicate<String> EMAIL_IS_VALID = Pattern.compile("", Pattern.CASE_INSENSITIVE).asPredicate();

	@Override
	public boolean test(String email) {
		return EMAIL_IS_VALID.test(email);
	}

}
