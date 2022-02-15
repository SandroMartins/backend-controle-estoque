package com.controle.estoque.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.estoque.domain.TipoProduto;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long>{

}
