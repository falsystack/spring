package jp.co.falsystack.ssiach18final.services;

import jp.co.falsystack.ssiach18final.entities.Workout;
import jp.co.falsystack.ssiach18final.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @PreAuthorize("authentication.authenticated == true")
    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    public List<Workout> findWorkouts() {
        return workoutRepository.findAllByMember();
    }

    public void deleteWorkout(Integer id) {
        workoutRepository.deleteById(id);
    }
}
