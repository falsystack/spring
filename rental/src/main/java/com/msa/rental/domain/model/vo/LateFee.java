package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LateFee {
    private long point;

    public LateFee addPoint(Long point) {
        return new LateFee(this.point + point);
    }

    public LateFee removePoint(Long point) {
        if (point > this.point) {
            throw new RuntimeException("삭제할 수 없습니다.");
        }

        return new LateFee(this.point - point);
    }

    public static LateFee createLateFee() {
        return new LateFee(0);
    }
}
