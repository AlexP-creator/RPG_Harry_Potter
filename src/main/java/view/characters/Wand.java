package view.characters;

import java.util.Random;

public class Wand {
    private static final String[] CORES = {"crin de licorne", "ventricule de dragon","plume de Phénix","corne de Basilic"};
    private static final String[] SIZES = {"petite", "moyenne", "grande"};
    private static final Random random = new Random();

    private String core;
    private String size;

    public Wand(String size, String core) {
        this.size = size;
        this.core = core;
    }

    public static Wand generateRandomWand() {
        String size = SIZES[random.nextInt(SIZES.length)];
        String core = CORES[random.nextInt(CORES.length)];
        return new Wand(size, core);
    }

    public String getCore() {
        return core;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format(" de taille %s et en %s", size, core);
    }
}
