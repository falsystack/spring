package jp.falsystack.tacocloud;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class Taco {

  private Long id;
  private LocalDate createdAt;

  @NotNull
  @Size(min = 5, message = "Nmae must be at least 5 characters long")
  private String name;

  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<String> ingredients;

}
