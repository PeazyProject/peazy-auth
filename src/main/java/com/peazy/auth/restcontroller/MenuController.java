package com.peazy.auth.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peazy.auth.model.entity.MenuEntity;
import com.peazy.auth.service.interfaces.MenuService;

@RestController
@RequestMapping(path = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MenuService menuService;

	@PostMapping(value = "/findAll")
	public ResponseEntity<List<MenuEntity>> findAll() {
		List<MenuEntity> userList = menuService.findAll();
		return ResponseEntity.ok(userList);
	}

}
