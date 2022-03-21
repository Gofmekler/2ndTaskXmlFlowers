package by.maiseichyk.task2.parser;

import by.maiseichyk.task2.entity.*;
import by.maiseichyk.task2.exception.CustomException;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;
import static org.testng.FileAssert.fail;

public class FlowersStaxBuilderTest {
    private String FILE_PATH_VALID = "src/test/java/resources/flowersTest.xml";
    private String SCHEMA_PATH = "src/test/java/resources/flowersTest.xsd";

    @org.testng.annotations.Test
    public void FlowerStaxBuilderTest() {
        Set<Flower> expectedSet = new HashSet<>();
        expectedSet.add(new NonPoisonousFlower("123123", "Rose", Soil.ACIDIC_SOIL, "India", YearMonth.parse("2022-02"), new VisualParameters(Colour.GREEN, Colour.DARK_GREEN, 70), new GrowingTips(20, 700, true), Multiplying.SEEDS, DangerLevel.NONE));

        FlowersStaxBuilder StaxBuilder;
        Set<Flower> actual = null;
        try {
            StaxBuilder = (FlowersStaxBuilder) FlowerBuilderFactory.getInstance().createFlowerBuilder(ParserType.STAX);
            StaxBuilder.buildFlowersSet(FILE_PATH_VALID, SCHEMA_PATH);
            actual = StaxBuilder.getFlowers();
        } catch (
                CustomException e) {
            fail(e.getMessage(), e);
        }
        assertEquals(expectedSet, actual);
    }

}