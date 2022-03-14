package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;

import java.util.Objects;

public class VisualParameters {
    private Colour leafColour;
    private Colour stemColour;
    private int averageSize;

    public VisualParameters() {
    }

    public VisualParameters(Colour leafColour, Colour stemColour, int averageSize) {
        this.leafColour = leafColour;
        this.stemColour = stemColour;
        this.averageSize = averageSize;
    }

    public Colour getLeafColour() {
        return leafColour;
    }

    public void setLeafColour(Colour leafColour) {
        this.leafColour = leafColour;
    }

    public Colour getStemColour() {
        return stemColour;
    }

    public void setStemColour(Colour stemColour) {
        this.stemColour = stemColour;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int averageSize) throws CustomException{
        if(averageSize < 0){
            throw new CustomException("Illegal argument");
        }
        this.averageSize = averageSize;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "leafColour=" + leafColour +
                ", stemColour=" + stemColour +
                ", averageSize=" + averageSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameters that = (VisualParameters) o;
        return averageSize == that.averageSize && leafColour == that.leafColour && stemColour == that.stemColour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leafColour, stemColour, averageSize);
    }
}
