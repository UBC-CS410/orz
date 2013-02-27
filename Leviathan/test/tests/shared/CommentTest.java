/**
 * 
 */
package tests.shared;

import java.util.Date;
import junit.framework.TestCase;
import org.junit.Test;
import stuffplotter.shared.Comment;

/**
 * 
 *
 */
public class CommentTest extends TestCase
{

	@Test
	public void testCommentCtor()
	{
		String name = "John Doe";
		Date time = new Date();
		String content = "This is a comment";
		Long iD = null;
		Comment cm = new Comment(name, time, content);
		
		assertNotNull(cm);
		assertEquals(name, cm.getUsername());
		assertEquals(time, cm.getTime());
		assertEquals(content, cm.getContent());
		assertEquals(iD,cm.getId());
	}

}
