package com.laas.auth.repository;

import java.util.List;
import java.util.Optional;

import com.laas.auth.model.entity.NotifyEventContextEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotifyEventContextRepository extends JpaRepository<NotifyEventContextEntity, Long> {

}
