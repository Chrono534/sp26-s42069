import edu.princeton.cs.algs4.StdDraw;

import java.util.HashMap;
import java.util.Map;


public class ParticleSimulator
{
    public static final Map<Character, ParticleFlavor> LETTER_TO_PARTICLE = Map.of(
            's', ParticleFlavor.SAND,
            'b', ParticleFlavor.BARRIER,
            'w', ParticleFlavor.WATER,
            'p', ParticleFlavor.PLANT,
            'f', ParticleFlavor.FIRE,
            '.', ParticleFlavor.EMPTY,
            'n', ParticleFlavor.FOUNTAIN,
            'r', ParticleFlavor.FLOWER
    );

    public int width;
    public int height;
    public Particle[][] particles;

    public ParticleSimulator(int w, int h)
    {
        this.width = w;
        this.height = h;
        this.particles = new Particle[this.width][this.height];

        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {
                Particle p = new Particle(ParticleFlavor.EMPTY);
                this.particles[i][j] = p;
            }
        }
    }

    public void drawParticles()
    {
        for (int x = 0; x < width; x += 1)
        {
            for (int y = 0; y < height; y += 1)
            {
                StdDraw.setPenColor(particles[x][y].color());
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
    }

    public boolean validIndex(int x, int y)
    {
        boolean validIdx = false;

        if (0 <= x && x < this.width && 0 <= y && y < this.height)
        {
            validIdx = true;
        }

        return validIdx;
    }

    public Map<Direction, Particle> getNeighbors(int x, int y)
    {
        Particle north, south, east, west;
        Map<Direction, Particle> neighborMap = new HashMap<>();

        north = validIndex(x, y + 1) ? this.particles[x][y + 1] : new Particle(ParticleFlavor.BARRIER);
        south = validIndex(x, y - 1) ? this.particles[x][y - 1] : new Particle(ParticleFlavor.BARRIER);
        east = validIndex(x + 1, y) ? this.particles[x + 1][y] : new Particle(ParticleFlavor.BARRIER);
        west = validIndex(x - 1, y) ? this.particles[x - 1][y] : new Particle(ParticleFlavor.BARRIER);

        neighborMap.put(Direction.UP, north);
        neighborMap.put(Direction.DOWN, south);
        neighborMap.put(Direction.RIGHT, east);
        neighborMap.put(Direction.LEFT, west);

        return neighborMap;
    }

    public void tick()
    {
        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {

               Particle p = this.particles[i][j];
               p.action( this.getNeighbors(i, j) );
               p.decrementLifespan();
            }
        }
    }
    @Override
    public String toString() {
        // 1. Build a reverse map to look up characters by Flavor
        Map<ParticleFlavor, Character> flavorToChar = new HashMap<>();
        for (Map.Entry<Character, ParticleFlavor> entry : LETTER_TO_PARTICLE.entrySet()) {
            flavorToChar.put(entry.getValue(), entry.getKey());
        }

        StringBuilder sb = new StringBuilder();

        // Have to iterate from the top so that
        // the top particles are shown first.
        for (int y = height - 1; y >= 0; y -= 1) {
            for (int x = 0; x < width; x += 1) {
                Particle p = particles[x][y];
                sb.append(flavorToChar.get(p.flavor));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    static void main()
    {
        ParticleSimulator particleSimulator = new ParticleSimulator(250, 250);
        StdDraw.setXscale(0, particleSimulator.width);
        StdDraw.setYscale(0, particleSimulator.height);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true)
        {

            if (StdDraw.hasNextKeyTyped())
            {
                ParticleFlavor tempParticleFlavor = LETTER_TO_PARTICLE.get(StdDraw.nextKeyTyped());
                if (tempParticleFlavor != null)
                {
                    nextParticleFlavor = tempParticleFlavor;
                }
            }

            if (StdDraw.isMousePressed())
            {
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();
                if (particleSimulator.validIndex(x, y))
                {
                    particleSimulator.particles[x][y] = new Particle(nextParticleFlavor);
                }
            }

            particleSimulator.tick();
            particleSimulator.drawParticles();
            StdDraw.show();
            StdDraw.pause(5);
        }
    }

}