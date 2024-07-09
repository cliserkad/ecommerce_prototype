import java.util.Set;

public abstract class User extends ClientSession {
	long uuid;
	Set<CreditCard> billingInfos;
	Set<PhysicalAddress> shippingAddresses;
	Set<PhysicalAddress> billingAddresses;
	String phone;
	String email;

	abstract Listing submitListing(Listing submission);

	abstract Review submitReview(Review submission);

	abstract Order submitOrder(Order submission);

	abstract Set<Listing> searchForProduct(String productName);

}
