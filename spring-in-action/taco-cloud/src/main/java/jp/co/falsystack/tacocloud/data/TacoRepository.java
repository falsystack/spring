package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
