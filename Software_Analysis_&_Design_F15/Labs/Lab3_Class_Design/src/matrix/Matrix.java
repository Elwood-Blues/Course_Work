package matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;

/**
 * An instance of this class represents a matrix of numbers 
 * as double values.
 * @author tcolburn
 */
public class Matrix {

    
    /**
     * Creates a matrix with the indicated numbers of rows and columns.
     * @param rows the number of rows in the matrix
     * @param columns the number of columns in the matrix
     */
    public Matrix(int rows, int columns) {
        numRows = rows;
        numColumns = columns;
        matrixA = new double[rows][columns];
    }

    /**
     * Getter for the number of rows in this matrix.
     * @return the number of rows in this matrix
     */
    public int getRows() {
        return this.numRows;  // You must provide
    }

    /**
     * Getter for the number of columns in this matrix.
     * @return the number of columns in this matrix
     */
    public int getColumns() {
        return this.numColumns;  // You must provide
    }
    
    /**
     * Gets the element at the indicated row and column in this matrix.
     * @param row the row position for the element.
     * It must be the case that 0 &le; row &lt; getRows().
     * @param column the column position for the element.
     * It must be the case that 0 &le; column &lt; getColumns().
     * @return the element at the indicated row and column
     * @throws MatrixException if row or column is out of bounds
     */
    public double get(int row, int column) {
        if (row > this.numRows - 1)
            throw new MatrixException(ROWBOUND);
        if (column > this.numColumns -1)
            throw new MatrixException(COLUMNBOUND);
        
        return matrixA[row][column];  // You must provide
    }
    
    /**
     * Sets the element at the indicated row and column in this matrix.
     * @param row the row position for the element.
     * It must be the case that 0 &le; row &lt; getRows().
     * @param column the column position for the element.
     * It must be the case that 0 &le; column &lt; getColumns().
     * @param element the value to set in the matrix
     * @throws MatrixException if row or column is out of bounds
     */
    public void set(int row, int column, double element) {
        if (row > this.numRows -1)
            throw new MatrixException(ROWBOUND);
        if (column > this.numColumns -1)
            throw new MatrixException(COLUMNBOUND);
        matrixA[row][column]= element;
    }
    
    /**
     * Tests for equality of this matrix with another.
     * Matrices are equal if they have the same dimensions and all
     * elements are equal by ==.
     * Note that since the parameter type for the other matrix is <b>Object</b>,
     * its reference must be cast to <b>Matrix</b>.
     * The parameter's type is <b>Object</b> so that this method overrides the
     * <b>equals</b> method in the <b>Object</b> superclass.
     * @param other the other matrix to be tested for equality with this matrix
     * @return <b>true</b> if the other matrix is equal to this matrix, <b>false</b> otherwise
     */
    public boolean equals(Object other) {
        Matrix otherMatrix = (Matrix) other;
        for(int i = 0; i < this.numRows; i++)
        {
            for(int j = 0; j < this.numColumns; j++)
            {
                if(matrixA[i][j] != otherMatrix.get(i,j))
                    return false;
            }
        }
        return true;  // You must provide
    }
    
    /**
     * Adds this matrix to another.
     * This matrix and the other matrix must have the same dimensions.
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not
     * have the same dimensions
     */
    public Matrix add(Matrix other) {
        if(other.getColumns() != this.numColumns || other.getRows() != this.numRows)
            throw new MatrixException("Matrix error: added matrices do not have same dimensions");
        
        Matrix newMatrix = new Matrix(this.numRows, this.numColumns);
        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < this.numColumns; j++)
            {
                newMatrix.set(i, j, matrixA[i][j] + other.get(i,j));
                
            }
        }
        return newMatrix;  // You must provide
    }
    
    /**
     * Multiples this matrix with another.
     * The number of columns in this matrix must match the number of rows in the other.
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not match 
     * the number of rows in the other
     */
    public Matrix multiply(Matrix other) {
        if (this.numColumns != other.getRows() )
            throw new MatrixException("Matrix error: multiplied matrices are not compatible");
        
        Matrix newMatrix = new Matrix(this.numRows, other.getColumns());
        
        for(int i = 0; i < newMatrix.getRows(); i++)
        {
            for(int j = 0; j < newMatrix.getColumns(); j++)
            {
                double result = 0.00000;
                for(int k = 0; k < this.getColumns(); k++)
                {
                result += (this.get(i,k) * other.get(k, j));
                newMatrix.set(i, j, result);
                }
                
            }
        }
        //System.out.println(newMatrix.toString() );
        return newMatrix;  // You must provide
    }
        
    
    /**
     * Creates a matrix from a data string.
     * Note that this method is written without knowing the representation
     * details of the matrix.
     * Only the constructor and public API method <b>set</b> are used.
     * @param string A string containing blank-separated matrix data 
     * which must parse as double values, or a NumberFormatException will be thrown.
     * Each row must be terminated by end-of-line character '\n'.
     * @param rows The number of rows in the matrix.  If the number of
     * rows in the data string is not the same, a MatrixException will be thrown.
     * @param columns The number of columns in the matrix.  If the number of
     * columns in the data string is not the same, a MatrixException will be thrown.
     * @return the created matrix.
     */
    public static Matrix toMatrix(String string, int rows, int columns) throws IOException {
        Matrix m = new Matrix(rows, columns);
        BufferedReader reader = new BufferedReader(new StringReader(string));
        String rowString = reader.readLine();
        int row = 0;
        while ( rowString != null ) {
            String[] values = rowString.trim().split("\\s+");
            for (int column = 0; column < values.length; column++) {
                m.set(row, column, Double.parseDouble(values[column]));
            }
            row++;
            rowString = reader.readLine();
        }
        return m;
    }
    
    /**
     * Creates a visual representation of this matrix as a string.
     * The opposite of <b>toMatrix</b>, this method can be used for debugging.
     * Note that this method is written without knowing the representation
     * details of the matrix.
     * Only the public API methods <b>getRows</b>, <b>getColumns</b>, and
     * <b>get</b> are used.
     * @return the string representation of this matrix.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int row = 0;
        while ( row < getRows() ) {
            int column = 0;
            while ( column < getColumns() ) {
                builder.append(get(row, column++));
                builder.append("  ");
            }
            builder.append("\n");
            row++;
        }
        return builder.toString();
    }
    
    
    // *****************************************************************
    // Your private fields and methods follow here
    private double[][] matrixA;
    private Integer numRows;
    private Integer numColumns;
    private final String ROWBOUND = "Matrix error: row index out of bounds";
    private final String COLUMNBOUND = "Matrix error: column index out of bounds";
    
    
}
