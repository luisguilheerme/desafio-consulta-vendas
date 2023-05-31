package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public List<SaleMinDTO> getReport(String minDate, String maxDate, String name) {

		if(minDate == "" || minDate == null) {
			LocalDate min = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L);
			minDate = min.toString();
		}
		if(maxDate == "" || maxDate == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			maxDate = today.toString();
		}	
				
		LocalDate minimumDate = LocalDate.parse(minDate);
		LocalDate maximumDate = LocalDate.parse(maxDate);
		List<SaleMinDTO> result = repository.getReport(minimumDate, maximumDate, name);
		return result;
	}
	
	public List<SaleSumDTO> getSummary(String minDate, String maxDate) {

		if(minDate == "" || minDate == null) {
			LocalDate min = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L);
			minDate = min.toString();
		}
		if(maxDate == "" || maxDate == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			maxDate = today.toString();
		}	
				
		LocalDate minimumDate = LocalDate.parse(minDate);
		LocalDate maximumDate = LocalDate.parse(maxDate);
		List<SaleSumDTO> result = repository.getSummary(minimumDate, maximumDate);
		return result;
	}
	
}
