package com.cagst.common.util;

import java.text.Collator;
import java.util.Locale;

import org.apache.commons.lang3.builder.Builder;

/**
 * Assists in implementing {@link java.lang.Comarable#compareTo(Object)} methods.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTCollatorBuilder implements Builder<Integer> {
	private final Collator collator;

	/**
	 * The default value is 0 which indicates they are equal (unknown). Once we know something is not
	 * equal, we no longer need to check.
	 */
	private int comparison = 0;

	public CGTCollatorBuilder() {
		collator = Collator.getInstance();
		collator.setStrength(Collator.PRIMARY);
	}

	public CGTCollatorBuilder(final Locale locale) {
		collator = Collator.getInstance(locale);
		collator.setStrength(Collator.PRIMARY);
	}

	public void setStrength(final int newStrength) {
		collator.setStrength(newStrength);
	}

	public CGTCollatorBuilder append(final String lhs, final String rhs) {
		if (comparison != 0) {
			return this;
		}
		if (lhs == rhs) {
			return this;
		}
		if (lhs == null) {
			comparison = -1;
			return this;
		}
		if (rhs == null) {
			comparison = +1;
			return this;
		}

		comparison = collator.compare(lhs, rhs);
		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CGTCollatorBuilder append(final Object lhs, final Object rhs) {
		if (comparison != 0) {
			return this;
		}
		if (lhs == rhs) {
			return this;
		}
		if (lhs == null) {
			comparison = -1;
			return this;
		}
		if (rhs == null) {
			comparison = +1;
			return this;
		}

		if (lhs instanceof Comparable && rhs instanceof Comparable) {
			comparison = ((Comparable) lhs).compareTo(rhs);
		}

		return this;
	}

	/**
	 * Returns a negative Integer, a positive Integer, or zero as the {@code builder} has judged the
	 * "left-hand" side as less than, greater than, or equal to the "right-hand" side.
	 * 
	 * @return final comparison result
	 * @see #build()
	 */
	public int compareTo() {
		return comparison;
	}

	/**
	 * Returns a negative Integer, a positive Integer, or zero as the {@code builder} has judged the
	 * "left-hand" side as less than, greater than, or equal to the "right-hand" side.
	 * 
	 * @return final comparison result as an Integer.
	 * @see #compareTo();
	 */
	@Override
	public Integer build() {
		return Integer.valueOf(comparison);
	}
}
