/**
 * 
 */
package com.availity.api.util;

import java.util.Comparator;

import com.availity.api.model.User;

/**
 * @author mangeles
 *
 */
public class VersionComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		return o1.getUserId().compareToIgnoreCase(o2.getUserId());
	}
	
}
