package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCardNo {
    private String no;

    public static RentalCardNo createRentalCardNo() {
        return new RentalCardNo(UUID.randomUUID() + "-" + LocalDate.now().getYear());
    }
}