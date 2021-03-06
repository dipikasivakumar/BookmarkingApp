package com.semanticsquare.thrillio.entities;

import java.util.Arrays;

import com.semanticsquare.thrillio.constants.BookGenre;


public class Book extends Bookmark  {
	private int publicationYear;
	private String publisher;
	private String[] authors;
	private BookGenre genre;
	private double amazonRating;

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public BookGenre getGenre() {
		return genre;
	}

	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	public double getAmazonRating() {
		return amazonRating;
	}

	public void setAmazonRating(double amazonRating) {
		this.amazonRating = amazonRating;
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if (genre.equals(BookGenre.PHILOSOPHY)
				|| genre.equals(BookGenre.SELF_HELP)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Book [publicationYear=" + publicationYear + ", publisher="
				+ publisher + ", authors=" + Arrays.toString(authors)
				+ ", genre=" + genre + ", amazonRating=" + amazonRating + "]";
	}

	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
		builder.append("<type> Book </type>");
		builder.append("</item>");
		builder.append("<title>").append(getTitle()).append("</title>");
		builder.append("<publisher>").append(publisher).append("</publisher>");
		builder.append("<publicationYear>").append(publicationYear).append("</publicationYear>");
		builder.append("<authors>").append(org.apache.commons.lang3.StringUtils.join(authors,",")).append("</authors>");
		builder.append("<genre>").append(genre).append("</genre>");
		builder.append("<amazonRating>").append(amazonRating).append("</amazonRating>");
		builder.append("</Item>");
		return builder.toString();
		
	}

}
