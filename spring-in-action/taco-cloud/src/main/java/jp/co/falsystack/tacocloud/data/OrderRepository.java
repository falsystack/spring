package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}
