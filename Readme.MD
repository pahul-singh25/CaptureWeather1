APIKey = c1d160e3e20a45c8bd544921242008

curl -X 'GET' \
'https://api.weatherapi.com/v1/current.json?q=Eagan%2C%20Minnesota&key=c1d160e3e20a45c8bd544921242008' \
-H 'accept: application/json'

<code>
{
"location": {
"name": "Eagan",
"region": "Minnesota",
"country": "United States of America",
"lat": 44.8,
"lon": -93.17,
"tz_id": "America/Chicago",
"localtime_epoch": 1724129531,
"localtime": "2024-08-19 23:52"
},
"current": {
"last_updated_epoch": 1724129100,
"last_updated": "2024-08-19 23:45",
"temp_c": 22.8,
"temp_f": 73,
"is_day": 0,
"condition": {
"text": "Partly cloudy",
"icon": "//cdn.weatherapi.com/weather/64x64/night/116.png",
"code": 1003
},
"wind_mph": 2.2,
"wind_kph": 3.6,
"wind_degree": 10,
"wind_dir": "N",
"pressure_mb": 1023,
"pressure_in": 30.22,
"precip_mm": 0,
"precip_in": 0,
"humidity": 57,
"cloud": 25,
"feelslike_c": 24.9,
"feelslike_f": 76.8,
"windchill_c": 18.4,
"windchill_f": 65.2,
"heatindex_c": 18.5,
"heatindex_f": 65.3,
"dewpoint_c": 13.7,
"dewpoint_f": 56.6,
"vis_km": 14,
"vis_miles": 8,
"uv": 1,
"gust_mph": 6.1,
"gust_kph": 9.8
}
}
</code>