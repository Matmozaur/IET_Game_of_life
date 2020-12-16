package agh.cs.lab8.utils_tests;

import agh.cs.lab8.utils.DNAUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DNAUtilsTest {

    List<Integer> dna1 = Arrays.asList(0,0,0,0,1,1,2,2,3,3,3,3,5,5,5,5,5,6,6,6,7,7,7,7,7,7,7,7,7,7,7,7);
    List<Integer> dna2 = Arrays.asList(0,0,0,0,1,1,2,2,3,3,3,3,4,5,5,5,5,5,6,6,6,7,7,7,7,7,7,7,7,7,7,7);
    List<Integer> dna3 = Arrays.asList(0,0,0,0,2,2,2,2,3,3,3,3,5,5,5,5,5,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7);
    List<Integer> dna4 = Arrays.asList(0,0,0,0,1,2,2,2,2,3,3,3,3,4,5,5,5,5,5,6,6,6,6,6,6,6,7,7,7,7,7,7);

    @Test
    public void mostCommonTests() {
        assertEquals("", Integer.valueOf(20), DNAUtils.mostCommon(dna1));
        assertEquals("", Integer.valueOf(21), DNAUtils.mostCommon(dna2));
        assertEquals("", Integer.valueOf(17), DNAUtils.mostCommon(dna3));
        assertEquals("", Integer.valueOf(19), DNAUtils.mostCommon(dna4));
    }

    @Test
    public void completeDNATests() {
        assertEquals("", dna2, DNAUtils.completeDNA(dna1));
        assertEquals("", dna4, DNAUtils.completeDNA(dna3));
    }
}
