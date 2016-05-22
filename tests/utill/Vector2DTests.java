package tests.utill;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

import src.utill.Vector2D;

public class Vector2DTests
{	
	@Test
	public void emptyConstructorShouldFillVectorWithZeros()
	{
		Vector2D vector = new Vector2D();
		
		assertEquals(0, vector.x, Vector2D.EPSILON);
		assertEquals(0, vector.y, Vector2D.EPSILON);
	}
	
	@Test
	public void constructorShouldGiveProperParametersToVector()
	{
		Vector2D vector = new Vector2D(3, 4);
		
		assertEquals(3, vector.x, Vector2D.EPSILON);
		assertEquals(4, vector.y, Vector2D.EPSILON);
	}
	
	@Test
	public void vectorMadeFromOtherVectorShouldHaveEqualFields()
	{
		Vector2D vector = new Vector2D(3, 4);
		Vector2D other  = new Vector2D(vector);
		
		assertEquals(vector.x, other.x, Vector2D.EPSILON);
		assertEquals(vector.y, other.y, Vector2D.EPSILON);
	}
	
	@Test
	public void vectorMadeFromPointDoubleShouldHaveEqualFields()
	{
		Point2D.Double point = new Point2D.Double(3, 4);
		Vector2D vector = new Vector2D(point);
		
		assertEquals(point.x, vector.x, Vector2D.EPSILON);
		assertEquals(point.y, vector.y, Vector2D.EPSILON);
	}
	
	@Test
	public void vectorsWithEqualFieldsShouldBeEqual()
	{
		Vector2D vector = new Vector2D(3, 5);
		Vector2D other  = new Vector2D(3, 5);
		
		assertTrue(vector.equals(other));
		assertTrue(other.equals(vector));
	}
	
	@Test
	public void vectorsWithDifferentFieldsShouldNotBeEqual()
	{
		Vector2D vector = new Vector2D(3, 3);
		Vector2D other  = new Vector2D(3, 5);
		
		assertFalse(vector.equals(other));
		assertFalse(other.equals(vector));
	}
	
	@Test
	public void additionOfTwoVectorsShouldAddItsFields()
	{
		Vector2D vector = new Vector2D(2, 1).add(new Vector2D(1, 2));
		
		assertEquals(new Vector2D(3, 3), vector); 
	}
	
	@Test
	public void subtractionOfTwoVectorsShouldSubtractItsFields()
	{
		Vector2D vector = new Vector2D(2, 1).subtract(new Vector2D(1, 2));
		
		assertEquals(new Vector2D(1, -1), vector); 
	}
	
	@Test
	public void multiplicationByScalarShouldMultiplyVectorsFields()
	{
		Vector2D vector = new Vector2D(3, 3).multiply(3);
		
		assertEquals(new Vector2D(9, 9), vector);
	}
	
	@Test
	public void divisionByScalarShouldDivideVectorsFields()
	{
		Vector2D vector = new Vector2D(3, 3).divide(3);
		
		assertEquals(new Vector2D(1, 1), vector);
	}
	
	@Test
	public void negationOfAVectorShouldNegateItsFields()
	{
		Vector2D vector = new Vector2D(-1, 1).negate();
		
		assertEquals(new Vector2D(1, -1), vector);
	}
	
	@Test
	public void distanceToItselfShouldBeZero()
	{
		Vector2D vector = new Vector2D();
		
		assertEquals(0, vector.distance(vector), Vector2D.EPSILON);
		assertEquals(0, vector.distance(vector), Vector2D.EPSILON);
		assertEquals(0, Vector2D.distance(vector, vector), Vector2D.EPSILON);
	}
	
	@Test
	public void distanceBetweenTwoVectorsShouldBeCalculatedCorrectly()
	{
		Vector2D vector = new Vector2D(0, 0);
		Vector2D other  = new Vector2D(3, 4);
		
		assertEquals(5, vector.distance(other), Vector2D.EPSILON);
		assertEquals(5, other.distance(vector), Vector2D.EPSILON);
		assertEquals(5, Vector2D.distance(vector, other), Vector2D.EPSILON);
		assertEquals(5, Vector2D.distance(other, vector), Vector2D.EPSILON);
	}
	
	@Test
	public void distanceSquaredToItselfShouldBeZero()
	{
		Vector2D vector = new Vector2D();
		
		assertEquals(0, vector.distanceSq(vector), Vector2D.EPSILON);
		assertEquals(0, vector.distanceSq(vector), Vector2D.EPSILON);
		assertEquals(0, Vector2D.distanceSq(vector, vector), Vector2D.EPSILON);
	}
	
	@Test
	public void distanceSquareBetweenTwoVectorsShouldBeCalculatedCorrectly()
	{
		Vector2D vector = new Vector2D(0, 0);
		Vector2D other  = new Vector2D(3, 4);
		
		assertEquals(25, vector.distanceSq(other), Vector2D.EPSILON);
		assertEquals(25, other.distanceSq(vector), Vector2D.EPSILON);
		assertEquals(25, Vector2D.distanceSq(vector, other), Vector2D.EPSILON);
		assertEquals(25, Vector2D.distanceSq(other, vector), Vector2D.EPSILON);
	}
	
	@Test
	public void dotProductToItselfShouldBeSumOfItsFieldsSquared()
	{
		Vector2D vector = new Vector2D(-1, 2);
		
		assertEquals(5, vector.dotProduct(vector), Vector2D.EPSILON);
	}
	
	@Test
	public void dotProductOfTwoVectorsShouldBeCalculatedCorrectly()
	{
		Vector2D vector = new Vector2D(-1, 2);
		Vector2D other  = new Vector2D(2, 3);
		
		assertEquals(4, vector.dotProduct(other), Vector2D.EPSILON);
	}
}