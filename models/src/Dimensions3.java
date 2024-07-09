/**
 * Represents the external dimensions of an object in 3 dimensions, measured in nanometers
 */
public class Dimensions3 {
	/** Height */
	long x;
	/** Width */
	long y;
	/** Depth */
	long z;

	public Dimensions3(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
