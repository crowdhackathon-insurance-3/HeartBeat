#multiple points request
from requests import get
places=[(23.73,38.02),(23.70,38.02),(23.72,38.02),(23.87,38.02),(23.80,38.02),(24.00,38.02),(23.77,38.02),(23.75,38.01)]
W_api="69f49b18f8d6d90c96e523a03220c9b3"
UVs=[]; #List of lists: For every lat lon place, return 8 dates of UV values
for place in places:
    init=[]
    lat=place[0]
    lon=place[1]
    requrl="http://api.openweathermap.org/data/2.5/uvi/forecast?appid="+W_api+"&lat="+str(lat)+"&lon="+str(lon)+"&cnt=8" #UV last 8 days
    r=get(requrl).json()
    for day in r:
        init.append(day["value"])
    UVs.append(init)