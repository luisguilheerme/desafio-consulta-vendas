package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<List<SaleMinDTO>> getReport(
			@RequestParam(name = "minDate", required = false) String minDate, 
			@RequestParam(name = "maxDate", required = false) String maxDate, 
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {		
		List<SaleMinDTO> dto = service.getReport(minDate, maxDate, name);		
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSumDTO>> getSummary(
			@RequestParam(name = "minDate", required = false) String minDate, 
			@RequestParam(name = "maxDate", required = false) String maxDate) {
		List<SaleSumDTO> dto = service.getSummary(minDate, maxDate);		
		return ResponseEntity.ok(dto);
	}
	
	
}
