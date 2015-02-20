package com.test.validator;

public interface PasswordValidator {

	boolean validate(String password, String confirmPassword);

	void setPreviousPasswordService(PreviousPasswordService prevPassService);

	public PreviousPasswordService getPreviousPasswordService();

}
