package jp.co.falsystack.ssiach18final.repositories;

import jp.co.falsystack.ssiach18final.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    @Query("select w from Workout w where w.member = ?#{authentication.name}")
    List<Workout> findAllByMember();

}
