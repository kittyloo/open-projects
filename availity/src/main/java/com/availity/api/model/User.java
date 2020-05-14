/**
 * 
 */
package com.availity.api.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author mangeles
 *
 */
@JsonPropertyOrder({ "lastName","firstName","userId","version","insuranceProvider" })
public class User implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8571730000244721301L;

	private String lastName;
	
	private String firstName;
	
	private String userId;
	
	private int version;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String insuranceProvider;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the insuranceProvider
	 */
	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	/**
	 * @param insuranceProvider the insuranceProvider to set
	 */
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	
	@Override
	public boolean equals(Object o) {
		return o!=null &&
			(o instanceof User) &&
			((User)o).userId.equals(this.userId);
	}

	@Override
	public int hashCode() {
		return userId.hashCode(); 
	} 
			
	@Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", version='" + version + '\'' +
                '}';
    }


}
