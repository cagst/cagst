package com.cagst.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

/**
 * Abstract immutable class that provides the common functionality for all domain objects.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public abstract class CGTObject implements Serializable {
	private static final long serialVersionUID = 8567198973613800306L;

	private boolean active_ind = true;
	private DateTime updt_dt_tm = null;

	/**
	 * Primary Constructor used to create an instance of CGTObject from a {@link CGTObjectBuilder}.
	 * 
	 * @param builder
	 *          The {@link CGTObjectBuilder} to base this CGTObject off of.
	 */
	protected CGTObject(final CGTObjectBuilder builder) {
		this.active_ind = builder.isActive();
		this.updt_dt_tm = builder.getUpdateDate();
	}

	/**
	 * Gets the active status of the object.
	 * 
	 * @return {@link boolean} <code>true</code> if the object is active, <code>false</code>
	 *         otherwise.
	 */
	public final boolean isActive() {
		return active_ind;
	}

	/**
	 * Gets the date this object was last updated.
	 * 
	 * @return {@link DateTime} the object was last updated.
	 */
	public final DateTime getUpdateDate() {
		return updt_dt_tm;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

		builder.append("active", active_ind);
		builder.append("updateDate", updt_dt_tm);

		return builder.build();
	}
}
