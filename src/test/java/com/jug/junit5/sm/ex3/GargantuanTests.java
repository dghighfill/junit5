package com.jug.junit5.sm.ex3;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

// Doesn't run as expected.  Reported bug in Eclipse
// https://github.com/junit-team/junit5/issues/569
@RunWith( JUnitPlatform.class )
@SelectPackages("com.jug.junit5.sm.ex3")
@IncludeTags("gargantuan")
public class GargantuanTests {}
