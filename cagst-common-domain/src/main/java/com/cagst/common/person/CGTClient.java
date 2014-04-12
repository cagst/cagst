package com.cagst.common.person;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cagst.common.CGTObject;

/**
 * Immutable class that represents a generic Client within the system.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTClient extends CGTObject implements Comparable<CGTClient> {
	private static final long serialVersionUID = -4917654346612405699L;

	private final long client_id;
	private final String client_name;

	/**
	 * Primary Constructor used to create an instance of CGTClient from an existing
	 * {@link CGTClientBuilder}.
	 * 
	 * @param builder
	 *          The {@link CGTClientBuilder} to base this CGTClient off of.
	 */
	protected CGTClient(final CGTClientBuilder builder) {
		super(builder);

		this.client_id = builder.getClientUID();
		this.client_name = builder.getName();
	}

	/**
	 * Gets the unique identifier for the Client.
	 * 
	 * @return A {@link long} that uniquely identifies the Client.
	 */
	public long getClientUID() {
		return client_id;
	}

	/**
	 * @return The name of the Client.
	 */
	public String getName() {
		return client_name;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("name", client_name);

		return builder.build();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(client_name);

		return builder.build();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof CGTClient)) {
			return false;
		}

		CGTClient rhs = (CGTClient) obj;

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(client_name, rhs.getName());

		return builder.build();
	}

	@Override
	public int compareTo(final CGTClient rhs) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(client_name, rhs.getName());

		return builder.build();
	}
}
