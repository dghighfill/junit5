package com.jug.junit5.sm.ex3;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith( JUnitPlatform.class )
@SelectPackages("com.jug.junit5.sm.ex3")
@IncludeTags("gargantuan")
public class GargantuanTests {}
