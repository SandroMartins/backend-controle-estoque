package com.controle.estoque.domain.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.controle.estoque.domain.Produto;
import com.controle.estoque.domain.TipoProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.tipoProduto tp WHERE tp = :tipoProduto")
	Page<Produto> findDistinctByTipoProduto(@Param("tipoProduto") Optional<TipoProduto> tipoProduto, Pageable pageRequest);

}
