package br.com.campelo.marcos.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ORD_ORDER")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id", nullable = false)
    private Long id;
    @Column(name = "ord_number_control", nullable = false)
    private BigInteger controlNumber;
    @Column(name = "ord_date_register", nullable = false)
    private LocalDate dateRegister;
    @Column(name = "ord_product_name", nullable = false)
    private String productName;
    @Column(name = "ord_price", nullable = false)
    private BigDecimal price;
    @Column(name = "ord_total_order_price", nullable = false)
    private BigDecimal totalOrderPrice;
    @Column(name = "ord_amount", nullable = false)
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "cli_id", nullable = false)
    private ClientEntity client;
}
