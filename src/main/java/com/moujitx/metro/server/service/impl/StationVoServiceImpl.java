package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moujitx.metro.server.entity.StationVo;
import com.moujitx.metro.server.service.StationVoService;
import com.moujitx.metro.server.mapper.StationVoMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【station_vo】的数据库操作Service实现
 * @createDate 2025-08-24 19:19:19
 */
@Service
public class StationVoServiceImpl extends ServiceImpl<StationVoMapper, StationVo>
        implements StationVoService {

}
