package coms.model.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank(message = "username cannot be null.")
	private String username;

	@NotBlank(message = "password cannot be null.")
	@Size(min = 6, message = "enter minimum six character password")
	private String password;

	@NotBlank(message = "Email cannot be null.")
	private String email;
	
	private boolean enabled = false;
	
	private String referralCode;
	
    private String referredByCode;
    
    private boolean referredVerified = false;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRoles = new HashSet<>();

	public User() {
		super();
	}
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(Long userId, String username, String password, 
			boolean enabled, Set<UserRole> userRoles, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRoles = userRoles;
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReferralCode() {
		return referralCode;
	}
	
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	
	public String getReferredByCode() {
		return referredByCode;
	}
	
	public void setReferredByCode(String referredByCode) {
		this.referredByCode = referredByCode;
	}
	
	public boolean isReferredVerified() {
		return referredVerified;
	}
	
	public void setReferredVerified(boolean referredVerified) {
		this.referredVerified = referredVerified;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> authority = new HashSet<>();
		this.userRoles.forEach(userRole -> {
			authority.add(new Authority(userRole.getRole().getRoleName()));
		});
		return authority;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
