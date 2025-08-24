package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moujitx.metro.server.entity.Type;
import com.moujitx.metro.server.service.TypeService;
import com.moujitx.metro.server.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【type】的数据库操作Service实现
* @createDate 2025-08-24 18:52:26
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




