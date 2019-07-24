package com.mygaienko.common.concurrency.userexperience;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserInfo {

    private final long id;
    private final long level;
    private final long experience;

}
