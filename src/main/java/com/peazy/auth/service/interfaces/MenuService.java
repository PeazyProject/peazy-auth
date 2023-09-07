package com.peazy.auth.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.peazy.auth.model.entity.MenuEntity;

@Service
public interface MenuService {
	List<MenuEntity> findAll();
}
