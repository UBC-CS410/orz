/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import stuffplotter.shared.Comment;

/**
 * @author farez
 *
 */
public class CommentTest
{

	@Test
	public void testCommentCtor()
	{
		String name = "John Doe";
		Date time = new Date();
		String content = "This is a comment";
		
		Comment cm = new Comment(name, time, content);
		
		assertNotNull(cm);
		
	}

}
