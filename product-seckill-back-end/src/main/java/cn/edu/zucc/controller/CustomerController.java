package cn.edu.zucc.controller;

import cn.edu.zucc.domain.Customer;
import cn.edu.zucc.service.ICustomerService;
import cn.edu.zucc.utils.Constant;
import cn.edu.zucc.utils.JwtUtil;
import cn.edu.zucc.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping
    public Result verifyToken(@RequestHeader("Authorization") String bearerToken) {
        String token = JwtUtil.renameToken(bearerToken);
        if (token != null) {
            Long exp = JwtUtil.getExpiration(token);
            if (exp != null && exp > System.currentTimeMillis()) {
                return Result.ok(Constant.AUTHORIZED, "用户匹配成功");
            }
        }
        return Result.fail(Constant.UNAUTHORIZED, "登录已过期，请重新登录");
    }

    @GetMapping("/{account}/{password}")
    public Result login(@PathVariable String account, @PathVariable String password) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq(Strings.isNotEmpty(account), "account", account);
        Customer one = customerService.getOne(wrapper);
        if (one != null && one.getPassword().equals(password)) {
            String token = JwtUtil.createToken(one.getId(), account, password);
            return Result.ok(token, 200, "登陆成功");
        }
        return Result.fail(400);
    }

}
