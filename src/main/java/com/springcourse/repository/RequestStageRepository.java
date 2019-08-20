package com.springcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long>{

	@Query("SELECT r FROM request_stage r WHERE id = ?1")
	public List<RequestStage> findAllRequest(Long id);
}
