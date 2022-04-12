package com.company.CoreFinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Realm {
    private static BufferedReader input;
    private static Person player = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        System.out.println("Input your name");
        input = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        try {
            command(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println("Our knight is " + player.getName());
            printNavigation();
        }

        switch (string) {
            case "D": {
                System.out.println("Dealer doesn't work");
                command(input.readLine());
            }
            break;
            case "F": {
                commitFight();
            }
            break;
            case "E":
                System.exit(1);
                break;
            case "y":
                command("F");
                break;
            case "n": {
                printNavigation();
                command(input.readLine());
                break;
            }
            default : {
            }
        }
        command(input.readLine());
    }

    private static void printNavigation() {
        System.out.println("Where do you want go?");
        System.out.println("Press D to gotTo dealer");
        System.out.println("Press F to go nto the dark forest");
        System.out.println("Press H to go home");
    }

    private static void commitFight() {
        battle.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.printf("%s won! You have %d xp, %d gold, hp %d.%n", player.getName(), player.getXp(),
                        player.getGold(), player.getHp());
                System.out.println("Would you like to continue? (y/n)");
                try {
                    command(input.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static Person createMonster() {
        Random whoIsTheEnemy = new Random();
        if (whoIsTheEnemy.nextBoolean()) return new Goblin(
                "Goblin",
                80,
                8,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Skeleton",
                25,
                20,
                30,
                100,
                10
        );
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }
}
