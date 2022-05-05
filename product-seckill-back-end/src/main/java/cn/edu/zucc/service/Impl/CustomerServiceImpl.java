package cn.edu.zucc.service.Impl;


import cn.edu.zucc.domain.Customer;
import cn.edu.zucc.mapper.CustomerMapper;
import cn.edu.zucc.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
}
