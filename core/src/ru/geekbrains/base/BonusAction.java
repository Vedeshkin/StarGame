package ru.geekbrains.base;

import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.MainShip;

public interface BonusAction {

    void performAction(EnemyPool enemyPool, MainShip mainShip, BulletPool bulletPool);

}
