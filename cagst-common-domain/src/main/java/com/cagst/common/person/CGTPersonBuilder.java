package com.cagst.common.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

import com.cagst.common.CGTObjectBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

/**
 * A mutable representation of a generic Person within the system.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public class CGTPersonBuilder extends CGTObjectBuilder {
  private static final long serialVersionUID = 4546930957600757055L;

  private long person_id;
  private CGTClient client;
  private String name_last;
  private String name_first;
  private String name_middle;
  private DateTime dob;
  private long updt_cnt = 0;

  private final CGTPerson originalCGTPerson;

  /**
   * Primary Constructor used to create an instance of CGTPersonBuilder.
   */
  public CGTPersonBuilder() {
    this.originalCGTPerson = null;
  }

  /**
   * Primary Constructor used to create an instance of CGTPersonBuilder from an existing instance of {@link CGTPerson).
   *
   * @param person
   *     The {@link CGTPerson} to base this CGTPersonBuilder off of.
   */
  public CGTPersonBuilder(final CGTPerson person) {
    super(person);

    copyFrom(person);

    this.originalCGTPerson = person;
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
   * Sets the unique identifier for the Person.
   *
   * @param personUID
   *     A {@link long} that uniquely identifies the Person.
   */
  public void setPersonUID(final long personUID) {
    this.person_id = personUID;
  }

  /**
   * @return The {@link CGTClient} this Person is associated with.
   */
  public CGTClient getClient() {
    return client;
  }

  /**
   * Set the {@link CGTClient} this Person is associated with.
   *
   * @param client
   *     The {@link CGTClient} to associate with this Person.
   */
  public void setClient(final CGTClient client) {
    this.client = client;
  }

  /**
   * Gets the last name for the Person.
   *
   * @return {@link String} the Persons last name.
   */
  @NotNull
  @Size(min = 1, max = 100)
  public String getLastName() {
    return name_last;
  }

  /**
   * Sets the last name for the Person.
   *
   * @param lastName
   *     {@link String} the Persons last name.
   */
  public void setLastName(final String lastName) {
    this.name_last = lastName;
  }

  /**
   * Gets the first name for the Person.
   *
   * @return {@link String} the Persons first name.
   */
  @NotNull
  @Size(min = 1, max = 100)
  public String getFirstName() {
    return name_first;
  }

  /**
   * Sets the first name for the Person.
   *
   * @param firstName
   *     {@link String} the Persons first name.
   */
  public void setFirstName(final String firstName) {
    this.name_first = firstName;
  }

  /**
   * Gets the middle name for the Person.
   *
   * @return {@link String} the Persons middle name.
   */

  @Size(max = 100)
  public String getMiddleName() {
    return name_middle;
  }

  /**
   * Sets the middle name for the Person.
   *
   * @param middleName
   *     {@link String} the Persons middle name.
   */
  public void setMiddleName(final String middleName) {
    this.name_middle = middleName;
  }

  /**
   * Gets the {@link DateTime} of Birth for the Person.
   *
   * @return The {@link DateTime} of Birth for the Person.
   */
  @Past
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
   * Sets the {@link DateTime} of Birth for the Person.
   *
   * @param dob
   *     The {@link DateTime} of Birth for the Person.
   */
  public void setDob(final DateTime dob) {
    this.dob = dob;
  }

  /**
   * Sets the {@link Date} of Birth for the Person.
   *
   * @param dob
   *     The {@link Date} of Birth for the Person.
   */
  public void setDateOfBirth(final Date dob) {
    if (dob == null) {
      this.dob = null;
    }

    this.dob = new DateTime(dob);
  }

  /**
   * Gets the number of times this object has been updated.
   *
   * @return {@link long} number of times the object has been updated.
   */
  public long getCGTPersonUpdateCount() {
    return updt_cnt;
  }

  /**
   * Sets the number of times this object has been updated.
   *
   * @param updateCount
   *     {@link long} the number of times the object has been updated.
   */
  public void setCGTPersonUpdateCount(final long updateCount) {
    this.updt_cnt = updateCount;
  }

  @Override
  public void rejectChanges() {
    if (originalCGTPerson != null) {
      copyFrom(originalCGTPerson);
    } else {
      this.name_first = null;
      this.name_middle = null;
      this.name_last = null;
      this.dob = null;
    }

    super.rejectChanges();
  }

  @Override
  public boolean hasChanged(final boolean deepCheck) {
    if (originalCGTPerson == null) {
      return true;
    }

    EqualsBuilder builder = new EqualsBuilder();
    builder.append(name_first, originalCGTPerson.getFirstName());
    builder.append(name_last, originalCGTPerson.getLastName());
    builder.append(name_middle, originalCGTPerson.getMiddleName());
    builder.append(dob, originalCGTPerson.getDob());
    builder.append(isActive(), originalCGTPerson.isActive());

    return !builder.build();
  }

  @Override
  public boolean isValid() {
    if (StringUtils.isEmpty(name_last)) {
      return false;
    }

    return !StringUtils.isEmpty(name_first);
  }

  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
    builder.append("person_id", person_id);
    builder.append("client", client == null ? StringUtils.EMPTY : client.toString());
    builder.append("name_last", name_last);
    builder.append("name_first", name_first);
    builder.append("dob", dob == null ? StringUtils.EMPTY : dob.toString());
    builder.append("updateCount", updt_cnt);
    builder.appendSuper(super.toString());

    return builder.build();
  }

  /**
   * Builds a {@link CGTPerson} based upon this builder.
   *
   * @return An immutable {@link CGTPerson}.
   */
  public CGTPerson build() {
    return new CGTPerson(this);
  }

  protected void copyFrom(final CGTPerson person) {
    this.person_id = person.getPersonUID();
    this.client = person.getClient();
    this.name_last = person.getLastName();
    this.name_first = person.getFirstName();
    this.name_middle = person.getMiddleName();
    this.dob = person.getDob();
    this.updt_cnt = person.getCGTPersonUpdateCount();

    super.copyFrom(person);
  }
}
