package cn.edu.zucc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String account;
    private String password;
    private Long shopCartId;
    private String nickName;
    private String telephone;
    private String address;
}
