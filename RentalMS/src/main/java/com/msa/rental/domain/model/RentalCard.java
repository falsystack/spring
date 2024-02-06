package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Aggregate Root
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {
    private RentalCardNo rentalCardNo;
    private IDName member;
    private RentStatus rentStatus;
    private LateFee lateFee;
    private List<RentalItem> rentalItemList = new ArrayList<>();
    private List<ReturnItem> returnItemList = new ArrayList<>();

    private void addRentalItem(RentalItem rentalItem) {
        this.rentalItemList.add(rentalItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        this.rentalItemList.remove(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        this.returnItemList.add(returnItem);
    }

    // 대여카드 생성
    public static RentalCard createRentalCard(IDName creator) {
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(creator);
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setLateFee(LateFee.createLateFee());
        return rentalCard;
    }

    // 대여처리
    public RentalCard rentItem(Item item) {
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }

    private void checkRentalAvailable() {
        if (this.rentStatus == RentStatus.RENT_UNAVAILABLE) {
            throw new IllegalArgumentException("대여불가 상태 입니다.");
        }

        if (this.rentalItemList.size() > 5) {
            throw new IllegalArgumentException("대여가능 횟수를 초과하였습니다.");
        }
    }

    // 반납처리
    public RentalCard returnItem(Item item, LocalDate returnDate) {
        RentalItem rentalItem = this.rentalItemList.stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst()
                .get();
        calculateFee(rentalItem, returnDate);
        this.addReturnItem(ReturnItem.createReturnItem(rentalItem));
        this.removeRentalItem(rentalItem);
        return this;
    }

    private void calculateFee(RentalItem rentalItem, LocalDate returnDate) {
        if (returnDate.isAfter(rentalItem.getOverdueDate())) {
            Long point = Period.between(rentalItem.getOverdueDate(), returnDate).getDays() * 10L;
            this.lateFee.addPoint(point);
        }
    }

    // 연체처리
    public RentalCard overdueItem(Item item) {
        RentalItem rentalItem = this.rentalItemList.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
        rentalItem.setOverdued(true);
        this.setRentStatus(RentStatus.RENT_UNAVAILABLE);

        //
        rentalItem.setOverdueDate(LocalDate.now().minusDays(1));
        return this;
    }

    // 연체해제 처리
    public Long makeAvailableRental(Long point) {
        if (!this.rentalItemList.isEmpty()) {
            throw new IllegalArgumentException("모든 도서가 반납되어야 정지를 해제할 수 있습니다.");
        }

        if (this.getLateFee().getPoint() != point) {
            throw new IllegalArgumentException("해당 포인트로 연체를 해제할 수 없습습니다.");
        }

        this.setLateFee(this.lateFee.removePoint(point));
        if (this.getLateFee().getPoint() == 0) {
            this.setRentStatus(RentStatus.RENT_AVAILABLE);
        }
        return this.getLateFee().getPoint();
    }


}
