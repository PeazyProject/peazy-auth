package com.laas.auth.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laas.auth.enumerate.TenantErrorCodeEnumImpl;
import com.laas.auth.exception.ErrorCodeException;
import com.laas.auth.model.entity.TenantEntity;
import com.laas.auth.model.resp.TenantInfoResp;
import com.laas.auth.model.resp.TenantListResp;
import com.laas.auth.repository.TenantRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/tenant",produces = MediaType.APPLICATION_JSON_VALUE)
public class TenantController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	TenantRepository tenantRepository;

	@GetMapping
	public List<TenantListResp> getTenant() throws  JsonProcessingException {
		List<TenantEntity> tenantList = tenantRepository.findAll();
		List<TenantListResp> respList = com.google.common.collect.Lists.newArrayList();
		tenantList.forEach(x->{
			TenantListResp  resp = new TenantListResp();
			BeanUtils.copyProperties(x, resp);
			respList.add(resp);
		});
		return respList;
	}
	
	@GetMapping("info/{tenantCode}/{key}")
	public TenantInfoResp tenantInfo(@PathVariable("tenantCode") String tenantCode,@PathVariable("key") String key) throws  JsonProcessingException {
		TenantEntity tenant = tenantRepository.findByTenantCodeAndAuthKey(tenantCode, key).orElseThrow(()-> new ErrorCodeException(TenantErrorCodeEnumImpl.TENANT_NOT_FOUND));
		TenantInfoResp resp = new TenantInfoResp();
		BeanUtils.copyProperties(tenant, resp);
		return resp;
	}


}
