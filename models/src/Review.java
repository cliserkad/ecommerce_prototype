public class Review {
	public final long id;
	public final Listing listing;
	public final int stars;
	public final String text;

	public Review(Listing listing, int stars, String text) {
		this.listing = listing;
		this.stars = stars;
		this.text = text;
	}

}
