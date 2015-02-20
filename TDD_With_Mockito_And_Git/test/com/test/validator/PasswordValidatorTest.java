package com.test.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PasswordValidatorTest {
	
	@Mock PreviousPasswordService prevPassService;
	
	PasswordValidator validator = null;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		validator = new PasswordValidatorImpl();
		validator.setPreviousPasswordService(prevPassService);
	}

	@Test
	public void when_password_is_greater_than_ten_characters_then_fail() {
		assertFalse(validator.validate("abcdefghijk", "abcdefghijk"));		
	}
	
	@Test
	public void when_password_is_less_than_ten_characters_then_pass() {
		assertTrue(validator.validate("1Bcdefghij", "1Bcdefghij"));
	}
	
	@Test 
	public void when_password_does_not_contain_at_least_one_numeric_character_then_fail() {
		assertFalse(validator.validate("abcdefghij", "abcdefghij"));
	}
	
	@Test 
	public void when_password_contains_at_least_one_numeric_character_then_pass() {
		assertTrue(validator.validate("1Bcdefghij", "1Bcdefghij"));
	}
	
	@Test
	public void when_password_does_not_contain_at_least_one_uppercase_character_then_fail() {
		assertFalse(validator.validate("abcdefghij", "abcdefghij"));
	}
	
	@Test
	public void when_password_contains_at_least_one_uppercase_character_then_pass() {
		assertTrue(validator.validate("1Bcdefghij", "1Bcdefghij"));
	}
	
	@Test
	public void when_password_does_not_contain_at_least_one_lowercase_character_then_fail() {
		assertFalse(validator.validate("1BCDEFGHIJ", "1BCDEFGHIJ"));
	}
	
	@Test
	public void when_password_contains_at_least_one_lowercase_character_then_pass() {
		assertTrue(validator.validate("1Bcdefghij", "1Bcdefghij"));
	}
	
	@Test
	public void when_password_does_not_match_confirm_password_then_fail() {
		assertFalse(validator.validate("1Bcdefghij", "1Bcdefghik"));
	}
	
	@Test
	public void when_password_previously_used_then_fail() {
		Mockito.when(prevPassService.isPreviousPassword("1Previous")).thenReturn(true);
		assertFalse(validator.validate("1Previous", "1Previous"));
	}
	
	@Test
	public void when_password_not_previously_used_then_pass() {
		Mockito.when(prevPassService.isPreviousPassword("1Bcdefghik")).thenReturn(false);
		assertTrue(validator.validate("1Previous", "1Previous"));
	}
	
	@After
	public void tearDown() {
		validator = null;
	}
	

}
