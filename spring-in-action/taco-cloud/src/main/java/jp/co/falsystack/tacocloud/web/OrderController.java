package jp.co.falsystack.tacocloud.web;

import jakarta.validation.Valid;
import jp.co.falsystack.tacocloud.Order;
import jp.co.falsystack.tacocloud.User;
import jp.co.falsystack.tacocloud.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProps orderProps;


    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        var pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
