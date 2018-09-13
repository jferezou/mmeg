package com.mmeg.glyphes.optimizer.exception;

public class ExceptionUtils {
	private ExceptionUtils() {
	}

	/**
	 * Propagates {@code throwable} exactly as-is, if and only if it is an instance of {@code declaredType}. Example usage:
	 *
	 * <pre>
	 * try {
	 * 	someMethodThatCouldThrowAnything();
	 * } catch (IKnowWhatToDoWithThisException e) {
	 * 	handle(e);
	 * } catch (Throwable t) {
	 * 	Throwables.propagateIfInstanceOf(t, IOException.class);
	 * 	Throwables.propagateIfInstanceOf(t, SQLException.class);
	 * 	throw Throwables.propagate(t);
	 * }
	 * </pre>
	 */
	public static <X extends Throwable> void propagateIfInstanceOf(Throwable throwable, Class<X> declaredType) throws X {
		// Check for null is needed to avoid frequent JNI calls to isInstance().
		if (throwable != null && declaredType.isInstance(throwable)) {
			throw declaredType.cast(throwable);
		}
	}
}
