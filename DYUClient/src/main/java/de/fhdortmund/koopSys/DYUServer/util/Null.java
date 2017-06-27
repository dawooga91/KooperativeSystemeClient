package de.fhdortmund.koopSys.DYUServer.util;

/**
 * 
 * Helper class for null values
 */
/**
 * @author droege_s
 *
 */
public class Null {

	/**
	 * Test if it is a null value and replace it
	 * 
	 * @param <T>
	 *            Type of testobjects
	 * @param test
	 *            Test value
	 * @param replace
	 *            Replace value
	 * @return test if it is not null else replace
	 */
	public static <T> T nvl(T test, T replace) {

		if (test == null) {

			return replace;
		}

		return test;
	}

	/**
	 * Test if an array value is a null value and replace it. If the array is
	 * null or the index does not exists, the replace value will be returned.
	 * Otherwise the array's value.
	 * 
	 * @param test
	 *            Test array
	 * @param index
	 *            Test index
	 * @param replace
	 *            Replace value
	 * @return test if it is not null and index exists else replace
	 */
	public static <T> T nvl(T[] test, int index, T replace) {
		if (test != null && (index >= 0 && index < test.length)) {
			return test[index];
		}
		return replace;
	}

	/**
	 * Test if at least one value is null
	 * 
	 * @param objs
	 *            Objects that should be tested for null references
	 * @return true, if any object is null
	 */
	public static boolean isAnyNull(Object... objs) {

		for (int i = 0; i < objs.length; i++) {

			if (objs[i] == null) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Test if at least one string is empty or null
	 * 
	 * @param strings
	 *            Strings that should be tested for emptiness
	 * @return true, if any string is null or {@link String#isEmpty()}
	 */
	public static boolean isAnyEmpty(String... strings) {

		for (int i = 0; i < strings.length; i++) {

			if (strings[i] == null || strings[i].isEmpty()) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Test if all values are null
	 * 
	 * @param objs
	 *            Objects that should be tested for null references
	 * @return true, if all object are null
	 */
	public static boolean isNull(Object... objs) {

		for (int i = 0; i < objs.length; i++) {

			if (objs[i] != null) {

				return false;
			}
		}

		return true;
	}

	/**
	 * Test if all values aren't null
	 * 
	 * @param objs
	 *            Objects that should be tested for null references
	 * @return true, if no object is null
	 */
	public static boolean isNotNull(Object... objs) {

		for (int i = 0; i < objs.length; i++) {

			if (objs[i] == null) {

				return false;
			}
		}

		return true;
	}
}