package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long>{

	@Query("SELECT r FROM request_stage r where id = ?1")
	public List<RequestStage> findAllRequestId(Long id);
	
	@Query("SELECT r FROM request_stage r where id = ?1")
	public Page<RequestStage> findAllRequestId(Long id, Pageable pageable);
}
