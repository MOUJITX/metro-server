package com.moujitx.metro.server.service;

import com.moujitx.metro.server.entity.MetroLineStation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MOUJITX
 */
public interface IMetroLineStationService extends IService<MetroLineStation> {

    List<MetroLineStation> getLinesByStationId(String stationId);

}