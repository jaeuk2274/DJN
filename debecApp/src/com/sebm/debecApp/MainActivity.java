/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.sebm.debecApp;

import java.io.File;

import android.content.Context;
import android.os.Bundle;

import org.apache.cordova.*;

public class MainActivity extends CordovaActivity
{
	public void clearApplicationCache(File file) { //캐시 삭제 메서드
		 
	    File dir = null;
	 
	    if (file == null) {
	        dir = getCacheDir();
	    } else {
	        dir = file;
	    }
	    
	    if (dir == null) {
	        return;
	    }
	 	 
	    File[] children = dir.listFiles();
	    try {
	        for (int i = 0; i < children.length; i++)
	            if (children[i].isDirectory())
	                clearApplicationCache(children[i]);
	            else
	                children[i].delete();
	    } 
	    catch (Exception e) {
	    }
	};
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		clearApplicationCache(getCacheDir()); //종료시 캐시파일을 삭제한다.
	} 
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
      
        loadUrl(launchUrl);
    }
}
