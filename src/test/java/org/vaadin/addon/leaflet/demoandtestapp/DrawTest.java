package org.vaadin.addon.leaflet.demoandtestapp;

import java.util.Arrays;

import org.vaadin.addon.leaflet.LFeatureGroup;
import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.LPolyline;
import org.vaadin.addon.leaflet.LTileLayer;
import org.vaadin.addon.leaflet.demoandtestapp.util.AbstractTest;
import org.vaadin.addon.leaflet.draw.LDraw;
import org.vaadin.addon.leaflet.draw.LDraw.FeatureDrawnEvent;
import org.vaadin.addon.leaflet.draw.LDraw.FeatureDrawnListener;
import org.vaadin.addon.leaflet.draw.LDraw.FeatureModifiedEvent;
import org.vaadin.addon.leaflet.draw.LDraw.FeatureModifiedListener;
import org.vaadin.addon.leaflet.shared.Point;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

public class DrawTest extends AbstractTest {

	@Override
	public String getDescription() {
		return "Test leaflet draw";
	}

	private LMap leafletMap;
	private LDraw draw = new LDraw();

	@Override
	public Component getTestComponent() {
		leafletMap = new LMap();
		leafletMap.setCenter(0, 0);
		leafletMap.setZoomLevel(0);
		leafletMap.addLayer(new LTileLayer("http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"));

		final LFeatureGroup group = new LFeatureGroup();

		group.addComponent(new LPolyline(new Point(-60, -30),
				new Point(20, 10), new Point(40, 150)));

		leafletMap.addLayer(group);
		
		draw.setEditableFeatureGroup(group);
		
		leafletMap.addControl(draw);
		
		draw.addFeatureDrawnListener(new FeatureDrawnListener() {
			
			@Override
			public void featureDrawn(FeatureDrawnEvent event) {
				group.addComponent(event.getDrawnFeature());
				Notification.show("Drawed " + event.getDrawnFeature().getClass().getSimpleName());
			}
		});
		
		draw.addFeatureModifiedListener(new FeatureModifiedListener() {
			
			@Override
			public void featureModified(FeatureModifiedEvent event) {
				Notification.show("Modified " + event.getModifiedFeature().getClass().getSimpleName());
				if (event.getModifiedFeature() instanceof LPolyline) {
					LPolyline pl = (LPolyline) event.getModifiedFeature();
					Point[] points = pl.getPoints();
					System.out.println(Arrays.toString(points));
				}
			}
		});
		return leafletMap;
	}

	@Override
	protected void setup() {
		super.setup();

	}
}