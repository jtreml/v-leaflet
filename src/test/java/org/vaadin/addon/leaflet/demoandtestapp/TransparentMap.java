package org.vaadin.addon.leaflet.demoandtestapp;

import java.util.ArrayList;
import java.util.Arrays;

import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.LPolyline;
import org.vaadin.addon.leaflet.demoandtestapp.util.AbstractTest;
import org.vaadin.addon.leaflet.shared.Control;
import org.vaadin.addon.leaflet.shared.Point;

import com.vaadin.server.Page;
import com.vaadin.ui.Component;

public class TransparentMap extends AbstractTest {

	@Override
	public String getDescription() {
		return "Test custom map background color.";
	}

	private LMap leafletMap;

	@Override
	public Component getTestComponent() {
		
		Page.getCurrent().getStyles().add(".v-leaflet .leaflet-container {background: yellow;}");
		leafletMap = new LMap();
		leafletMap.setWidth("300px");
		leafletMap.setHeight("300px");
		leafletMap.setCenter(0, 0);
		leafletMap.setZoomLevel(2);

		LPolyline lPolyline = new LPolyline(new Point(-30, -30), new Point(0, 30), new Point(30, 0));
		lPolyline.setColor("red");
		lPolyline.setFill(false);
		
		leafletMap.addLayer(lPolyline);

		return leafletMap;
	}
}
