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

/** {@link ProductTag} entity. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldNameConstants
@Table(name = "PRODUCT_TAG")
public class ProductTag {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productTagSeq")
  @SequenceGenerator(sequenceName = "SEQ_PRODUCT_TAG", name = "productTagSeq", allocationSize = 1)
  @Column
  private Long id;

  @Column private String name;
}
