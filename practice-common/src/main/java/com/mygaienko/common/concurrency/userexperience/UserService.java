package com.mygaienko.common.concurrency.userexperience;

public interface UserService {

    long getLevel(long userId);

    long getExperience(long userId);

    void addSubscriber(Subscriber subscriber);

    void addExperience(long userId, long experience);
}
