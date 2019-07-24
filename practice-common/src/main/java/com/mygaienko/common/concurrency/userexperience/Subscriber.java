package com.mygaienko.common.concurrency.userexperience;

public interface Subscriber {

    void notify(long userId, long lvl);

}
