import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int heroesHealth[] = {260, 270, 240, 100};
    public static int heroesDamage[] = { 35, 35, 30, 0};
    public static String heroesType[] = {"Warrior", "Idol", "Mage", "Medic"};

    public static int round = 0;
    public static String bossBarrier;
    public static boolean medicAlive = true;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            round++;
            round();
        }
    }

    public static void bossTypeBarrier() {

        if (bossHealth > 0) {
            Random random = new Random();
            int index = random.nextInt(heroesDamage.length);
            bossBarrier = heroesType[index];
            System.out.println("Boss barrier for - " + bossBarrier);
            bossHealth = bossHealth + heroesDamage[index];
        }

    }

    public static void printStatistics() {
        System.out.println("Statistics");
        System.out.println("Boss health: " + "SkeletonKing " + bossHealth + " Boss damage:" + bossDamage);
        for (int i = 0; i < heroesDamage.length; i++) {
            System.out.println("Hero health: " + heroesType[i] + " " + heroesHealth[i] + " Hero damage " + heroesDamage[i]);
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            heroesHealth[i] = heroesHealth[i] - bossDamage;
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }

        }
    }

    public static void heroHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            bossHealth = bossHealth - heroesDamage[i];
        }
        if (bossHealth < 0) {
            bossHealth = 0;
        }
    }

    public static void round() {
        System.out.println("ROUND - " + round);
        bossHits();
        heroHits();
        medicSkill();
        bossTypeBarrier();
        printStatistics();
    }

    public static boolean isGameOver() {
        boolean allHeroesDead = true;
        if (bossHealth <= 0) {
            System.out.println("Congratulations you won");
            return true;
        }
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
            if (allHeroesDead) {
                System.out.println("Gameover you lost");
                break;
            }
        }
        return allHeroesDead;
    }
    public static void medicSkill() {
        int medicHeal = 70;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesType[i].equals("Medic") && heroesHealth[i] > 0) {
                medicAlive = true;
                break;
            }
        }
        for (int i = 0; i <heroesDamage.length ; i++) {
            if (!heroesType[i].equals("Medic")) {
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && medicAlive) {
                    heroesHealth[i] = heroesHealth[i] + medicHeal;
                    System.out.println("Medic heals - " + heroesType[i]);
                    break;
                }
            }
        }
    }
}