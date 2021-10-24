import googlemaps
from datetime import datetime
import numpy as np
import sys

gmaps = googlemaps.Client(key='AIzaSyD8blytH8AVL1WqUjkMKl2NdDwRMiC3wxY')

userLocation = sys.argv[1]

geocode_result = gmaps.geocode(userLocation)
lat = (geocode_result[0]['geometry']['location']['lat'])
lng = (geocode_result[0]['geometry']['location']['lng'])

df = [[lat, lng]]

if (sys.argv[2] == "user"):
    np.savetxt("userLocation.csv", 
           df,
           delimiter =", ", 
           fmt ='% s')
else:
    np.savetxt("restaurantLocation.csv", 
           df,
           delimiter =", ", 
           fmt ='% s')