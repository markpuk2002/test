package com.test.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorImpl implements PasswordValidator {
	
	private static Pattern PATTERN_NUMERIC = Pattern.compile("\\d");
	private static Pattern PATTERN_UPPERCASE = Pattern.compile("[A-Z]+");
	private static Pattern PATTERN_LOWERCASE = Pattern.compile("[a-z]+");
	
	PreviousPasswordService previousPasswordService;

	@Override
	public PreviousPasswordService getPreviousPasswordService() {
		return previousPasswordService;
	}
	
	public void setPreviousPasswordService(
			PreviousPasswordService previousPasswordService) {
		this.previousPasswordService = previousPasswordService;		
	}

	@Override
	public boolean validate(String password, String confirmPassword) {		
		if (isLengthLessThanEleven(password)
				&& containsNumeric(password)
				&& containsUppercase(password)
				&& containsLowercase(password)
				&& equalsConfirmPassword(password, confirmPassword)
				&& isPreviousPassword(password)) {
			return true;
		}
		return false;		
	}

	private boolean isPreviousPassword(String password) {
		if (!getPreviousPasswordService().isPreviousPassword(password)) {
			return true;
		}
		return false;
	}

	private boolean isLengthLessThanEleven(String password) {
		if (password.length() < 11) {
			return true;
		}
		return false;
	}

	private boolean containsNumeric(String password) {
		Matcher matcherNumeric = PATTERN_NUMERIC.matcher(password);
		if (matcherNumeric.find()) {
			return true;
		}
		return false;
	}

	private boolean containsUppercase(String password) {
		Matcher matcherUppercase = PATTERN_UPPERCASE.matcher(password);
		if (matcherUppercase.find()) {
			return true;
		}
		return false;
	}

	private boolean containsLowercase(String password) {
		Matcher matcherLowercase = PATTERN_LOWERCASE.matcher(password);
		if (matcherLowercase.find()) {
			return true;
		}
		return false;
	}

	private boolean equalsConfirmPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return true;
		}
		return false;
	}

}
