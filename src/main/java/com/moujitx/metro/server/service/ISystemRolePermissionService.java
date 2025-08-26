package com.moujitx.metro.server.service;

import com.moujitx.metro.server.entity.SystemRolePermission;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MOUJITX
 */
public interface ISystemRolePermissionService extends IService<SystemRolePermission> {
    List<SystemRolePermission> getRolePermissionsByRoleId(String roleId);
}