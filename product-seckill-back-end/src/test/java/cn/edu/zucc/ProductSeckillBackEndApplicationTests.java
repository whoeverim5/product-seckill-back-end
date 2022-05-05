package cn.edu.zucc;

import cn.edu.zucc.domain.Customer;
import cn.edu.zucc.service.ICustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ProductSeckillBackEndApplicationTests {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    ICustomerService customerService;

    @Test
    void testGetById() {
        Customer customer = customerService.getById(1);
        System.out.println(customer);
    }

    @Test
    void testGetAuthorization() {

    }

}
