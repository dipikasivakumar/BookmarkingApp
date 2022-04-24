package com.semanticsquare.thrillio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

public class BookmarkDao {

	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();

	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		// DataStore.add(userBookmark);
		try (Connection conn = DriverManager.getConnection( 
				"jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false",
				"root", "root");
				Statement stmt = conn.createStatement();) {
			if (userBookmark.getBookmark() instanceof Book) {
				saveUserBook(userBookmark, stmt);
			} else if (userBookmark.getBookmark() instanceof Movie) {
				saveUserMovie(userBookmark, stmt);
			} else {
				saveUserWebLink(userBookmark, stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saveUserWebLink(UserBookmark userBookmark, Statement stmt) {
		String query = "insert into User_WebLink(user_id, weblink_id) values("
				+ userBookmark.getBookmark().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

	private void saveUserMovie(UserBookmark userBookmark, Statement stmt) {
		String query = "insert into User_Movie(user_id, movie_id) values("
				+ userBookmark.getBookmark().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saveUserBook(UserBookmark userBookmark, Statement stmt) {
		String query = "insert into User_Book(user_id, book_id) values("
				+ userBookmark.getUser().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks = bookmarks.get(0);
		for (Bookmark bookmark : allWebLinks) {
			result.add((WebLink) bookmark);
		}
		return result;
	}

	

	public void updateKidFriendlyStatus(Bookmark bookmark) {
		int kidFriendlyStatus = bookmark.getKidFriendlyStatus().ordinal();
		long userId = bookmark.getKidFriendlyMarkedBy().getId();
		String tableToUpdate = "Book";
		if (bookmark instanceof Movie) {
			tableToUpdate = "Movie";
		} else if (bookmark instanceof WebLink) {
			tableToUpdate = "WebLink";
		}
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false",
				"root", "root");
				Statement stmt = conn.createStatement()) {
			String query = "UPDATE " + tableToUpdate
					+ " SET kid_friendly_status = " + kidFriendlyStatus
					+ ", kid_friendly_marked_by =" + userId + " WHERE id = "
					+ bookmark.getId();
			System.out.println("query (updateKidFriendlyStatus):" + query);
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
