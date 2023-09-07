package com.peazy.auth.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peazy.auth.model.entity.MenuEntity;
import com.peazy.auth.service.interfaces.MenuService;

@CrossOrigin
@RestController
@RequestMapping(path = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping(value = "/fetchMenu")
	public ResponseEntity<List<MenuEntity>> fetchMenu() {
		List<MenuEntity> menuList = menuService.findAll();
		return ResponseEntity.ok(menuList);
	}

}
