package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name ,'%')) ")			
	List<SaleMinDTO> getReport(LocalDate minDate, LocalDate maxDate, String name);
	
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSumDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY obj.seller.name")	
	List<SaleSumDTO> getSummary(LocalDate minDate, LocalDate maxDate);
}
