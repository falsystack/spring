package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
