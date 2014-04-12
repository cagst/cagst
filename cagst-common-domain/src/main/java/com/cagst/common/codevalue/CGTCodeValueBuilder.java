package com.cagst.common.codevalue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.cagst.common.CGTObjectBuilder;
import com.cagst.common.person.CGTClient;

/**
 * Represents a CodeValue within the system.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
public final class CGTCodeValueBuilder extends CGTObjectBuilder {
	private static final long serialVersionUID = 571293295760168134L;

	private long codevalue_id;
	private CGTCodeSet codeset;
	private CGTClient client;
	private String codevalue_display;
	private String codevalue_meaning;
	private long updt_cnt = 0;

	private final CGTCodeValue originalCodeValue;

	/**
	 * Primary Constructor used to create an instance of CGTCodeValueBuilder.
	 */
	public CGTCodeValueBuilder() {
		this.originalCodeValue = null;
	}

	/**
	 * Primary Constructor used to create an instance of CGTCodeValueBuilder from an existing instance
	 * of {@link CGTCodeValue}.
	 * 
	 * @param codevalue
	 *          The {@link CGTCodeValue} to base this CGTCodeValueBuilder off of.
	 */
	public CGTCodeValueBuilder(final CGTCodeValue codevalue) {
		super(codevalue);

		copyFrom(codevalue);

		this.originalCodeValue = codevalue;
	}

	/**
	 * Gets the unique identifier for the CodeValue.
	 * 
	 * @return A {@link long} that uniquely identifies the CodeValue.
	 */
	public long getCodeValueUID() {
		return codevalue_id;
	}

	/**
	 * Sets the unique identifier for the CodeValue.
	 * 
	 * @param codeValueUID
	 *          A {@link long} that uniquely identifies the CodeValue.
	 */
	public void setCodeValueUID(final long codeValueUID) {
		this.codevalue_id = codeValueUID;
	}

	/**
	 * @return The {@link CGTCodeSet} this CodeValue is associated with.
	 */
	public CGTCodeSet getCodeSet() {
		return codeset;
	}

	/**
	 * Sets the CodeSet this CodeValue is associated with.
	 * 
	 * @param codeSet
	 *          The {@link CGTCodeSet} this CodeValue is associated with.
	 */
	public void setCodeSet(final CGTCodeSet codeSet) {
		this.codeset = codeSet;
	}

	/**
	 * @return The {@link CGTClient} this CodeValue is associated to, <code>null</code> if not
	 *         associated to any Client and is therefore a global CodeValue.
	 */
	public CGTClient getClient() {
		return client;
	}

	/**
	 * Sets the Client this CodeValue is associated with.
	 * 
	 * @param client
	 *          The {@link CGTClient} to associate this CodeValue to, <code>null</code> if this
	 *          CodeValue isn't associated with any Client and is therefore a global CodeValue.
	 */
	public void setClient(final CGTClient client) {
		this.client = client;
	}

	/**
	 * @return The display / name of the CodeValue.
	 */
	public String getDisplay() {
		return codevalue_display;
	}

	/**
	 * Sets the display / name of the CodeValue.
	 * 
	 * @param display
	 *          The {@link String} display / name for the CodeValue.
	 */
	public void setDisplay(final String display) {
		this.codevalue_display = display;
	}

	/**
	 * Gets the meaning of the CodeValue.
	 * 
	 * @return A {@link String} that represents the meaning of the CodeValue.
	 */
	public String getMeaning() {
		return codevalue_meaning;
	}

	/**
	 * Sets the meaning of the CodeValue.
	 * 
	 * @param meaning
	 *          A {@link String} that represents the meaning of the CodeValue.
	 */
	public void setMeaning(final String meaning) {
		this.codevalue_meaning = meaning;
	}

	/**
	 * Gets the number of times this object has been updated.
	 * 
	 * @return {@link long} number of times the object has been updated.
	 */
	public long getCodeValueUpdateCount() {
		return updt_cnt;
	}

	/**
	 * Sets the number of times this object has been updated.
	 * 
	 * @param updateCount
	 *          {@link long} the number of times the object has been updated.
	 */
	public void setCodeValueUpdateCount(final long updateCount) {
		this.updt_cnt = updateCount;
	}

	@Override
	public void rejectChanges() {
		if (originalCodeValue != null) {
			copyFrom(originalCodeValue);
		} else {
			this.codevalue_display = null;
			this.codevalue_meaning = null;
		}

		super.rejectChanges();
	}

	@Override
	public boolean hasChanged(final boolean deepCheck) {
		if (originalCodeValue == null) {
			return true;
		}

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(codevalue_display, originalCodeValue.getDisplay());
		builder.append(codevalue_meaning, originalCodeValue.getMeaning());
		builder.append(isActive(), originalCodeValue.isActive());

		return !builder.build();
	}

	@Override
	public boolean isValid() {
		return !StringUtils.isEmpty(codevalue_display);
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("codeset", codeset);
		builder.append("client", client);
		builder.append("display", codevalue_display);
		builder.append("meaning", codevalue_meaning);
		builder.append("updateCount", updt_cnt);
		builder.appendSuper(super.toString());

		return builder.build();
	}

	/**
	 * Builds a {@link CGTCodeValue} based upon this builder.
	 * 
	 * @return An immutable {@link CGTCodeValue}.
	 */
	public CGTCodeValue build() {
		return new CGTCodeValue(this);
	}

	protected void copyFrom(final CGTCodeValue codevalue) {
		this.codevalue_id = codevalue.getCodeValueUID();
		this.codeset = codevalue.getCodeSet();
		this.client = codevalue.getClient();
		this.codevalue_display = codevalue.getDisplay();
		this.codevalue_meaning = codevalue.getMeaning();
		this.updt_cnt = codevalue.getCodeValueUpdateCount();
	}
}
