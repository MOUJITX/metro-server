package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moujitx.metro.server.entity.MetroStationVo;
import com.moujitx.metro.server.mapper.MetroStationVoMapper;
import com.moujitx.metro.server.service.IMetroStationVoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MOUJITX
 */
@Service
public class MetroStationVoServiceImpl extends ServiceImpl<MetroStationVoMapper, MetroStationVo> implements IMetroStationVoService {

}