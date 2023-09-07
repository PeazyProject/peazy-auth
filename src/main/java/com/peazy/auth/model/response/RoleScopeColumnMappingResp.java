package com.peazy.auth.model.response;

import lombok.Data;

@Data
public class RoleScopeColumnMappingResp {
    private String scopeColumnName;
    private String functionName;
    private String mappingColumnName;
}
