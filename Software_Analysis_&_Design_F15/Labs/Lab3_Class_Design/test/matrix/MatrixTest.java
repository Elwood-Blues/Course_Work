package matrix;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A JUnit test class for Matrix.
 * @author tcolburn
 */
public class MatrixTest {
    
    /**
     * Creates a matrix, sets its elements, then gets the elements and
     * checks for correctness.
     */
    @Test
    public void testSetAndGet1() {
        Matrix m = new Matrix(2, 2);  // Creates the matrix:
        m.set(0, 0, 3);               //   +------+
        m.set(0, 1, 2);               //   | 3  2 |
        m.set(1, 0, 5);               //   | 5  4 |
        m.set(1, 1, 4);               //   +------+
        assertTrue(m.get(0, 0) == 3);
        assertTrue(m.get(0, 1) == 2);
        assertTrue(m.get(1, 0) == 5);
        assertTrue(m.get(1, 1) == 4);
    }

    /**
     * Like testSetAndGet1, but uses Matrix.toMatrix and Matrix.toString to
     * test set and get.
     */
    @Test
    public void testSetAndGet2() throws IOException {
        Matrix m = Matrix.toMatrix("3 2 5\n" +                // The toMatrix
                                   "2 4 3\n" +                // method calls set
                                   "6 5 1\n" +
                                   "1 6 4\n", 4, 3);
        assertTrue(m.toString().equals("3.0  2.0  5.0  \n" +  // The toString
                                       "2.0  4.0  3.0  \n" +  // method calls get
                                       "6.0  5.0  1.0  \n" +
                                       "1.0  6.0  4.0  \n"));
    }

    /**
     * Tests the handling of out-of-bounds index errors in Matrix accesses.
     * Note that the assertions indicate the exact wording of the exception messages.
     */
    @Test
    public void testBounds() {
        Matrix m = new Matrix(4, 3);  // row indices: 0..3  column indices: 0..2
        try {
            m.set(4, 2, 10.0);        // row 4 is out of bounds
        }
        catch(MatrixException ex) {
            System.out.println(ex.getMessage());
            assertTrue(ex.getMessage().equals("Matrix error: row index out of bounds"));
        }
        try {
            m.set(3, 3, 10.0);        // column 3 is out of bounds
        }
        catch(MatrixException ex) {
            System.out.println(ex.getMessage());
            assertTrue(ex.getMessage().equals("Matrix error: column index out of bounds"));
        }
        try {
            m.get(4, 2);              // row 4 is out of bounds
        }
        catch(MatrixException ex) {
            System.out.println(ex.getMessage());
            assertTrue(ex.getMessage().equals("Matrix error: row index out of bounds"));
        }
        try {
            m.get(3, 3);              // column 3 is out of bounds
        }
        catch(MatrixException ex) {
            System.out.println(ex.getMessage());
            assertTrue(ex.getMessage().equals("Matrix error: column index out of bounds"));
        }
    }

    /**
     * Tests equality testing for matrices.
     * Note that testing matrices of different sizes for equality does not
     * cause an exception, just returns false.
     */
    @Test
    public void testEquals() throws IOException {
        Matrix m1 = Matrix.toMatrix("3 2 5\n" +         
                                    "2 4 3\n" +         
                                    "6 5 1\n" +
                                    "1 6 4\n", 4, 3);
        Matrix m2 = Matrix.toMatrix("3 2 5\n" +        // m2 is same as m1 
                                    "2 4 3\n" +         
                                    "6 5 1\n" +
                                    "1 6 4\n", 4, 3);
        Matrix m3 = Matrix.toMatrix("3 2 5\n" +        // m3 has one element  
                                    "2 4 3\n" +        // different than m1
                                    "6 6 1\n" +
                                    "1 6 4\n", 4, 3);
        Matrix m4 = Matrix.toMatrix("2 3\n" +          // m4 is different size
                                    "4 2\n" +         
                                    "3 4\n", 3, 2);
        assertTrue(m1.equals(m2));
        assertFalse(m1.equals(m3));
        assertFalse(m1.equals(m4));
    }

    /**
     * Tests matrix addition.
     * Note that a MatrixException is thrown if the operand matrices are not
     * the same size.
     * The assertion indicates the exact wording of the exception message.
     */
    @Test
    public void testAdd() throws IOException {
        Matrix m1 = Matrix.toMatrix(" 3  2  5\n" +         
                                    " 2  4  3\n" +         
                                    " 6  5  1\n" +
                                    " 1  6  4\n", 4, 3);
        Matrix m2 = Matrix.toMatrix(" 6  4 10\n" +        // m2 is m1 + m1
                                    " 4  8  6\n" +         
                                    "12 10  2\n" +
                                    " 2 12  8\n", 4, 3);
        Matrix m4 = Matrix.toMatrix(" 2  3\n" +          // m4 is different size
                                    " 4  2\n" +         
                                    " 3  4\n", 3, 2);
        try {
            m1.add(m4);
        }
        catch(MatrixException ex) {
            System.out.println(ex.getMessage());
            assertTrue(ex.getMessage().equals("Matrix error: added matrices do not have same dimensions"));
        }
        assertTrue(m1.add(m1).equals(m2));
        assertFalse(m1.add(m1).equals(m1));
    }

    /**
     * Tests matrix multiplication.
     * Note that a MatrixException is thrown if the operand matrices are not
     * compatible.
     * The assertion indicates the exact wording of the exception message.
     */
    @Test
    public void testMultiply() throws IOException {
        Matrix m1 = Matrix.toMatrix("  3  2  5\n" +         
                                    "  2  4  3\n" +         
                                    "  6  5  1\n" +
                                    "  1  6  4\n", 4, 3);
        Matrix m4 = Matrix.toMatrix("  2  3\n" +          
                                    "  4  2\n" +         
                                    "  3  4\n", 3, 2);
        Matrix m5 = Matrix.toMatrix(" 29 33\n" +         
                                    " 29 26\n" +         
                                    " 35 32\n" +
                                    " 38 31\n", 4, 2);
        try {
            m1.multiply(m5);  // try to multiply a 4x3 matrix by a 4x2 matrix
        }
        catch(MatrixException ex) {
            System.out.println(ex.getMessage());
            assertTrue(ex.getMessage().equals("Matrix error: multiplied matrices are not compatible"));
        }
        Matrix product = m1.multiply(m4);  // multiply a 4x3 matrix by a 3x2 matrix
        assertTrue(product.getRows() == 4);
        assertTrue(product.getColumns() == 2);
        //System.out.println(product.toString());
        assertTrue(product.equals(m5));
    }
    
}
