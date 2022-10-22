package com.laas.auth.enumerate;

public enum RoleCodeEnum {
    CSR("CSR"),
    PCSR("PCSR"),
    ;
    
    private String roleCode;
    
    private RoleCodeEnum(String roleCode) {
        this.setRoleCode(roleCode);
    }
    
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
