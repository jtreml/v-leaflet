package org.vaadin.addon.leaflet.client.vaadin;

import org.peimari.gleaflet.client.CircleMarker;
import org.peimari.gleaflet.client.CircleMarkerOptions;
import org.peimari.gleaflet.client.ClickListener;
import org.peimari.gleaflet.client.ILayer;
import org.peimari.gleaflet.client.LatLng;
import org.peimari.gleaflet.client.MouseEvent;
import org.vaadin.addon.leaflet.shared.Point;

import com.vaadin.shared.ui.Connect;

@Connect(org.vaadin.addon.leaflet.LCircleMarker.class)
public class LeafletCircleMarkerConnector extends
        AbstractLeafletVectorConnector<LeafletCircleState, CircleMarkerOptions> {

    private CircleMarker marker;

    @Override
    protected CircleMarkerOptions createOptions() {
   	 CircleMarkerOptions o = super.createOptions();
        LeafletCircleState s = getState();
        if (s.radius != null) {
            o.setRadius(s.radius);
        }
        return o;
    }

    @Override
    protected void update() {
        if (marker != null) {
            removeLayerFromParent();
            marker.removeClickListener();
        }
        LatLng latlng = LatLng.create(getState().point.getLat(),
                getState().point.getLon());
        CircleMarkerOptions options = createOptions();
        marker = CircleMarker.create(latlng, options);
        addToParent(marker);

        marker.addClickListener(new ClickListener() {
			@Override
			public void onClick(MouseEvent event) {
				LatLng latLng2 = event.getLatLng();
				Point p = new Point(latLng2.getLatitude(), latLng2.getLongitude());
				rpc.onClick(p);
			}
		});

    }

    @Override
    public ILayer getLayer() {
        return marker;
    }

}
