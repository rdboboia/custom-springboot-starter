package es.rdboboia.custom.starter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/** {@link ProductType} entity. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldNameConstants
@Table(name = "PRODUCT_TYPE")
public class ProductType {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productTypeSeq")
  @SequenceGenerator(sequenceName = "SEQ_PRODUCT_TYPE", name = "productTypeSeq", allocationSize = 1)
  @Column
  private Long id;

  @Column private String name;
}
