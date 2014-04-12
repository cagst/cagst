package com.cagst.common.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.cagst.common.db.DataSourceFactory;
import com.cagst.common.db.StatementLoader;
import com.cagst.common.person.CGTUser;

/**
 * Test class for CGTUserRepositoryJdbc.
 * 
 * @author Craig Gaskill
 * 
 * @version 1.0.0
 * 
 */
@RunWith(JUnit4.class)
public class CGTUserRepositoryJdbcTest {
	private CGTUserRepositoryJdbc repo;

	@Before
	public void setUp() {
		repo = new CGTUserRepositoryJdbc(createTestDataSource());
		repo.setStatementDialect(StatementLoader.HSQLDB_DIALECT);
	}

	/**
	 * Tests the getUserByUsername and not finding any CGTUser.
	 */
	@Test
	public void testGetUserByUsername_NotFound() {
		CGTUser user = repo.getUserByUsername("billlybob");
		assertNull(user);
	}

	/**
	 * Tests the getUserByUsername and finding the CGTUser.
	 */
	@Test
	public void testGetUserByUsername_Found() {
		CGTUser user = repo.getUserByUsername("cgaskill");
		assertNotNull(user);
		assertEquals("cgaskill", user.getUsername());
		assertEquals("Craig", user.getFirstName());
		assertEquals("Gaskill", user.getLastName());

		DateTime lastSigninDate = user.getLastSigninDate();
		assertNotNull(lastSigninDate);
	}

	/**
	 * Tests the getUserByUsername where account will expire but hasn't expired yet.
	 */
	@Test
	public void testGetUserByUsername_WillExpired() {
		CGTUser user = repo.getUserByUsername("temp");
		assertNotNull(user);
		assertEquals("temp", user.getUsername());

		DateTime expireDate = user.getExpireDate();
		assertNotNull(expireDate);
		assertTrue(user.isAccountNonExpired());
	}

	/**
	 * Tests the getUserByUsername where account has expired.
	 */
	@Test
	public void testGetUserByUsername_Expired() {
		CGTUser user = repo.getUserByUsername("expire");
		assertNotNull(user);
		assertEquals("expire", user.getUsername());

		DateTime expireDate = user.getExpireDate();
		assertNotNull(expireDate);
		assertFalse(user.isAccountNonExpired());
	}

	/**
	 * Tests the getUserByUsername where account has been locked.
	 */
	@Test
	public void testGetUserByUsername_Locked() {
		CGTUser user = repo.getUserByUsername("locked");
		assertNotNull(user);
		assertEquals("locked", user.getUsername());
		assertEquals(5, user.getSigninAttempts());

		DateTime lockedDate = user.getAccountLockedDate();
		assertNotNull(lockedDate);
		assertFalse(user.isAccountNonLocked());
	}

	/**
	 * Tests the incrementSigninAttempts method.
	 */
	@Test
	public void testIncrementSigninAttempts() {
		CGTUser user1 = repo.getUserByUsername("cgaskill");
		assertNotNull(user1);
		assertEquals("cgaskill", user1.getUsername());
		assertEquals(0, user1.getSigninAttempts());

		CGTUser user = repo.incrementSigninAttempts(user1);
		assertNotNull(user);
		assertEquals("cgaskill", user.getUsername());
		assertEquals(1, user.getSigninAttempts());

		CGTUser user2 = repo.getUserByUsername("cgaskill");
		assertNotNull(user2);
		assertEquals("cgaskill", user2.getUsername());
		assertEquals(1, user2.getSigninAttempts());
	}

	/**
	 * Tests the updateLastSigninDatetime method.
	 */
	@Test
	public void testUpdateLastSigninDatetime() {
		CGTUser user1 = repo.getUserByUsername("temp");
		assertNotNull(user1);
		assertEquals("temp", user1.getUsername());
		assertNull(user1.getLastSigninDate());

		CGTUser user = repo.updateLastSigninDatetime(user1);
		assertNotNull(user);
		assertEquals("temp", user.getUsername());
		assertNotNull(user.getLastSigninDate());

		CGTUser user2 = repo.getUserByUsername("temp");
		assertNotNull(user2);
		assertEquals("temp", user2.getUsername());
		assertNotNull(user2.getLastSigninDate());
	}

	/**
	 * Tests the changeUserPassword method.
	 */
	@Test
	public void testChangeUserPassword() {
		CGTUser user1 = repo.getUserByUsername("temp");
		assertNotNull(user1);
		assertEquals("temp", user1.getUsername());
		assertEquals("temp", user1.getPassword());
		assertTrue(user1.needToChangePassword());

		CGTUser user2 = repo.changeUserPassword(user1, "newPassword");
		assertNotNull(user2);
		assertEquals("temp", user2.getUsername());
		assertEquals("newPassword", user2.getPassword());
		assertFalse(user2.needToChangePassword());
	}

	/**
	 * Tests the changeUserPassword method with a null password.
	 */
	@Test
	public void testChangeUserPassword_NullPassword() {
		CGTUser user1 = repo.getUserByUsername("temp");
		assertNotNull(user1);
		assertEquals("temp", user1.getUsername());
		assertEquals("temp", user1.getPassword());

		try {
			repo.changeUserPassword(user1, null);
			fail("should have thrown an exception and not reached this point");
		} catch (IllegalArgumentException ex) {
			assertNotNull(ex);
		}
	}

	/**
	 * Tests the changeUserPassword method with an empty password
	 */
	@Test
	public void testChangeUserPassword_EmptyPassword() {
		CGTUser user1 = repo.getUserByUsername("temp");
		assertNotNull(user1);
		assertEquals("temp", user1.getUsername());
		assertEquals("temp", user1.getPassword());

		try {
			repo.changeUserPassword(user1, StringUtils.EMPTY);
			fail("should have thrown an exception and not reached this point");
		} catch (IllegalArgumentException ex) {
			assertNotNull(ex);
		}
	}

	private DataSource createTestDataSource() {
		Resource schemaLocation = new ClassPathResource("/testDb/schema.sql");
		Resource testDataLocation = new ClassPathResource("/testDb/test_data.sql");

		DataSourceFactory dsFactory = new DataSourceFactory("savedb", schemaLocation, testDataLocation);
		return dsFactory.getDataSource();
	}
}
