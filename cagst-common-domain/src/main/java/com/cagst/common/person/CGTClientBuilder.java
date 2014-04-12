package com.cagst.common.person;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cagst.common.CGTObjectBuilder;

/**
 * Mutable class that represents a generic Client within the system.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public class CGTClientBuilder extends CGTObjectBuilder {
	private static final long serialVersionUID = 6839810936166628988L;

	private long client_id;
	private String client_name;

	private final CGTClient originalClient;

	/**
	 * Primary Constructor used to create an instance of CGTClientBuilder.
	 */
	public CGTClientBuilder() {
		this.originalClient = null;
	}

	/**
	 * Primary Constructor used to create an instance of CGTClientBuilder from an existing instance of
	 * {@link CGTClient}.
	 * 
	 * @param client
	 *          The {@link CGTClient} to base this CGTClientBuilder off of.
	 */
	public CGTClientBuilder(final CGTClient client) {
		super(client);

		copyFrom(client);

		this.originalClient = client;
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
	 * Sets the unique identifier for the Client.
	 * 
	 * @param clientUID
	 *          A {@link long} that uniquely identifies the Client.
	 */
	public void setClientUID(final long clientUID) {
		this.client_id = clientUID;
	}

	/**
	 * @return The name of the Client.
	 */
	public String getName() {
		return client_name;
	}

	/**
	 * Sets the name of the Client.
	 * 
	 * @param name
	 *          The {@link String} name of the Client.
	 */
	public void setName(final String name) {
		this.client_name = name;
	}

	@Override
	public void rejectChanges() {
		if (originalClient != null) {
			copyFrom(originalClient);
		} else {
			this.client_name = null;
		}

		super.rejectChanges();
	}

	@Override
	public boolean hasChanged(final boolean deepCheck) {
		if (originalClient == null) {
			return true;
		}

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(client_name, originalClient.getName());
		builder.append(isActive(), originalClient.isActive());

		return !builder.build();
	}

	@Override
	public boolean isValid() {
		return !StringUtils.isEmpty(client_name);
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("name", client_name);

		return builder.build();
	}

	/**
	 * Builds a {@link CGTClient} based upon this builder.
	 * 
	 * @return An immutable {@link CGTClient}.
	 */
	public CGTClient build() {
		return new CGTClient(this);
	}

	private void copyFrom(final CGTClient client) {
		this.client_id = client.getClientUID();
		this.client_name = client.getName();

		super.copyFrom(client);
	}
}
