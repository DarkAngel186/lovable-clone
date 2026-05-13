package com.lp.projects.lovable_clone.mapper;

import com.lp.projects.lovable_clone.dto.auth.UserProfleResponse;
import com.lp.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfleResponse userToUserProfileResponse(User user);
}
