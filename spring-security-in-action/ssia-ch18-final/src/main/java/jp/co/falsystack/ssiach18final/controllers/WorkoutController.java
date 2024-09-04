package jp.co.falsystack.ssiach18final.controllers;

import jp.co.falsystack.ssiach18final.entities.Workout;
import jp.co.falsystack.ssiach18final.services.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    public void add(@RequestBody Workout workout, Authentication authentication, @AuthenticationPrincipal OAuth2User user) {
        workoutService.saveWorkout(workout);
//        user.getUsername();
    }

    @GetMapping
    public List<Workout> findAll() {
        return workoutService.findWorkouts();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        workoutService.deleteWorkout(id);
    }
}
