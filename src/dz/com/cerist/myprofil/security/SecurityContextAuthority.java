package dz.com.cerist.myprofil.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Maps the groups defined in LDAP to roles for a specific user.
 */
public enum SecurityContextAuthority implements GrantedAuthority {
	
    // These roles are specified in the security context (security.xml) and are
    // mapped to LDAP roles by the ActiveDirectoryGrantedAuthoritiesMapper
    ROLE_ADMIN;

    @Override
	public String getAuthority() {
        return name();
    }
}