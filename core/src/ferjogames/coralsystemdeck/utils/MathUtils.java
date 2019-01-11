package ferjogames.coralsystemdeck.utils;

public class MathUtils {
	public static int roundToNextPowerOfTwo(int value) {
		return (int) Math.pow(2, Math.ceil(Math.log(value)/Math.log(2)));
	}
}
