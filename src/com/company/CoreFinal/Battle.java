package com.company.CoreFinal;

public class Battle {
    public void fight(Person hero, Person monster, Realm.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Round: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Person attacker, Person defender, Realm.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHp() - hit;
        if (hit != 0) {
            System.out.printf("%s hit %d strength!%n", attacker.getName(), hit);
            System.out.printf("%s has %d hp %n", defender.getName(), defenderHealth);
        } else {
            System.out.printf("%s lost his hit!%n", attacker.getName());
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("wasted");
            fightCallback.fightLost();
            return true;
        } else if(defenderHealth <= 0) {
            System.out.printf("You won! And have had %d xp and %d gold%n", defender.getXp(), defender.getGold());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }
}

