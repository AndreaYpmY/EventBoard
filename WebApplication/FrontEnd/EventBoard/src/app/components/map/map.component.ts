import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MAPBOX_KEY} from "../../../constants";
import * as mapboxgl from 'mapbox-gl';
import {MapboxService} from "../../services/mapbox.service";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  map!: mapboxgl.Map
  @Input() coordinates: Array<number> = []
  @Input() draggable: boolean
  @Output() sendCoordinatesToParent = new EventEmitter<number[]>()

  constructor(private mapboxService: MapboxService) {
  }

  ngOnInit(): void {
    (mapboxgl as any).accessToken = MAPBOX_KEY
    this.map = new mapboxgl.Map({
      container: 'mapbox',
      style: 'mapbox://styles/mapbox/streets-v11',
      center: [this.coordinates[0], this.coordinates[1]], // Starting [Longitude, Latitude]
      zoom: 17
    });

    this.createMarker(this.coordinates[0], this.coordinates[1], this.draggable)
  }

  createMarker(longitude: number, latitude: number, draggable: boolean) {
    const marker = new mapboxgl.Marker({
      draggable: draggable
    })
      .setLngLat([longitude, latitude])
      .addTo(this.map);

    let coordinatesToSend: number[]
    marker.on('dragend', () => {
      console.log(marker.getLngLat()),
      coordinatesToSend = [marker.getLngLat().lng, marker.getLngLat().lat],
      this.sendCoordinatesToParent.emit(coordinatesToSend)
    })
  }
}
