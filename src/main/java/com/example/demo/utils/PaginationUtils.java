package com.example.demo.utils;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.BaseEntity;

public class PaginationUtils {

	public static <T extends BaseEntity> Page<T> findPaginated(List<T> listOfEntities, Pageable pageable){
		Integer pageSize = pageable.getPageSize();
		Integer currentPage = pageable.getPageNumber();
		Integer startItem = currentPage * pageSize;
		
		List<T> pagedList;

		if (listOfEntities.size() < startItem) {
			pagedList = Collections.emptyList();
		} else {
			Integer toIndex = Math.min(startItem + pageSize, listOfEntities.size());
			pagedList = listOfEntities.subList(startItem, toIndex);
		}

		Page<T> entityPage = new PageImpl<T>(pagedList, PageRequest.of(currentPage, pageSize),
				listOfEntities.size());

		return entityPage;
	}
}
