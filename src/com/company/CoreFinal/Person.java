package com.company.CoreFinal;

import java.util.Random;

public abstract class Person implements Fight{
    String name;
    int hp;
    int power;
    int agility;
    int xp;
    int gold;

    public Person(String name, int hp, int power, int agility, int xp, int gold) {
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.agility = agility;
        this.xp = xp;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public int attack() {
        Random ifCanHit = new Random();
        int lucky = ifCanHit.nextInt(100);
        if (agility * 3 >= lucky) {
            return power;
        } else if (lucky == 10 || lucky == 30 || lucky == 70) {
            power *= 2;
            return power;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s hp:%d", name, hp);
    }
}
