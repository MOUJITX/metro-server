package com.moujitx.metro.server.service;

import com.moujitx.metro.server.entity.SystemPermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MOUJITX
 */
public interface ISystemPermissionService extends IService<SystemPermission> {
    SystemPermission getPermissionById(String id);
}