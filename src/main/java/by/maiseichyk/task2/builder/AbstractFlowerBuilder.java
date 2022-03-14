package by.maiseichyk.task2.builder;

import by.maiseichyk.task2.entity.Flower;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowerBuilder {
    protected Set<Flower> flowers;

    public AbstractFlowerBuilder(){
        flowers = new HashSet<>();
    }

    public AbstractFlowerBuilder(Set<Flower> flowers){
        this.flowers = flowers;
    }

    public Set<Flower> getFlowers(){
        return flowers;
    }

    public abstract void buildFlowersSet(String filename);
}
