public class Order {
	long id;
	Listing listing;
	User purchaser;
	PhysicalAddress shippingAddress;
	PhysicalAddress billingAddress;
	CreditCard paymentMethod;
	Instant orderDate;
}
