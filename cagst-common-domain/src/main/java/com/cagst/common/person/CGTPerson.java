package com.cagst.common.person;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import com.cagst.common.CGTObject;

/**
 * An immutable representation of a generic Person within the system.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTPerson extends CGTObject implements Comparable<CGTPerson> {
	private static final long serialVersionUID = -44642081554893455L;

	private final long person_id;
	private final CGTClient client;
	private final String name_last;
	private final String name_first;
	private final String name_middle;
	private final DateTime dob;

	private final long updt_cnt;

	/**
	 * Primary Constructor used to create an instance of a CGTPerson from an existing
	 * {@link CGTPersonBuilder}.
	 * 
	 * @param builder
	 *          The {@link CGTPersonBuilder} to base this CGTPerson off of.
	 */
	protected CGTPerson(final CGTPersonBuilder builder) {
		super(builder);

		this.person_id = builder.getPersonUID();
		this.client = builder.getClient();
		this.name_last = builder.getLastName();
		this.name_first = builder.getFirstName();
		this.name_middle = builder.getMiddleName();
		this.dob = builder.getDob();
		this.updt_cnt = builder.getCGTPersonUpdateCount();
	}

	/**
	 * Gets the unique identifier for the Person.
	 * 
	 * @return A {@link long} that uniquely identifies the Person.
	 */
	public long getPersonUID() {
		return person_id;
	}

	/**
	 * @return The {@link CGTClient} this Person is associated with.
	 */
	public CGTClient getClient() {
		return client;
	}

	/**
	 * Gets the last name for the Person.
	 * 
	 * @return {@link String} the Persons last name.
	 */
	public String getLastName() {
		return name_last;
	}

	/**
	 * Gets the first name for the Person.
	 * 
	 * @return {@link String} the Persons first name.
	 */
	public String getFirstName() {
		return name_first;
	}

	/**
	 * Gets the middle name for the Person.
	 * 
	 * @return {@link String} the Persons middle name.
	 */
	public String getMiddleName() {
		return name_middle;
	}

	/**
	 * Gets the {@link DateTime} of Birth for the Person.
	 * 
	 * @return The {@link DateTime} of Birth for the Person.
	 */
	public DateTime getDob() {
		return dob;
	}

	/**
	 * Gets the {@link Date} of Birth for the Person.
	 * 
	 * @return The {@link Date} of Birth for the Person.
	 */
	public Date getDateOfBirth() {
		if (dob == null) {
			return null;
		}

		return dob.toDate();
	}

	/**
	 * Gets the number of times this object has been updated.
	 * 
	 * @return {@link long} number of times the object has been updated.
	 */
	public final long getCGTPersonUpdateCount() {
		return updt_cnt;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("person_id", person_id);
		builder.append("name_last", name_last);
		builder.append("name_first", name_first);
		builder.append("dob", dob == null ? StringUtils.EMPTY : dob.toString());
		builder.append("client", client == null ? StringUtils.EMPTY : client.toString());
		builder.append("updateCount", updt_cnt);
		builder.appendSuper(super.toString());

		return builder.build();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(name_last);
		builder.append(name_first);
		builder.append(name_middle);
		builder.append(dob);
		builder.append(client);

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
		if (!(obj instanceof CGTPerson)) {
			return false;
		}

		CGTPerson rhs = (CGTPerson) obj;

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(name_last, rhs.getLastName());
		builder.append(name_first, rhs.getFirstName());
		builder.append(name_middle, rhs.getMiddleName());
		builder.append(dob, rhs.getDob());
		builder.append(client, rhs.getClient());

		return builder.build();
	}

	@Override
	public int compareTo(final CGTPerson rhs) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(name_last, rhs.getLastName());
		builder.append(name_first, rhs.getFirstName());

		return builder.build();
	}
}
