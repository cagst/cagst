package com.cagst.common.codevalue;

import com.cagst.common.CGTObject;
import com.cagst.common.person.CGTClient;
import org.apache.commons.lang3.builder.*;

/**
 * Represents a CodeValue within the system.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public final class CGTCodeValue extends CGTObject implements Comparable<CGTCodeValue> {
  private static final long serialVersionUID = -2283948456320997999L;

  private final long codevalue_id;
  private final CGTCodeSet codeset;
  private final CGTClient client;
  private final String codevalue_display;
  private final String codevalue_meaning;
  private final long updt_cnt;

  /**
   * Primary Constructor used to create an instance of CGTCodeValue from an existing
   * {@link CGTCodeValueBuilder}.
   *
   * @param builder
   *     The {@link CGTCodeValueBuilder} to base this CGTCodeValue off of.
   */
  /* package */CGTCodeValue(final CGTCodeValueBuilder builder) {
    super(builder);

    this.codevalue_id = builder.getCodeValueUID();
    this.codeset = builder.getCodeSet();
    this.client = builder.getClient();
    this.codevalue_display = builder.getDisplay();
    this.codevalue_meaning = builder.getMeaning();
    this.updt_cnt = builder.getCodeValueUpdateCount();
  }

  /**
   * Gets the unique identifier for the CodeValue.
   *
   * @return A {@link long} that uniquely identifies the CodeValue.
   */
  public long getCodeValueUID() {
    return this.codevalue_id;
  }

  /**
   * @return The {@link CGTCodeSet} this CodeValue is associated with.
   */
  public CGTCodeSet getCodeSet() {
    return this.codeset;
  }

  /**
   * @return The {@link CGTClient} this CodeValue is associated to, <code>null</code> if not
   * associated to any Client and is therefore a global CodeValue.
   */
  public CGTClient getClient() {
    return this.client;
  }

  /**
   * @return The display / name of the CodeValue.
   */
  public String getDisplay() {
    return this.codevalue_display;
  }

  /**
   * Gets the meaning of the CodeValue.
   *
   * @return A {@link String} that represents the meaning of the CodeValue.
   */
  public String getMeaning() {
    return this.codevalue_meaning;
  }

  /**
   * Gets the number of times this object has been updated.
   *
   * @return {@link long} number of times the object has been updated.
   */
  public final long getCodeValueUpdateCount() {
    return updt_cnt;
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

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    builder.append(codeset);
    builder.append(client);
    builder.append(codevalue_display);
    builder.append(codevalue_meaning);

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
    if (!(obj instanceof CGTCodeValue)) {
      return false;
    }

    CGTCodeValue rhs = (CGTCodeValue) obj;

    EqualsBuilder builder = new EqualsBuilder();
    builder.append(codeset, rhs.getCodeSet());
    builder.append(client, rhs.getClient());
    builder.append(codevalue_display, rhs.getDisplay());
    builder.append(codevalue_meaning, rhs.getMeaning());

    return builder.build();
  }

  @Override
  public int compareTo(final CGTCodeValue rhs) {
    CompareToBuilder builder = new CompareToBuilder();
    builder.append(codevalue_display, rhs.getDisplay());

    return builder.build();
  }
}
