package task.util;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import task.util.StringUtil;

public class StringUtilTest {

	@Test
	public void notAddEmptyString() {
		System.out.println(StringUtil.convertHourAndMinute(-1));
		System.out.println(StringUtil.convertHourAndMinute(5));
		System.out.println(StringUtil.convertHourAndMinute(69));
		System.out.println(StringUtil.convertHourAndMinute(179));
	}

}
