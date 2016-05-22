package tests.model.entity;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

import src.model.entity.Circle;
import src.utill.Vector2D;

public class CircleTests
{
	@Test
	public void byDeafultCircleShouldHaveZeros() 
	{
		Circle circle = new Circle();
		
		assertEquals(0, circle.getRadius(), Vector2D.EPSILON);
		assertEquals(0, circle.getPosition().x, Vector2D.EPSILON);
		assertEquals(0, circle.getPosition().y, Vector2D.EPSILON);
	}
	
	@Test
	public void constructedCircleShouldHaveProperParameters() 
	{
		Circle circle = new Circle(new Vector2D(5, 6), 10);
		
		assertEquals(10, circle.getRadius(), Vector2D.EPSILON);
		assertEquals(5, circle.getPosition().x, Vector2D.EPSILON);
		assertEquals(6, circle.getPosition().y, Vector2D.EPSILON);
	}
	
	@Test
	public void circleMadeFromOtherCircleShouldHaveSameParameters()
	{
		Circle circle = new Circle(new Vector2D(4, 5), 11);
		Circle other  = new Circle(circle);
		
		assertEquals(circle.getPosition(), other.getPosition());
		assertEquals(circle.getRadius(), other.getRadius(), Vector2D.EPSILON);
	}
	
	@Test (expected = InvalidParameterException.class)
	public void circleShouldNotHaveNegativeRadius()
	{
		Circle circle = new Circle(new Vector2D(2, 4), -4);
	}
	
	@Test
	public void circlesWithSameParametersShouldBeConsideredEqual()
	{
		Circle circle = new Circle(new Vector2D(3, 3), 3);
		Circle other  = new Circle(new Vector2D(3, 3), 3);
		
		assertTrue(circle.equals(other));
		assertTrue(other.equals(circle));
	}
	
	@Test
	public void pushingCircleShouldAddToItsPosition()
	{
		Circle circle = new Circle(new Vector2D(3, 4), 5);
		circle.push(new Vector2D(4, 4));
		assertEquals(7 ,circle.getPosition().x, Vector2D.EPSILON);
		assertEquals(8 ,circle.getPosition().y, Vector2D.EPSILON);
	}
	
	@Test
	public void massIsActuallyRadiusSquared()
	{
		Circle circle = new Circle(new Vector2D(3, 5), 6);
		
		assertEquals(36, circle.getMass(), Vector2D.EPSILON);
	}
	
	@Test
	public void circlesBoundingBoxesCross()
	{
		Circle circle = new Circle(new Vector2D(3, 3), 4);
		Circle other  = new Circle(new Vector2D(8, 4), 2);
		
		assertTrue(circle.boundingBoxesCross(other));
		assertTrue(other.boundingBoxesCross(circle));
	}
	
	@Test
	public void circlesBoundingBoxesDontCross()
	{
		Circle circle = new Circle(new Vector2D(3, 3), 4);
		Circle other  = new Circle(new Vector2D(10, 4), 2);
		
		assertFalse(circle.boundingBoxesCross(other));
		assertFalse(other.boundingBoxesCross(circle));
	}
	
	@Test
	public void circlesFarAwayShouldNotCross()
	{
		Circle circle = new Circle(new Vector2D(4, 4), 4);
		Circle other  = new Circle(new Vector2D(40, 40), 4);
		
		assertFalse(circle.crosses(other ));
		assertFalse(other. crosses(circle));
	}
	
	@Test
	public void circlesWhoseAABBsAreOverlapingButStillNotCrossing()
	{
		Circle circle = new Circle(new Vector2D(0, 0), 4);
		Circle other  = new Circle(new Vector2D(7, 7), 4);
		
		assertFalse(circle.crosses(other ));
		assertFalse(other. crosses(circle));
	}
	
	@Test
	public void circlesThatAreFinallyCrossing()
	{
		Circle circle = new Circle(new Vector2D(0, 0), 4);
		Circle other  = new Circle(new Vector2D(5, 5), 4);
		
		assertTrue(circle.crosses(other ));
		assertTrue(other. crosses(circle));
	}
	
	@Test
	public void circlesThatAreBarelyTouchingShouldNotCross()
	{
		Circle circle = new Circle(new Vector2D(0, 0), 4);
		Circle other  = new Circle(new Vector2D(0, 5), 1);
		
		assertFalse(circle.crosses(other ));
		assertFalse(other. crosses(circle));
	}
	
	@Test
	public void pointFarAwayShouldNotBeInTheCircle()
	{
		Circle circle = new Circle(new Vector2D(3, 3), 5);
		
		assertFalse(circle.contains(new Vector2D(50, 50)));
	}
	
	@Test
	public void pointInAABBButStillNotInTheCircle()
	{
		Circle circle = new Circle(new Vector2D(3, 3), 5);
		
		assertFalse(circle.contains(new Vector2D(7, 7)));
	}
	
	@Test
	public void pointFinallyInTheCircle()
	{
		Circle circle = new Circle(new Vector2D(3, 3), 5);
		
		assertTrue(circle.contains(new Vector2D(6, 6)));
	}
	
	@Test
	public void pointOnTheCircumferenceShouldNotBeContained()
	{
		Circle circle = new Circle(new Vector2D(3, 3), 5);
		
		assertFalse(circle.contains(new Vector2D(3, 8)));
	}
	
	@Test
	public void circleOutsideViewingBoxShouldNotBeVisible()
	{
		Circle circle = new Circle(new Vector2D(40, 40), 10);
		
		assertFalse(circle.isVisibleInside(new Vector2D(0, 0), new Vector2D(10, 10)));
	}
	
	@Test
	public void circleInsideViewingBoxShouldBeVisible()
	{
		Circle circle = new Circle(new Vector2D(0, 0), 1);
		
		assertTrue(circle.isVisibleInside(new Vector2D(-10, -10), new Vector2D(20, 20)));
	}
	
	@Test
	public void circlePartiallyInsideViewingBoxShouldBeVisible()
	{
		Circle circle = new Circle(new Vector2D(0, 3), 4);
		
		assertTrue(circle.isVisibleInside(new Vector2D(0, 0), new Vector2D(10, 10)));
	}
	
	@Test
	public void circleCoveringWholeBoxShouldBeVisible()
	{
		Circle circle = new Circle(new Vector2D(5, 5), 100);
		
		assertTrue(circle.isVisibleInside(new Vector2D(0, 0), new Vector2D(1, 1)));
	}
}