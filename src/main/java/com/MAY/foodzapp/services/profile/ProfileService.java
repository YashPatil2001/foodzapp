package com.MAY.foodzapp.services.profile;

import com.MAY.foodzapp.entities.Profile;
import com.MAY.foodzapp.models.LoginUserModel;
import com.MAY.foodzapp.utils.ResultFlags;

public interface ProfileService {

    ResultFlags createUser(Profile profile);

    ResultFlags validateCode(String code);

    Long loginUser(LoginUserModel user);
}
