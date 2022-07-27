package com.userfront.domain.security;

import org.springframework.security.core.GrantedAuthority;


 
/**
 * The Class Authority.
 */
public class Authority implements GrantedAuthority{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The authority. */
    private final String authority;

    /**
     * Instantiates a new authority.
     *
     * @param authority the authority
     */
    public Authority(String authority) {
        this.authority = authority;
    }

    /**
     * Gets the authority.
     *
     * @return the authority
     */
    @Override
    public String getAuthority() {
        return authority;
    }
}
