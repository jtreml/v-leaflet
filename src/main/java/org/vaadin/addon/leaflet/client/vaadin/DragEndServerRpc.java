package org.vaadin.addon.leaflet.client.vaadin;

import org.vaadin.addon.leaflet.shared.Point;

import com.vaadin.shared.communication.ServerRpc;

public interface DragEndServerRpc extends ServerRpc {
	
	void dragEnd(Point point);
	
}
