import edu.princeton.cs.algs4.StdRandom;

import java.util.Map;
import java.awt.Color;

public class Particle
{
    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                    ParticleFlavor.PLANT, PLANT_LIFESPAN,
                    ParticleFlavor.FIRE, FIRE_LIFESPAN);

    // Task 2: Create Particle Constructor
    //  - Create instance variables: flavor and lifespan

    public int lifespan;
    public ParticleFlavor flavor;

    public Particle(ParticleFlavor flavor)
    {
        this.flavor = flavor;
        this.lifespan = LIFESPANS.getOrDefault(flavor, -1);
    }

    public Color color()
    {
        int r, g, b;
        double ratio;
        Color particleColor;

        switch (this.flavor)
        {
            case ParticleFlavor.SAND:
                particleColor = Color.YELLOW;
                break;

            case ParticleFlavor.BARRIER:
                particleColor = Color.GRAY;
                break;

            case ParticleFlavor.WATER:
                particleColor = Color.BLUE;
                break;

            case ParticleFlavor.FOUNTAIN:
                particleColor = Color.CYAN;
                break;

            case ParticleFlavor.PLANT:
                ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
                g = 120 + (int) Math.round((255 - 120) * ratio);
                particleColor = new Color(0, g, 0);
                break;

            case ParticleFlavor.FIRE:
                ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
                r = (int) Math.round(255 * ratio);
                particleColor = new Color(r, 0, 0);
                break;

            case ParticleFlavor.FLOWER:
                ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
                r = 120 + (int) Math.round((255 - 120) * ratio);
                g = 70 + (int) Math.round((141 - 70) * ratio);
                b = 80 + (int) Math.round((161 - 80) * ratio);
                particleColor = new Color(r, g, b);
                break;

            default:
                particleColor = Color.BLACK;
                break;
        }

        return particleColor;
    }

    public void moveInto(Particle other)
    {
        other.flavor = this.flavor;
        other.lifespan = this.lifespan;

        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors)
    {
        Particle downNeighbor = neighbors.get(Direction.DOWN);
        if (downNeighbor.flavor == ParticleFlavor.EMPTY)
        {
            this.moveInto(downNeighbor);
        }
    }

    public void action(Map<Direction, Particle> neighbors)
    {
        if (this.flavor == ParticleFlavor.EMPTY)
            return;

        if (this.flavor != ParticleFlavor.BARRIER)
            this.fall(neighbors);

        if (this.flavor == ParticleFlavor.WATER)
            this.flow(neighbors);

        if (this.flavor == ParticleFlavor.PLANT || this.flavor == ParticleFlavor.FLOWER)
            this.grow(neighbors);

        if ( this.flavor == ParticleFlavor.FIRE )
            this.burn(neighbors);
    }

    public void flow(Map<Direction, Particle> neighbors)
    {
        switch (StdRandom.uniformInt(3))
        {
            case 0:
                return;

            case 1:
                Particle left = neighbors.get(Direction.LEFT);
                if (left.flavor == ParticleFlavor.EMPTY)
                {
                    this.moveInto(left);
                }
                break;

            case 2:
                Particle right = neighbors.get(Direction.RIGHT);
                if (right.flavor == ParticleFlavor.EMPTY)
                {
                    this.moveInto(right);
                }
                break;
        }
    }

    public void grow(Map<Direction, Particle> neighbors)
    {
        switch (StdRandom.uniformInt(10))
        {

            case 0:
                Particle up = neighbors.get(Direction.UP);
                if (up.flavor == ParticleFlavor.EMPTY)
                {
                    up.flavor = this.flavor;
                    up.lifespan = LIFESPANS.get(this.flavor);
                }
                break;

            case 1:
                Particle left = neighbors.get(Direction.LEFT);
                if (left.flavor == ParticleFlavor.EMPTY)
                {
                    left.flavor = this.flavor;
                    left.lifespan = LIFESPANS.get(this.flavor);
                }
                break;

            case 2:
                Particle right = neighbors.get(Direction.RIGHT);
                if (right.flavor == ParticleFlavor.EMPTY)
                {
                    right.flavor = this.flavor;
                    right.lifespan = LIFESPANS.get(this.flavor);
                }
                break;

            default:
                return;
        }
    }

    public void decrementLifespan()
    {
        if (this.lifespan > 0)
        {
            this.lifespan--;
        }
        else if (this.lifespan == 0)
        {
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }

    public void burn(Map<Direction, Particle> neighbors)
    {
        Particle up, down, left, right;

        switch (StdRandom.uniformInt(10))
        {
            case 0:
            case 1:
            case 2:
            case 3:
                for (Map.Entry<Direction, Particle> entry : neighbors.entrySet())
                {
                    Particle np = entry.getValue();
                    if ((np.flavor == ParticleFlavor.PLANT) || (np.flavor == ParticleFlavor.FLOWER))
                    {
                        np.flavor = ParticleFlavor.FIRE;
                        np.lifespan = LIFESPANS.get(ParticleFlavor.FIRE);
                    }
                }
                /*
                up = neighbors.get(Direction.UP);
                down = neighbors.get(Direction.DOWN);
                left = neighbors.get(Direction.LEFT);
                right = neighbors.get(Direction.RIGHT);
                 */
                break;
            default:
                return;
        }
    }
}
