package com.mega.tweaks.model.mapper;

import com.mega.tweaks.model.entity.TestUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TestUserMapper extends Mapper<TestUser> {

}
