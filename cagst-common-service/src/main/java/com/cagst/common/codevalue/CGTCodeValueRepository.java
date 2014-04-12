package com.cagst.common.codevalue;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cagst.common.person.CGTClient;

/**
 * Interface for retrieving / saving {@link CGTCodeSet}s and {@link CGTCodeValue}s from / to
 * persistent storage.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
@Repository
public interface CGTCodeValueRepository {
	/**
	 * Retrieves a {@link CGTCodeSet} by its unique identifier.
	 * 
	 * @param uid
	 *          The {@link long} that uniquely identifies the {@link CGTCodeSet} to retrieve.
	 * 
	 * @return The {@link CGTCodeSet} that corresponds to the specified {@link long} unique
	 *         identifier, <code>null</code> if the CodeSet does not exist.
	 */
	public CGTCodeSet getCodeSetByUID(final long uid);

	/**
	 * Retrieves a {@link List} of {@link CGTCodeValues} that are associated to the specified
	 * {@link CGTCodeSet}.
	 * 
	 * @param codeSet
	 *          The {@link CGTCodeSet} to retrieve CodeValues for.
	 * @param client
	 *          The {@link CGTClient} to retrieve the {@link CGTCodeSet} for.
	 * 
	 * @return A {@link List} of {@link CGTCodeValues} that are associated to the specified
	 *         {@link CGTCodeSet} and the specified {@link CGTClient}, an empty list if no CodeValues
	 *         are associated to the CodeSet / Client.
	 */
	public List<CGTCodeValue> getCodeValuesForCodeSet(final CGTCodeSet codeSet, final CGTClient client);
}
