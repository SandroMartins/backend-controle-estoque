package com.controle.estoque.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.controle.estoque.domain.MovimentacaoEstoque;
import com.controle.estoque.domain.Produto;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM MovimentacaoEstoque obj INNER JOIN obj.produto p WHERE p = :produto ")
	Page<MovimentacaoEstoque> findDistinctByProduto(@Param("produto") Produto produto, Pageable pageRequest);

}
