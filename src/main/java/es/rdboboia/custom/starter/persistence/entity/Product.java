package es.rdboboia.custom.starter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/** {@link Product} entity. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldNameConstants
@Table(name = "PRODUCT")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
  @SequenceGenerator(sequenceName = "SEQ_PRODUCT", name = "productSeq", allocationSize = 1)
  @Column
  private Long id;

  @Column private String name;

  @ManyToOne
  @JoinColumn(name = "FK_PRODUCT_TYPE", nullable = false)
  private ProductType type;
}
