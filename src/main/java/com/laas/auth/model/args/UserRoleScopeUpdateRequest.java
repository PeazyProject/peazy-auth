package com.laas.auth.model.args;

import java.util.List;

public class UserRoleScopeUpdateRequest {
	private Long roleCode;
	private List<UserRoleScopeForwarderUpdateRequest> forwarders;
	private List<String> shippings;
	private List<String> customers;
	private List<UserRoleScopeTenantUpdateRequest> tenants;

	public List<String> getShippings() {
		return shippings;
	}
	public void setShippings(List<String> shippings) {
		this.shippings = shippings;
	}
	public List<UserRoleScopeTenantUpdateRequest> getTenants() {
		return tenants;
	}
	public void setTenants(List<UserRoleScopeTenantUpdateRequest> tenants) {
		this.tenants = tenants;
	}
	public Long getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}
	public List<String> getCustomers() {
		return customers;
	}
	public void setCustomers(List<String> customers) {
		this.customers = customers;
	}
	public List<UserRoleScopeForwarderUpdateRequest> getForwarders() {
		return forwarders;
	}
	public void setForwarders(List<UserRoleScopeForwarderUpdateRequest> forwarders) {
		this.forwarders = forwarders;
	}
}
