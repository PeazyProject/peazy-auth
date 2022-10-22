package com.laas.auth.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.laas.auth.model.entity.UserSubscriptionEntity;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscriptionEntity, Long> {

	List<UserSubscriptionEntity> findBySubscriberIdAndSendingMethod(String subscriberId,String sendingMethod);
	//List<UserSubscriptionEntity> findBySubscriberUserTypeAndIsActivated(String subscriberUserType,Integer isActivated);
	
	@Transactional
	@Modifying
	void deleteBySubscriberIdAndNotifyEventSeqNoIn(String subscriberId, List<Long> notifyEventSeqNo);

}