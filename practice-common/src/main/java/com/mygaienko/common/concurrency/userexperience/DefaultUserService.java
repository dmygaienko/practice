package com.mygaienko.common.concurrency.userexperience;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.LongStream;

@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private List<Subscriber> subscribers = new CopyOnWriteArrayList<>();

    private Map<Long, UserInfo> userInfos = new ConcurrentHashMap<>();

    private final ConfigProvider configProvider;

    @Override
    public long getLevel(long userId) {
        return getUserInfo(userId)
                .map(UserInfo::getLevel)
                .orElse(0L);
    }

    private Optional<UserInfo> getUserInfo(long userId) {
        return Optional.ofNullable(userInfos.get(userId));
    }

    @Override
    public long getExperience(long userId) {
        return getUserInfo(userId)
                .map(UserInfo::getExperience)
                .orElse(0L);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void addExperience(long userId, long experience) {
        userInfos.compute(userId,
                (existingUserId, userInfo) -> {
                    long newExperience = (userInfo == null ? 0 : userInfo.getExperience()) + experience;
                    long previousLevel = userInfo == null ? 0 : userInfo.getLevel();
                    long newLevel = computeLevel(previousLevel, newExperience);
                    notifyAll(userId, previousLevel, newLevel);
                    return new UserInfo(
                            userId,
                            newLevel,
                            newExperience);
                });
    }

    private void notifyAll(long userId, long previousLevel, long newLevel) {
        if (newLevel <= previousLevel) {
            return;
        }

        LongStream.rangeClosed(previousLevel + 1, newLevel)
                .forEach(levelRaised ->
                        subscribers
                                .forEach(subscriber -> subscriber.notify(userId, levelRaised)));
    }

    private long computeLevel(long previousLevel, long experience) {
        return new TreeMap<>(configProvider.getConfigs())
                .tailMap(previousLevel)
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > experience)
                .map(entry -> entry.getKey() - 1)
                .findFirst()
                .orElse(12L);
    }

}
