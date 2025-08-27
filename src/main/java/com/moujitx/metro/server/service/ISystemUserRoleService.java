package com.moujitx.metro.server.service;

import com.moujitx.metro.server.entity.SystemUserRole;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MOUJITX
 */
public interface ISystemUserRoleService extends IService<SystemUserRole> {
    List<SystemUserRole> getUserRolesByUserId(String userId);

    Boolean saveOrUpdateUserRoles(String userId, List<String> roleIds);
}