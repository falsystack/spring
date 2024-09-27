package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {

    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentStatus rentStatus;
    private LateFee lateFee;
    private List<RentalItem> rentalItems = new ArrayList<>();
    private List<ReturnItem> returnItems = new ArrayList<>();


    private void addRentalItem(RentalItem rentalItem) {
        this.rentalItems.add(rentalItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        this.rentalItems.remove(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        this.returnItems.add(returnItem);
    }

}
