package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class BookTest {

	@Test
	public void testIsKidFriendlyEligible() {
		
		//Test 1: For Philosophy books
		Book book=  BookmarkManager.getInstance().createBook(4000,
				"Walden","", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY,
				4.3);
		boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse(
				"For Philosophy books, isKidFriendlyEligible() must return False",
				isKidFriendlyEligible);
				
				
		//Test2: For Self-help books
		 book=  BookmarkManager.getInstance().createBook(4000,"Walden"," ",1854,"Wilder Publications",new String[]{"Henry David Thoreau"},BookGenre.PHILOSOPHY,4.3);
		 isKidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse(
				"For Philosophy books, isKidFriendlyEligible() must return False",
				isKidFriendlyEligible);
	}

}
