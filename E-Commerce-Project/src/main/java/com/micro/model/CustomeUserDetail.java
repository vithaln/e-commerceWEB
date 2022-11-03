
  package com.micro.model;
  
  import java.util.ArrayList; import java.util.Collection; import
  java.util.List;
  
  import org.springframework.security.core.GrantedAuthority; import
  org.springframework.security.core.authority.SimpleGrantedAuthority; import
  org.springframework.security.core.userdetails.UserDetails;
  
  public class CustomeUserDetail extends User implements UserDetails {
  
 
		  
		  
		  
		  public CustomeUserDetail(User user) {
		  
		  super(user); }
		  
		  
		  public Collection<? extends GrantedAuthority> getAuthorities() {
		  
		  List<GrantedAuthority> authorityList=new ArrayList<>();
		  super.getRoles().forEach(role -> { authorityList.add(new
		  SimpleGrantedAuthority(role.getName())); }); return authorityList; }
		  
		  public String getUsername() {
		  
		  return super.getEmail(); }
		  
		  public String getPassword() {
		  
		  return super.getPassword(); } public boolean isAccountNonExpired() {
		  
		  return true; }
		  
		  public boolean isAccountNonLocked() {
		  
		  return true; }
		  
		  public boolean isCredentialsNonExpired() {
		  
		  return true; }
		  
		  public boolean isEnabled() {
		  
		  return true; }
		  
		  
		  
		  }
		 