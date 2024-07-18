import java.util.Set;

public abstract class User extends ClientSession {
	long uuid;
	Set<CreditCard> billingInfos;
	Set<PhysicalAddress> shippingAddresses;
	Set<PhysicalAddress> billingAddresses;
	String phone;
	String email;

	/**
	 * attempts to submit the provided listing
	 * @return true on success, false on failure
	 */
	abstract boolean submitListing(Listing submission);

	/**
	 * attempts to submit the provided review
	 * @return true on success, false on failure
	 */
	abstract boolean submitReview(Review submission);

	/**
	 * attempts to submit the provided order
	 * @return true on success, false on failure
	 */
	abstract boolean submitOrder(Order submission);

	/**
	 * searches for a product by name
	 * @return a set of listings that match the search criteria
	 */
	abstract Set<Listing> searchForProduct(String productName);

}
