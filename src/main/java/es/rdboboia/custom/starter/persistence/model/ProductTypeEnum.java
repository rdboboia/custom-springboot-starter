package es.rdboboia.custom.starter.persistence.model;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/** Enum that maps all possible values of a {@link ProductType} entity. */
@Getter
@ToString
@AllArgsConstructor
public enum ProductTypeEnum {
  FOOD(1L, "Food"),

  DRINK(2L, "Drink"),

  OTHER(3L, "Other");

  private final Long id;
  private final String name;

  /**
   * Builds and returns a new {@link ProductType} entity based on the instance of {@link
   * ProductTypeEnum}.
   *
   * @return the {@link ProductType} entity.
   */
  public ProductType getEntity() {
    return ProductType.builder().id(this.id).name(this.name).build();
  }

  /**
   * Retrieves the {@link ProductTypeEnum} from the {@link ProductType} ID.
   *
   * @param id the ID of the {@link ProductType}.
   * @return the {@link ProductTypeEnum} that corresponds to the provided {@link ProductType} ID.
   */
  public static ProductTypeEnum getEnumFromId(Long id) {
    for (ProductTypeEnum e : ProductTypeEnum.values()) {
      if (e.getId().equals(id)) {
        return e;
      }
    }

    throw new IllegalArgumentException("No enum value for provided ID: " + id);
  }
}
