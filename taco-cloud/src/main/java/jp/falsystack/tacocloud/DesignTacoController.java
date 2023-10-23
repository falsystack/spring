package jp.falsystack.tacocloud;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jp.falsystack.tacocloud.Ingredient.Type;
import jp.falsystack.tacocloud.data.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/design")
public class DesignTacoController {

  private final IngredientRepository ingredientRepository;

  @GetMapping
  public String showDesignForm(Model model) {
    var ingredients = new ArrayList<Ingredient>();
    ingredientRepository.findAll().forEach(ingredients::add);

    Type[] types = Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    model.addAttribute("taco", new Taco());

    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors) {
    if (errors.hasErrors()) {
      return "design";
    }

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
        .stream()
        .filter(ingredient -> ingredient.getType().equals(type))
        .toList();
  }
}
