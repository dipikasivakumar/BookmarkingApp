package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidFriendlyEligible() {

		// Test 1: For Horror Genre
		Movie movie = BookmarkManager.getInstance().createMovie(3000,
				"Citizen Kane", "",1941,
				new String[] { "Orson Welles,Joseph Cotten" },
				new String[] { "Orson Welles" }, MovieGenre.HORROR, 8.5);
		boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse(
				"For Horror Genre, isKidFriendlyEligible() must return False",
				isKidFriendlyEligible);

		// Test2: For Thriller Genre
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane",
				"",1941, new String[] { "Orson Welles,Joseph Cotten" },
				new String[] { "Orson Welles" }, MovieGenre.THRILLERS, 8.5);
		isKidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse(
				"For Thriller Genre, isKidFriendlyEligible() must return False",
				isKidFriendlyEligible);

	}

}

