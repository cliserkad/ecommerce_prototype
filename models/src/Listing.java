import java.util.List;
import java.util.Set;

public class Listing {
	long uuid;
	String name;
	String description;
	int weightInGrams;
	Dimensions3 dimensions;
	Set<String> stampsOfApproval;
	List<String> imageURLs;
	List<Review> reviews;

}
