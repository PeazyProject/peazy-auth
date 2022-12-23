package com.peazy.auth.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.peazy.auth.model.entity.MenuEntity;
import com.peazy.auth.repository.MenuRepository;
import com.peazy.auth.service.interfaces.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public List<MenuEntity> findAll() {
		return menuRepository.findAll(Sort.by("ParentNodeSeqNo").and(Sort.by("SortPriority")));
	}

}
