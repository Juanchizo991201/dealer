package com.firstapp.crud.car;

import com.firstapp.crud.car.dto.UpdateCarPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends ListCrudRepository<Car, String> {

    @Query(value =
            "UPDATE cars " +
            "SET price = :#{#newCarPrice.price} " +
            "WHERE registration = :#{#newCarPrice.registration}", nativeQuery = true)
    @Modifying
    void updateCarPrice(@Param ("newCarPrice")UpdateCarPriceDto dto);
}
