package com.laas.auth.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laas.auth.model.entity.NotifyEventEntity;

@Repository
public interface NotifyEventRepository extends JpaRepository<NotifyEventEntity, Long> {
    List<NotifyEventEntity> findByIsUserVisible(Integer isUserVisible);
}
