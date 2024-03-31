package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class LateFee {

    private long point;

    public LateFee addPoint(long point) {
        return LateFee.of(this.point + point);
    }

    public LateFee removePoint(long point) {
        if (point > this.point) {
            throw new RuntimeException("보유한 포인트보다 커서 삭감할수 없습니다.");
        }
        return LateFee.of(this.point - point);
    }
}
