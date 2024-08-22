package com.api.gestaodelucro.produto;

import com.api.gestaodelucro.peca.Peca;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
@Table(name = "tab_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "peca_id")
    @JsonBackReference
    private Peca peca;
    private Long idProduto;
    private BigDecimal quantidade;
    private BigDecimal percentual;
    private BigDecimal custo;
    private BigDecimal venda;
    private BigDecimal total;
}
