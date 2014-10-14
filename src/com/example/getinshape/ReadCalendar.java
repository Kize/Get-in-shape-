package com.example.getinshape;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CalendarContract.Events;

public class ReadCalendar {

	public String getEvents(ContentResolver... params) {
		
		
	ContentResolver contentResolver = params[0];
	String result = "";
    Cursor eventCursor = contentResolver.query(Events.CONTENT_URI,
            new String[]  { Events._ID, Events.TITLE, Events.DTSTART, Events.DTEND },
            null,null, null);
    
    if(eventCursor.getCount()>0)
    {
        eventCursor.moveToFirst();
        
        do
        {
            final String title = eventCursor.getString(1);
            
            final long start = eventCursor.getLong(2);
            SimpleDateFormat df = new SimpleDateFormat("hh:ss MM/dd/yyyy");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            String sstart = df.format(start);
            
            final long end = eventCursor.getLong(3);
            String send = df.format(end);
            
            result += ("Title: " + title + " Start: " + sstart + " End: " + send ) + "\n";
        }while (eventCursor.moveToNext());
        
    }
	return result;
		
	}
}
	