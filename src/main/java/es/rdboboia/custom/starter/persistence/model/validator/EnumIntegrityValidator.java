package es.rdboboia.custom.starter.persistence.model.validator;

import es.rdboboia.custom.starter.persistence.entity.ProductType;
import es.rdboboia.custom.starter.persistence.model.ProductTypeEnum;
import es.rdboboia.custom.starter.persistence.repository.ProductTypeRepository;
import es.rdboboia.custom.starter.utils.LoggerUtils;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 * This class validates that the defined enums are consistent with the DB data that they represent
 * at application startup.
 */
@RequiredArgsConstructor
@Component
// @Profile({ "local" })
public class EnumIntegrityValidator {
  // TODO: review log usage.
  Logger log = org.apache.logging.log4j.LogManager.getLogger(EnumIntegrityValidator.class);
  LoggerUtils loggerUtilsInstance = LoggerUtils.getLoggerUtilsInstance();

  private final ProductTypeRepository productTypeRepository;

  /** Validate {@link ProductTypeEnum}. */
  @EventListener(ApplicationReadyEvent.class)
  public void validateProductTypeEnumIntegrity() {
    this.loggerUtilsInstance.logEnteringMethod(this.log);

    this.log.info("Validating Product type enum integrity...");

    List<ProductType> all = this.productTypeRepository.findAll();
    ProductTypeEnum[] values = ProductTypeEnum.values();
    this.checkSize(all, values);

    for (ProductTypeEnum value : values) {
      ProductType entity = value.getEntity();
      this.checkIntegrity(all, entity);
    }

    this.log.info("Product type enum integrity is valid.");
  }

  /**
   * Validate the DB list and Enum list have the same length.
   *
   * @param <T> the DB type.
   * @param <E> the enum type.
   * @param dbValues the DB value list.
   * @param enumValues the enum list.
   */
  private <T, E> void checkSize(List<T> dbValues, E[] enumValues) {
    if (dbValues.size() != enumValues.length) {
      throw new DataIntegrityViolationException("Enum and DB entity sizes don't match.");
    }
  }

  /**
   * Validate that the provided enum entity is equals to one element of the DB list.
   *
   * @param <T> the DB entity type.
   * @param dbList the DB entity list.
   * @param enumEntity the enum entity to validate.
   */
  private <T> void checkIntegrity(List<T> dbList, Object enumEntity) {
    Optional<T> first = dbList.stream().filter(e -> e.equals(enumEntity)).findFirst();
    if (first.isEmpty()) {
      throw new DataIntegrityViolationException(
          "The following enum's integrity is not valid: " + enumEntity);
    }
  }
}
