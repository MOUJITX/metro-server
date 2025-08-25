package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moujitx.metro.server.entity.City;
import com.moujitx.metro.server.service.CityService;
import com.moujitx.metro.server.mapper.CityMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【city】的数据库操作Service实现
 * @createDate 2025-08-24 18:51:51
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
        implements CityService {

}
