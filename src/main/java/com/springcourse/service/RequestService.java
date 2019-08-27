package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;

	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		Request createdRequest = requestRepository.save(request);
		return createdRequest;
	}

	public Request update(Request request) {
		Request updateRequest = requestRepository.save(request);
		return updateRequest;
	}

	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Request não encontrado com este id: " + id));
	}

	public List<Request> listAll() {
		List<Request> requets = requestRepository.findAll();
		return requets;
	}
	
	public PageModel<Request> listAllOnLazyModel(PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAll(pageable);
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}

	public List<Request> listByOwnerId(Long id) {
		List<Request> requets = requestRepository.findAllByOwnerId(id);
		return requets;
	}

	public PageModel<Request> listByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pr) {
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);
		PageModel<Request> pm = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(),
				page.getContent());
		return pm;
	}

}
